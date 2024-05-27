package com.ziheng.deal.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ziheng.deal.common.domain.TokenInfo;
import com.ziheng.deal.common.domain.VO.UserRegisterVO;
import com.ziheng.deal.common.ex.customizeErorr.serviceErorr.PasswordNotMatchException;
import com.ziheng.deal.common.ex.customizeErorr.serviceErorr.UserDataDoesNotExistException;
import com.ziheng.deal.common.util.TokenUtil;
import com.ziheng.deal.db.entity.TAdmin;
import com.ziheng.deal.db.service.TAdminService;
import com.ziheng.deal.db.mapper.TAdminMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
* @author Administrator
* @description 针对表【t_admin】的数据库操作Service实现
* @createDate 2024-04-18 21:37:41
*/
@Service
public class TAdminServiceImpl extends ServiceImpl<TAdminMapper, TAdmin>
    implements TAdminService{
    @Resource
    private TAdminMapper adminMapper;

    @Resource
    private TokenUtil tokenUtil;

    /**
     * 管理员登录
     * @param userRegisterVO username password
     */
    @Override
    public String adminLoginVerification(UserRegisterVO userRegisterVO) {
        TAdmin result = lambdaQuery()
                .eq(userRegisterVO.getUsername() != null, TAdmin::getUsername, userRegisterVO.getUsername())
                .one();

        if (result == null) {
            throw new UserDataDoesNotExistException("用户信息不存在");
        }
        String md5Password = getMd5Password(userRegisterVO.getPassword(), result.getSalt());
        if (!md5Password.equals(result.getPassword())) {
            throw new PasswordNotMatchException("密码错误");
        }

        TokenInfo info = new TokenInfo();
        info.setUId(result.getAdminId());
        info.setUsername(result.getUsername());
        return tokenUtil.createToken(info);

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




