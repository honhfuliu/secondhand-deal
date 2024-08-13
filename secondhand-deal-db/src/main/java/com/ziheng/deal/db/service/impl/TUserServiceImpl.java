package com.ziheng.deal.db.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.TimeUtil;
import com.ziheng.deal.common.domain.DTO.UserInfoDTO;
import com.ziheng.deal.common.domain.TokenInfo;
import com.ziheng.deal.common.domain.VO.UserInfoV0;
import com.ziheng.deal.common.domain.VO.UserRegisterVO;
import com.ziheng.deal.common.ex.customizeErorr.controllerErorr.*;
import com.ziheng.deal.common.ex.customizeErorr.serviceErorr.*;
import com.ziheng.deal.common.util.SendEmailUtil;
import com.ziheng.deal.common.util.TokenUtil;
import com.ziheng.deal.db.entity.TUser;
import com.ziheng.deal.db.service.TUserService;
import com.ziheng.deal.db.mapper.TUserMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public static final String UPDATE = "update";

    public static final String SignIn = "sign_in";

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

        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        String md5Passwprd = getMd5Password(user.getPassword(),salt);
        user.setPassword(md5Passwprd);
        // 补全日志
        user.setNickname(user.getUsername());
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

    /**
     * 验证码修改密码
     * @param userRegisterVO 修改密码信息
     */
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

    /**
     * 根据用户id获取用户信息
     * @param userId 用户id
     * @return 用户信息
     */
    @Override
    public UserInfoDTO getByUserIdInfo(Integer userId) {
        // 根据用户id查询用户信息
        LambdaQueryWrapper<TUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(TUser::getNickname, TUser::getScore, TUser::getAvatar);
        wrapper.eq(TUser::getuId, userId);

        TUser user = userMapper.selectOne(wrapper);
        if (user == null) {
            throw new UserDataDoesNotExistException("用户数据不存在");
        }

        // 补全DTO对象
        UserInfoDTO dto = new UserInfoDTO();
        dto.setName(user.getNickname());
        dto.setScore(user.getScore());
        dto.setPath(user.getAvatar());



        return dto;
    }

    /**
     * 根据用户id修改用户信息
     * @param userId 用户id
     * @param userInfoV0 用户信息
     */
    @Override
    public void updateUserInfo(Integer userId, UserInfoV0 userInfoV0) {
        // 根据用户id查询用户信息
        LambdaQueryWrapper<TUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TUser::getuId, userId);

        TUser result = userMapper.selectOne(wrapper);
        if (result == null) {
            throw new UserDataDoesNotExistException("用户数据不存在");
        }

        // 将修改休的信息存储到user对象中
        TUser user = new TUser();
        user.setuId(userId);
        user.setNickname(userInfoV0.getName());
        user.setGender(userInfoV0.getSex());
        user.setBirthday(userInfoV0.getBirthday());
        user.setModifiedTime(new Date());

        //进行修改数据
        int i = userMapper.updateById(user);
        if (i !=  1) {
            throw new UpdateException();
        }


    }

    /**
     * 根据用户id获取到当前账号的邮箱
     * @param userId 用户id
     * @return 邮箱
     */
    @Override
    public String getByUserIdEmail(Integer userId) {
        TUser result = lambdaQuery()
                .eq(TUser::getuId, userId)
                .one();

        if (result == null) {
            throw new UserDataDoesNotExistException();
        }

        return result.getEmail();
    }

    /**
     * 用户修改密码验证码发送
     * @param userId 用户id
     */
    @Override
    public void UpdatePasswordEmailCodeGain(Integer userId) {
        // 根据用户id获取到用户信息
        TUser result = lambdaQuery()
                .eq(TUser::getuId, userId)
                .one();
        if (result == null) {
            throw new UserDataDoesNotExistException();
        }

        // 发送验证码
        String code = sendEmailUtil.SendEmail(result.getEmail());
        String redisCodeKey = UPDATE + result.getEmail();

        redisTemplate.opsForValue().set(redisCodeKey, code, 300L, TimeUnit.SECONDS);
    }

    /**
     * 修改密码验证码验证
     * @param userId 用户id
     * @param code 验证码
     */
    @Override
    public void UpdatePasswordEmailCodeVerification(Integer userId, String email, String code) {
        // 根据用户id获取到用户信息
        TUser result = lambdaQuery()
                .eq(TUser::getuId, userId)
                .eq(TUser::getEmail, email)
                .one();
        if (result == null) {
            throw new UserDataDoesNotExistException();
        }

        // 获取到redis中存储的验证码
        String key = UPDATE + result.getEmail();
        String value = redisTemplate.opsForValue().get(key);

        if (value == null) {
            throw new CodePastDueException("验证码已过期");
        }
        if (!code.equals(value)) {
            throw new CodeException("验证码错误");
        }

        // 清除redis中的验证码
        redisTemplate.delete(key);


    }

    /**
     * 登录之后密码修改
     * @param userId 用户id
     * @param password 新密码
     */
    @Override
    public void UpdatePassword(Integer userId,  String email, String password) {
        // 根据用户id获取到用户信息
        TUser result = lambdaQuery()
                .eq(TUser::getuId, userId)
                .eq(TUser::getEmail, email)
                .one();
        if (result == null) {
            throw new UserDataDoesNotExistException();
        }
        if (password == null) {
            throw  new PasswordCannotEmptyException("密码不能为空");
        }

        TUser updateUser = new TUser();
        String md5Password = getMd5Password(password, result.getSalt());
        updateUser.setPassword(md5Password);
        updateUser.setModifiedTime(new Date());
        updateUser.setuId(result.getuId());

        int i = userMapper.updateById(updateUser);

        if (i != 1) {
            throw  new UpdateException();
        }

    }


    // 用户上传头像
    //设置上传文件的最大值
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    // 设置上传文件的类型
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/jpg");
        AVATAR_TYPE.add("image/png");
    }
    // 设置上传地址
    @Value("${upload.path}")
    String updateFilePath;
    /**
     * 用户头像上传
     * @param file 头像
     * @param userId 用户id
     */
    @Override
    public void uploadAvatar(MultipartFile file, Integer userId){
        // 根据用户id查询出用户信息
        TUser user = userMapper.selectById(userId);
        if (user == null) {
            throw  new UserDataDoesNotExistException();
        }


        // 判断文件是否为空
        if (file.isEmpty()) {
            throw new FileEmptyException("文件为空");
        }

        // 判断文件大小
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException("文件超过规定大小");
        }

        // 判断文件类型
        String type = file.getContentType();
        if (!AVATAR_TYPE.contains(type)) {
            throw new FileTypeException("文件类型错误");
        }

        // 文件保存位置
        String path = updateFilePath + "/upload";

        // path路径是否存在
        File dir = new File(path);
        if (!dir.exists()) { // 检测目录是否存在
            dir.mkdirs(); //创建目录
        }

        // 获取到这个文件的名称， UUID工具生成一个新的随机字符串作为名称
        String filename = file.getOriginalFilename(); // 包含名称+后缀
        int i = filename.lastIndexOf(".");
        String suffix = filename.substring(i);
        String uploadName = UUID.randomUUID().toString().toUpperCase() + suffix;

        File dest = new File(dir, uploadName);

        // 将参数file中数据写入到这个空文件中
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        } catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        }

        // 新文件路径
        String avatar = "upload/" + uploadName;


        //如果用户已经有头像，将存储的头像图片删除
        if (user.getAvatar() != null) {
            String userAvatarPath = updateFilePath + "/" + user.getAvatar();
            Path path1 = Paths.get(userAvatarPath); // 查询这个
            boolean b = Files.exists(path1);

            if (b) {
                try {
                    Files.delete(path1);
                } catch (IOException e) {
                    throw new FileUploadIOException("文件上传时产生未知的异常，请于管理员联系");
                }
            }
        }



        //更新用户信息

        TUser user1 = new TUser();
        user1.setuId(userId);
        user1.setAvatar(avatar);
        user1.setModifiedTime(new Date());

        int updatedById = userMapper.updateById(user1);
        if (updatedById != 1) {
            throw new UpdateException();
        }

    }

    /**
     * 用户签到实现
     * @param userId 用户id
     */
    @Override
    public void SignIn(Integer userId) {
        // 根据用户id 查询用户数据是否存在
        TUser reusltUser = userMapper.selectById(userId);

        if (reusltUser == null) {
            throw  new UserDataDoesNotExistException();
        }

        // 获取今天是哪一天
        int bitOffset = LocalDate.now().getDayOfYear();

        // 查询redis中用户是否已经签到过
        String key = SignIn + userId;
        Boolean bit = redisTemplate.opsForValue().getBit(key, bitOffset);

        if (bit != null && bit) {
            throw new SignedInException("用户已签到！");
        }


        // 进行签到
        redisTemplate.opsForValue().setBit(key, bitOffset,true);
        // 获取过期时间
        Long SinInTime = GetSignInExpirationTime();
        // 设置过期时间
        redisTemplate.expire(key, SinInTime, TimeUnit.SECONDS);


        // 添加积分
        TUser user = new TUser();
        user.setuId(userId);
        user.setScore(reusltUser.getScore() + 30);
        user.setModifiedTime(new Date());

        // 更新数据
        int i = userMapper.updateById(user);

        if (i != 1) {
            throw new UpdateException();
        }

    }

    /**
     * 用户积分获取
     * @param userId 用户id
     * @return 用户积分
     */
    @Override
    public Integer UserPointsAcquisition(Integer userId) {
        TUser user = userMapper.selectById(userId);
        return user.getScore();
    }


    // 过期时间获取
    private Long GetSignInExpirationTime(){
        // 当前时间
        LocalDateTime now = LocalDateTime.now();

        // 今天的午夜时间
        LocalDateTime midnight = now.toLocalDate().atStartOfDay().plusDays(1);

        // 计算时间差
        Duration duration = Duration.between(now, midnight);
        return duration.getSeconds();

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




