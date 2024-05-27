package com.ziheng.deal.db.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ziheng.deal.common.domain.TokenInfo;
import com.ziheng.deal.common.domain.VO.UserRegisterVO;
import com.ziheng.deal.common.ex.customizeErorr.serviceErorr.*;
import com.ziheng.deal.common.util.SendEmailUtil;
import com.ziheng.deal.common.util.TokenUtil;
import com.ziheng.deal.db.entity.TUser;
import com.ziheng.deal.db.service.TUserService;
import com.ziheng.deal.db.mapper.TUserMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
* @author Administrator
* @description 针对表【t_user(用户表)】的数据库操作Service实现
* @createDate 2024-04-07 20:44:13
*/
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser>
    implements TUserService{

    public static final String REGISTER = "register";
    public static final String LOGIN = "login";
    public static final String RECOVER = "recover";

    @Resource
    private TUserMapper userMapper;

    @Resource
    private SendEmailUtil sendEmailUtil;


    @Autowired
    private StringRedisTemplate redisTemplate;


    @Autowired
    private TokenUtil tokenUtil;


    /**
     * 邮箱短信发送和验证邮箱是否能注册
     * @param email 邮箱号
     */
    @Override
    public void SendEmail(String email) {
        TUser user =  lambdaQuery()
                .eq(email != null, TUser::getEmail, email)
                .one();

        if (user != null) {
            throw new EmailDuplicateException("邮箱已被注册");
        }
        // 发送邮箱验证码
        String code = sendEmailUtil.SendEmail(email);
        String redisCodeKey = REGISTER + email;
        // 存储到redis缓存中
        redisTemplate.opsForValue().set(redisCodeKey, code, 300L, TimeUnit.SECONDS);
    }

    /**
     * 邮箱验证码验证
     * @param code 验证码
     */
    @Override
    public void CodeVerification(String email, String code) {
        String redisCodeKey = REGISTER + email;
//        String value = EmailCodes.get(email);
        String value = redisTemplate.opsForValue().get(redisCodeKey);
        if (value == null) {
            throw new CodePastDueException("验证码已过期");
        }
        if (!code.equals(value)) {
            throw new CodeException("验证码错误");
        }

    }

    /**
     * 用户信息注册
     * @param userRegisterVO 用户信息
     */
    @Override
    public void RegisterUser(UserRegisterVO userRegisterVO) {
        TUser result  = lambdaQuery()
                .eq(userRegisterVO.getUsername() != null, TUser::getUsername, userRegisterVO.getUsername())
                .one();

        if (result != null) {
            throw new UsernameDuplicateException("用户名已存在");
        }

        TUser user = new TUser();
        BeanUtil.copyProperties(userRegisterVO, user);
//        System.out.println(user.toString());

        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        String md5Passwprd = getMd5Password(user.getPassword(),salt);
        user.setPassword(md5Passwprd);
        // 补全日志
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(new Date());
        user.setCreateUser(user.getUsername());
        user.setCreateTime(new Date());
        // 插入到数据库中
        boolean resultUser = save(user);
        if (!resultUser) {
            throw new InsertException("插入数据时产生未知的异常，请于管理员进行联系");
        }
    }

    /**
     * 密码登录
     * @param userRegisterVO 账号 密码
     */
    @Override
    public String UserLogin(UserRegisterVO userRegisterVO) {
        TUser result = lambdaQuery()
                .eq(userRegisterVO.getUsername() != null, TUser::getUsername, userRegisterVO.getUsername())
                .one();
        if (result == null || !result.getIsDelete().equals(0)) {
            throw new UserDataDoesNotExistException("用户数据不存在或该账号已被冻结，请与管理员进行联系");
        }
        // 密码验证
        String md5Password = getMd5Password(userRegisterVO.getPassword(), result.getSalt());

        if (!result.getPassword().equals(md5Password)) {
            throw new PasswordNotMatchException("密码错误");
        }

        // 生成token
        TokenInfo info = new TokenInfo();
        info.setUsername(result.getUsername());
        info.setUId(result.getuId());
        return  tokenUtil.createToken(info);

    }

    /**
     * 登录验证码发送
     * @param userRegisterVO 账号 验证码
     * @return token
     */
    @Override
    public void EmailCodeSend(UserRegisterVO userRegisterVO) {
        TUser result = lambdaQuery()
                .eq(userRegisterVO.getEmail() != null, TUser::getEmail, userRegisterVO.getEmail())
                .one();
        if (result == null || !result.getIsDelete().equals(0)) {
            throw new EmailDoesNotExistException("邮箱不存在或者是该用户已被冻结");
        }
        // 该邮箱存在
        String code = sendEmailUtil.SendEmail(result.getEmail());
        //存储到redis中，时间是5分钟
        String redisKey = LOGIN + result.getEmail();
        redisTemplate.opsForValue().set(redisKey, code, 300L, TimeUnit.SECONDS);
    }

    /**
     * 登录验证码验证
     * @param userRegisterVO 邮箱  code
     * @return  token
     */
    @Override
    public String EamilCodeVerifi(UserRegisterVO userRegisterVO) {
        TUser result = lambdaQuery()
                .eq(userRegisterVO.getEmail() != null, TUser::getEmail, userRegisterVO.getEmail())
                .one();
        if (result == null || !result.getIsDelete().equals(0)) {
            throw new EmailDoesNotExistException("邮箱不存在或者是该用户已被冻结");
        }
        String redisKey = LOGIN + result.getEmail();
        // 获取redis中的验证码
        String code = redisTemplate.opsForValue().get(redisKey);
        if (code == null) {
            throw new CodePastDueException("验证码已过期");
        }
        if (!code.equals(userRegisterVO.getCode())) {
            throw new CodeException("验证码错误");
        }

        // 生成token
        TokenInfo info = new TokenInfo();
        info.setUsername(result.getUsername());
        info.setUId(result.getuId());
        return  tokenUtil.createToken(info);
    }

    /**
     * 密码找回验证，判断用户名和邮箱是否存在
     * @param userRegisterVO 用户名  邮箱
     */
    @Override
    public void UsernameAndEmailVerification(UserRegisterVO userRegisterVO) {
        TUser result = lambdaQuery()
                .eq(userRegisterVO.getUsername() != null, TUser::getUsername, userRegisterVO.getUsername())
                .eq(userRegisterVO.getEmail() != null, TUser::getEmail, userRegisterVO.getEmail())
                .one();
        if (result == null) {
            throw new UserDataDoesNotExistException("用户数据不存在");
        }

    }

    /**
     * 密码找回验证码发送
     * @param userRegisterVO email username
     */
    @Override
    public void RecoverCode(UserRegisterVO userRegisterVO) {
        TUser result = lambdaQuery()
                .eq(userRegisterVO.getUsername() != null, TUser::getUsername, userRegisterVO.getUsername())
                .eq(userRegisterVO.getEmail() != null, TUser::getEmail, userRegisterVO.getEmail())
                .one();
        if (result == null) {
            throw new EmailDoesNotExistException("邮箱错误");
        }
        String redisKey = RECOVER + result.getEmail();
        String code = sendEmailUtil.SendEmail(result.getEmail());
        // 存储到redis服务中
        redisTemplate.opsForValue().set(redisKey, code, 300L, TimeUnit.SECONDS);
    }

    /**
     * 密码找回验证码验证
     * @param userRegisterVO email code
     */
    @Override
    public void RecoverCodeVerification(UserRegisterVO userRegisterVO) {
        TUser result = lambdaQuery()
                .eq(userRegisterVO.getUsername() != null, TUser::getUsername, userRegisterVO.getUsername())
                .eq(userRegisterVO.getEmail() != null, TUser::getEmail, userRegisterVO.getEmail())
                .one();
        if (result == null) {
            throw new EmailDoesNotExistException();
        }

        String redisKey = RECOVER + userRegisterVO.getEmail();
        String value = redisTemplate.opsForValue().get(redisKey);
        if (value == null) {
            throw new CodePastDueException("验证码已过期");
        }
        if (!userRegisterVO.getCode().equals(value)) {
            throw new CodeException("验证码错误");
        }
    }

    @Override
    public void UpdateRecoverPassword(UserRegisterVO userRegisterVO) {
        TUser result = lambdaQuery()
                .eq(userRegisterVO.getUsername() != null, TUser::getUsername, userRegisterVO.getUsername())
                .eq(userRegisterVO.getEmail() != null, TUser::getEmail, userRegisterVO.getEmail())
                .one();
        if (result == null) {
            throw new EmailDoesNotExistException();
        }
        String NewPassword =getMd5Password(userRegisterVO.getPassword(), result.getSalt());

        TUser user = new TUser();
        user.setPassword(NewPassword);
        user.setuId(result.getuId());
        // 补全日志
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(new Date());
        int i = userMapper.updateById(user);
        if (i != 1) {
            throw new UpdateException("修改数据时产生了未知的异常");
        }


    }


    /*md5算法加密处理*/
    private String getMd5Password(String password, String salt) {
        // md5加密算法方法调用（进行三次加密）
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }


}




