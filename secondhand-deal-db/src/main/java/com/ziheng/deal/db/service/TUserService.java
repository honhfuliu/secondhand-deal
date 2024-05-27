package com.ziheng.deal.db.service;

import com.ziheng.deal.common.domain.VO.UserRegisterVO;
import com.ziheng.deal.db.entity.TUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【t_user(用户表)】的数据库操作Service
* @createDate 2024-04-07 20:44:13
*/
public interface TUserService extends IService<TUser> {
    // 邮箱验证码发送
    void SendEmail(String email);

    // 验证码验证
    void CodeVerification(String email, String code);

    // 账号注册
    void RegisterUser(UserRegisterVO userRegisterVO);

    // 账号登录（密码方式）
    String UserLogin(UserRegisterVO userRegisterVO);

    // 账号登录（验证码方式）
    void EmailCodeSend(UserRegisterVO userRegisterVO);

    // 判断验证码码是否正确
    String EamilCodeVerifi(UserRegisterVO userRegisterVO);

    // 验证账号和邮箱是否存在
    void UsernameAndEmailVerification(UserRegisterVO userRegisterVO);

    // 密码找回邮箱验证码发送
    void RecoverCode(UserRegisterVO userRegisterVO);

    // 密码找回验证码验证
    void RecoverCodeVerification(UserRegisterVO userRegisterVO);

    // 密码修改（验证码方式）
    void UpdateRecoverPassword(UserRegisterVO userRegisterVO);


}
