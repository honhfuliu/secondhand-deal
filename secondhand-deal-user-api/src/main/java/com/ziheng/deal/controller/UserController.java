package com.ziheng.deal.controller;

import com.ziheng.deal.common.domain.DTO.UserInfoDTO;
import com.ziheng.deal.common.domain.TokenInfo;
import com.ziheng.deal.common.domain.VO.UserInfoV0;
import com.ziheng.deal.common.domain.VO.UserRegisterVO;
import com.ziheng.deal.common.resp.ResultJsonData;
import com.ziheng.deal.common.util.TokenUtil;
import com.ziheng.deal.db.service.TUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Resource
    private TokenUtil tokenUtil;

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

    /**
     * 根据用户的id获取到用户的信息
     * @return 用户信息
     */
    @GetMapping("info")
    @Operation(summary = "根据用户的id获取到用户的信息")
    public ResultJsonData<UserInfoDTO> getByIdUserInfo(){
        String token = httpServletRequest.getHeader("token");
        TokenInfo tokenTuser = tokenUtil.getTokenTuser(token);
        UserInfoDTO dto = userService.getByUserIdInfo(tokenTuser.getUId());
        return ResultJsonData.success(dto);

    }


    /**
     * 根据用户id修改用户信息
     * @param userInfoV0 用户修改的信息
     * @return OK
     */
    @PostMapping("u")
    @Operation(summary = "根据用户id修改用户信息")
    public ResultJsonData<Void> updateUserInfo(@RequestBody UserInfoV0 userInfoV0) {
        String token = httpServletRequest.getHeader("token");
        TokenInfo tokenInfo = tokenUtil.getTokenTuser(token);
        userService.updateUserInfo(tokenInfo.getUId(), userInfoV0);

        return ResultJsonData.success();
    }

    /**
     * 根据用户的id获取到用户的邮箱
     * @return 用户邮箱
     */
    @GetMapping("v/code")
    @Operation(summary = "根据用户的id获取到用户的信息")
    public ResultJsonData<Map<String, String>> getByIdEmail(){
        String token = httpServletRequest.getHeader("token");
        TokenInfo tokenTuser = tokenUtil.getTokenTuser(token);
        String email = userService.getByUserIdEmail(tokenTuser.getUId());
        HashMap<String, String> map = new HashMap<>();
        map.put("email", email);
        return ResultJsonData.success(map);
    }

    /**
     * 密码修改验证码发送
     * @return OK
     */
    @PostMapping("v/gain")
    @Operation(summary = "修改密码验证码发送")
    public ResultJsonData<Void> SendEmailCode(@RequestBody Map<String,String> info){
        String token = httpServletRequest.getHeader("token");
        TokenInfo tokenTuser = tokenUtil.getTokenTuser(token);

        userService.UpdatePasswordEmailCodeGain(tokenTuser.getUId());

        return ResultJsonData.CustomizeSuccess(200, "验证码已发送");

    }

    /**
     * 修改密码验证码验证
     * @param info email code
     * @return OK
     */
    @PostMapping("v/gainCode")
    @Operation(summary = "修改密码验证码验证")
    public ResultJsonData<Void> VerificationEmailCode(@RequestBody Map<String,String> info){
        String token = httpServletRequest.getHeader("token");
        TokenInfo tokenTuser = tokenUtil.getTokenTuser(token);

        userService.UpdatePasswordEmailCodeVerification(tokenTuser.getUId(), info.get("email"), info.get("code"));

        return ResultJsonData.success();
    }

    /**
     * 密码修改
     * @param info email password
     * @return OK
     */
    @PostMapping("v/updatePasswd")
    @Operation(summary = "密码修改")
    public ResultJsonData<Void> UpdatePassword(@RequestBody Map<String,String> info){
        String token = httpServletRequest.getHeader("token");
        TokenInfo tokenTuser = tokenUtil.getTokenTuser(token);

        userService.UpdatePassword(tokenTuser.getUId(), info.get("email"), info.get("password"));


        return ResultJsonData.success();
    }

    /**
     * 头像上传
     * @param file  图片地址
     * @return OK
     */
    @PostMapping("avatar")
    @Operation(summary = "用户头像上传")
    public ResultJsonData<Void>  commodityPictureSaves(@RequestParam("file")MultipartFile file) {
        String token = httpServletRequest.getHeader("token");
        TokenInfo tokenTuser = tokenUtil.getTokenTuser(token);

        userService.uploadAvatar(file, tokenTuser.getUId());


        return ResultJsonData.success();
    }


    @PostMapping("signIn")
    @Operation(summary = "用户签到")
    public ResultJsonData<Void> userSignIn(){
        String token = httpServletRequest.getHeader("token");
        TokenInfo tokenTuser = tokenUtil.getTokenTuser(token);

        userService.SignIn(tokenTuser.getUId());

        return ResultJsonData.success();
    }

    @GetMapping("acquisition")
    @Operation(summary = "用户积分获取")
    public ResultJsonData<HashMap<String, Integer>> UserPointsAcquisition(){
        String token = httpServletRequest.getHeader("token");
        TokenInfo tokenTuser = tokenUtil.getTokenTuser(token);

        Integer acquisition = userService.UserPointsAcquisition(tokenTuser.getUId());

        HashMap<String, Integer> map = new HashMap<>();
        map.put("acquisition", acquisition);

        return ResultJsonData.success(map);
    }






}
