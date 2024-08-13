package com.ziheng.deal.controller;

import com.ziheng.deal.common.domain.DTO.AddressDTO;
import com.ziheng.deal.common.domain.TokenInfo;
import com.ziheng.deal.common.domain.VO.AddressVO;
import com.ziheng.deal.common.resp.ResultJsonData;
import com.ziheng.deal.common.util.TokenUtil;
import com.ziheng.deal.db.service.TAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("address")
@Tag(name = "用户收货地址相关接口")
public class AddressController {

    @Resource
    private TAddressService addressService;

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private TokenUtil tokenUtil;

    @PostMapping()
    @Operation(summary = "用户收货地址添加接口")
    public ResultJsonData<Void> addDeliveryAddress(@RequestBody @Valid AddressVO addressVO){
        String token = servletRequest.getHeader("token");
        TokenInfo userInfo = tokenUtil.getTokenTuser(token);
        addressService.AddShippingAddressBasedUserId(userInfo.getUId(), userInfo.getUsername(), addressVO);
        return ResultJsonData.success();
    }


    @GetMapping()
    @Operation(summary = "用户收货地址获取")
    public ResultJsonData<List<AddressDTO>> getAllAddress(){
        String token = servletRequest.getHeader("token");
        TokenInfo userInfo = tokenUtil.getTokenTuser(token);
        List<AddressDTO> addressDTOS = addressService.getAllAddress(userInfo.getUId());

        return  ResultJsonData.success(addressDTOS);
    }

    @PostMapping("update")
    @Operation(summary = "收货地址修改")
    public ResultJsonData<Void> updateAddress(@RequestBody @Valid AddressVO addressVO){
        String token = servletRequest.getHeader("token");
        TokenInfo userInfo = tokenUtil.getTokenTuser(token);
        addressService.updateAddress(addressVO, userInfo.getUId());
        return ResultJsonData.success();
    }

    @PostMapping("delete")
    @Operation(summary = "收货地址删除")
    public ResultJsonData<Void> updateAddress(@RequestBody HashMap<String, Integer> data){
        String token = servletRequest.getHeader("token");
        TokenInfo userInfo = tokenUtil.getTokenTuser(token);
        addressService.deleteAddress(userInfo.getUId(), data.get("status"));
        return ResultJsonData.success();
    }


    @PostMapping("set")
    @Operation(summary = "默认收货地址修改")
    public ResultJsonData<Void> acquiesceAddressSet(@RequestBody HashMap<String, Integer> data){
        String token = servletRequest.getHeader("token");
        TokenInfo userInfo = tokenUtil.getTokenTuser(token);
        addressService.setAcquiesceAddress(data.get("newAddress"), userInfo.getUId());
        return ResultJsonData.success();
    }





}
