package com.ziheng.deal.controller;

import com.ziheng.deal.common.domain.VO.UserRegisterVO;
import com.ziheng.deal.common.resp.ResultJsonData;
import com.ziheng.deal.common.resp.ReturnCodeEnum;
import com.ziheng.deal.db.service.TUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reg")
@Tag(name = "用户注册")
public class RegisterController {
    @Resource
    private TUserService userService;

    /**
     * 验证邮箱是否注册和电子邮件发送
     * @param registerVO 注册信息
     * @return
     */
    @PostMapping("send")
    @Operation(summary = "验证邮箱是否注册和电子邮件发送")
    public ResultJsonData<Void> sendEmail(@RequestBody UserRegisterVO registerVO) {
        userService.SendEmail(registerVO.getEmail());
        return ResultJsonData.CustomizeSuccess(ReturnCodeEnum.RC200.getCode(), ReturnCodeEnum.RC200.getMessage());
    }

    /**
     * 验证码验证
     * @param registerVO 验证码信息
     * @return
     */
    @PostMapping("code")
    @Operation(summary = "验证码验证")
    public ResultJsonData<Void> codeVerification(@RequestBody UserRegisterVO registerVO){
        userService.CodeVerification(registerVO.getEmail(), registerVO.getCode());
        return ResultJsonData.success();
    }

    /**
     * 用户注册
     * @param userRegisterVO  用户名 密码  邮箱
     * @return 成功状态码
     */
    @PostMapping()
    @Operation(summary = "用户注册")
    public ResultJsonData<Void> UserRegister(@RequestBody UserRegisterVO userRegisterVO){
        userService.RegisterUser(userRegisterVO);
        return ResultJsonData.success();
    }

    /**
     * 密码找回验证账号 邮箱是否存在
     * @param userRegisterVO 账号 邮箱
     * @return 200
     */
    @PostMapping("recover")
    @Operation(summary = "验证码账号密码是否存在")
    public ResultJsonData<Void> UsernameAndEmailVerification(@RequestBody UserRegisterVO userRegisterVO) {
        userService.UsernameAndEmailVerification(userRegisterVO);
        return ResultJsonData.success();
    }

    /**
     * 找回密码验证码发送
     * @param userRegisterVO username email
     * @return 200
     */
    @PostMapping("recover/code")
    @Operation(summary = "密码找回验证码发送")
    public ResultJsonData<Void> RecoverCode(@RequestBody UserRegisterVO userRegisterVO) {
        userService.RecoverCode(userRegisterVO);
        return ResultJsonData.CustomizeSuccess(200, "验证码已发送注意查收");
    }

    @PostMapping("recover/code/v")
    @Operation(summary = "密码找回验证码验证")
    public ResultJsonData<Void> RecoverCodeVerification(@RequestBody UserRegisterVO userRegisterVO) {
        userService.RecoverCodeVerification(userRegisterVO);
        return ResultJsonData.success();
    }

    @PostMapping("recover/p")
    @Operation(summary = "密码修改")
    public ResultJsonData<Void> UpdateRecoverPassword(@RequestBody UserRegisterVO userRegisterVO) {
        userService.UpdateRecoverPassword(userRegisterVO);
        return ResultJsonData.CustomizeSuccess(200,"修改成功");
    }

}
