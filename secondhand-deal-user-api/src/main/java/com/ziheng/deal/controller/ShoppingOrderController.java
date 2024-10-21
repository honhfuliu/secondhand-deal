package com.ziheng.deal.controller;

import com.ziheng.deal.common.domain.DTO.MyOrderDTO;
import com.ziheng.deal.common.domain.DTO.PageDOT;
import com.ziheng.deal.common.domain.DTO.SettlementCommodityDTO;
import com.ziheng.deal.common.domain.TokenInfo;
import com.ziheng.deal.common.domain.VO.CommodityOrderVO;
import com.ziheng.deal.common.domain.VO.CommoditySettlementVO;
import com.ziheng.deal.common.domain.VO.MyOrderVO;
import com.ziheng.deal.common.domain.VO.ShoppingCartCommoditySettlementVO;
import com.ziheng.deal.common.resp.ResultJsonData;
import com.ziheng.deal.common.util.TokenUtil;
import com.ziheng.deal.db.service.TOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("order")
@Tag(name = "商品订单相关接口")
public class ShoppingOrderController {
    @Resource
    private HttpServletRequest httpServletRequest;

    @Resource
    private TokenUtil tokenUtil;

    @Resource
    private TOrderService orderService;


    @GetMapping("")
    @Operation(summary = "我的订单获取")
    public ResultJsonData<PageDOT<List<MyOrderDTO>>> showMyOrder(MyOrderVO orderVO){
        String token = httpServletRequest.getHeader("token");
        TokenInfo tokenInfo = tokenUtil.getTokenTuser(token);
        PageDOT<List<MyOrderDTO>> listPageDOT = orderService.showMyOrder(orderVO, tokenInfo.getUId());
        return ResultJsonData.success(listPageDOT);
    }

    @PostMapping("pay")
    @Operation(summary = "订单支付")
    public ResultJsonData<List<Void>> OrderPay(@Valid @RequestBody HashMap<String,String> map){
        String token = httpServletRequest.getHeader("token");
        TokenInfo tokenInfo = tokenUtil.getTokenTuser(token);
        orderService.CommodityOrderPay(map.get("OrderNumber"), tokenInfo.getUId(), tokenInfo.getUsername());
        return ResultJsonData.success();
    }


    @PostMapping()
    @Operation(summary = "购物车商品结算接口")
    public ResultJsonData<List<SettlementCommodityDTO>> ShoppingCartCommoditySettlement(@Valid @RequestBody List<ShoppingCartCommoditySettlementVO> commoditySettlements){
        String token = httpServletRequest.getHeader("token");
        TokenInfo tokenInfo = tokenUtil.getTokenTuser(token);
        List<SettlementCommodityDTO> dtos = orderService.ShoppingCartCommoditySettlement(commoditySettlements, tokenInfo.getUId(), tokenInfo.getUsername());

        return ResultJsonData.success(dtos);
    }

    @PostMapping("page")
    @Operation(summary = "商品详情页结算接口")
    public ResultJsonData<List<SettlementCommodityDTO>> CommoditySettlement(@Valid @RequestBody CommoditySettlementVO commoditySettlement){
        String token = httpServletRequest.getHeader("token");
        TokenInfo tokenInfo = tokenUtil.getTokenTuser(token);
        List<SettlementCommodityDTO> dtos = orderService.CommoditySettlement(commoditySettlement, tokenInfo.getUId(), tokenInfo.getUsername());

        return ResultJsonData.success(dtos);
    }


    @PostMapping("submit")
    @Operation(summary = "商品订单创建")
    public ResultJsonData<HashMap<String,String>> CommoditySettlement(@Valid @RequestBody CommodityOrderVO commodityOrderVO){
        String token = httpServletRequest.getHeader("token");
        TokenInfo tokenInfo = tokenUtil.getTokenTuser(token);
        HashMap<String, String> map = orderService.CommodityOrderCreate(commodityOrderVO, tokenInfo.getUId(), tokenInfo.getUsername());

        return ResultJsonData.success(map);
    }


}
