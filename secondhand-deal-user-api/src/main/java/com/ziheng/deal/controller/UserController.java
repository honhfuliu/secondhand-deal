package com.ziheng.deal.controller;

import com.ziheng.deal.common.domain.VO.UserRegisterVO;
import com.ziheng.deal.common.resp.ResultJsonData;
import com.ziheng.deal.db.service.TUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
@Slf4j
@Tag(name = "用户管理")
public class UserController {
    @Resource
    private TUserService userService;
    @Resource
    private HttpServletRequest httpServletRequest;

    /**
     * 用户登录
     * @param userRegisterVO 账号  密码
     * @return token
     */
    @PostMapping()
    @Operation(summary = "账号密码登录")
    public ResultJsonData<Map<String,String>> UserLogin(@RequestBody UserRegisterVO userRegisterVO) {
        String token = userService.UserLogin(userRegisterVO);
        Map<String, String> data = new HashMap<>();
        data.put("token", token);
        return ResultJsonData.success(data);
    }


    /**
     * 验证码码发送
     * @param userRegisterVO 邮箱
     * @return
     */
    @PostMapping("send")
    @Operation(summary = "登录验证码发送")
    public ResultJsonData<Void> SendEmailCode(@RequestBody UserRegisterVO userRegisterVO) {
        userService.EmailCodeSend(userRegisterVO);
        return ResultJsonData.success();
    }


    @PostMapping("code")
    @Operation(summary = "登录验证码验证登录")
    public ResultJsonData<Map<String,String>> loginEmailCode(@RequestBody UserRegisterVO userRegisterVO) {
        String token = userService.EamilCodeVerifi(userRegisterVO);
        Map<String, String> data = new HashMap<>();
        data.put("token", token);
        return ResultJsonData.success(data);
    }
}
