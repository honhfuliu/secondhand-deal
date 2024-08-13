package com.ziheng.deal.db.service;

import com.ziheng.deal.common.domain.DTO.UserInfoDTO;
import com.ziheng.deal.common.domain.TokenInfo;
import com.ziheng.deal.common.domain.VO.UserInfoV0;
import com.ziheng.deal.common.domain.VO.UserRegisterVO;
import com.ziheng.deal.db.entity.TUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

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

    // 个人信息获取
    UserInfoDTO getByUserIdInfo(Integer userId);

    // 根据用户id修改用户信息
    void updateUserInfo(Integer userId, UserInfoV0 userInfoV0);

    // 根据用户id获取到当前账号的邮箱
    String getByUserIdEmail(Integer userId);

    // 修改密码验证码发送
    void UpdatePasswordEmailCodeGain(Integer userId);

    // 修改密码验证码验证
    void UpdatePasswordEmailCodeVerification(Integer userId, String email, String code);

    // 密码修改
    void UpdatePassword(Integer userId,  String email, String password);

    // 用户头像修改
    void uploadAvatar(MultipartFile file, Integer userId);

    // 用户签到实现
    void SignIn(Integer userId);

    // 用户积分获取
    Integer UserPointsAcquisition(Integer userId);


}
