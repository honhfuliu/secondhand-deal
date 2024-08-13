package com.ziheng.deal.controller;

import com.ziheng.deal.common.domain.DTO.shoppingCartDTO;
import com.ziheng.deal.common.domain.TokenInfo;
import com.ziheng.deal.common.domain.VO.ShoppingCartVO;
import com.ziheng.deal.common.resp.ResultJsonData;
import com.ziheng.deal.common.util.TokenUtil;
import com.ziheng.deal.db.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("shopping")
@Tag(name = "购物车相关接口")
public class CommodityShoppingCartController {
    @Resource
    private ShoppingCartService shoppingCartService;

    @Resource
    private HttpServletRequest servletRequest;

    @Resource
    private TokenUtil tokenUtil;

    @PostMapping()
    @Operation(summary = "添加商品到购物车接口")
    public ResultJsonData<Void> addShoppingCart(@Valid @RequestBody ShoppingCartVO shoppingCartVO){
        String token = servletRequest.getHeader("token");
        TokenInfo info = tokenUtil.getTokenTuser(token);
        shoppingCartService.getByUserIdAddShoppingCart(shoppingCartVO, info.getUId());
        return ResultJsonData.success();
    }

    @GetMapping()
    @Operation(summary = "根据用户id获取购物车中的有效商品")
    public ResultJsonData<List<shoppingCartDTO>> ObtainShoppingCartInfo(){
        String token = servletRequest.getHeader("token");
        TokenInfo info = tokenUtil.getTokenTuser(token);
        List<shoppingCartDTO> list = shoppingCartService.getByUserIdShoppingCart(info.getUId());
        return ResultJsonData.success(list);
    }

    @GetMapping("/n")
    @Operation(summary = "根据用户id获取购物车中无效的商品")
    public ResultJsonData<List<shoppingCartDTO>> ObtainShoppingCartInfo1(){
        String token = servletRequest.getHeader("token");
        TokenInfo info = tokenUtil.getTokenTuser(token);
        List<shoppingCartDTO> list = shoppingCartService.getByUserIdNoAvailShoppingCart(info.getUId());
        return ResultJsonData.success(list);
    }

    @PostMapping("/d")
    @Operation(summary = "根据id删除购物车中的商品")
    public ResultJsonData<Void> deleteShoppingCart(@RequestBody Map<String, Integer> payload){
        String token = servletRequest.getHeader("token");
        TokenInfo info = tokenUtil.getTokenTuser(token);
        shoppingCartService.getByUserIdAndShoppingIdDeleteCommodity(info.getUId(), payload.get("status"));
        return ResultJsonData.success();
    }


}
