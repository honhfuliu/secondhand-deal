package com.ziheng.deal.admin.controller;

import com.ziheng.deal.common.domain.VO.UserRegisterVO;
import com.ziheng.deal.common.resp.ResultJsonData;
import com.ziheng.deal.db.service.TAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin")
@Tag(name = "管理员账号接口")
public class AdminController {
    @Resource
    private TAdminService adminService;

    /**
     * 管理员登录
     * @param userRegisterVO username password
     * @return token
     */
    @PostMapping("/login")
    @Operation(summary = "管理员登录")
    public ResultJsonData<Map<String, String>> adminLoginVerification(@RequestBody UserRegisterVO userRegisterVO) {
        String token = adminService.adminLoginVerification(userRegisterVO);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        return ResultJsonData.success(map);
    }



}
