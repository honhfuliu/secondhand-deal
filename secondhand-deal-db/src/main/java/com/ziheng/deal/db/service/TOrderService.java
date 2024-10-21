package com.ziheng.deal.db.service;

import com.ziheng.deal.common.domain.DTO.MyOrderDTO;
import com.ziheng.deal.common.domain.DTO.PageDOT;
import com.ziheng.deal.common.domain.DTO.SettlementCommodityDTO;
import com.ziheng.deal.common.domain.VO.CommodityOrderVO;
import com.ziheng.deal.common.domain.VO.CommoditySettlementVO;
import com.ziheng.deal.common.domain.VO.MyOrderVO;
import com.ziheng.deal.common.domain.VO.ShoppingCartCommoditySettlementVO;
import com.ziheng.deal.db.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;

/**
* @author Administrator
* @description 针对表【t_order(订单表)】的数据库操作Service
* @createDate 2024-08-04 20:25:57
*/
public interface TOrderService extends IService<TOrder> {
    // 查看所有订单
    PageDOT<List<MyOrderDTO>> showMyOrder(MyOrderVO orderVO, Integer userId);

    // 过期订单处理
    void ExpiredCommodityOrderSolve(String orderNumber);

    // 商品订单支付
    void CommodityOrderPay(String orderNumber, Integer userId, String updateUser);

    // 商品订单创建
    HashMap<String, String> CommodityOrderCreate(CommodityOrderVO commodityOrderVO, Integer userId, String createUser);

    // 商品详情页商品结算
    List<SettlementCommodityDTO> CommoditySettlement(CommoditySettlementVO commoditySettlement, Integer userId, String createUser);

    // 购物车商品结算
    List<SettlementCommodityDTO> ShoppingCartCommoditySettlement(List<ShoppingCartCommoditySettlementVO> commoditySettlements, Integer userId, String createUser);



    // 商品支付

    // 商品取消支付

}
