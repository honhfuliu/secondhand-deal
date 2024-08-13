package com.ziheng.deal.db.service;

import com.ziheng.deal.common.domain.DTO.shoppingCartDTO;
import com.ziheng.deal.common.domain.VO.ShoppingCartVO;
import com.ziheng.deal.db.entity.ShoppingCart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【shopping_cart(购物车表)】的数据库操作Service
* @createDate 2024-06-30 19:41:58
*/
public interface ShoppingCartService extends IService<ShoppingCart> {
    // 根据商品的id和用户id添加商品到购物车
    void getByUserIdAddShoppingCart(ShoppingCartVO shoppingCartVO, Integer userId);

    // 根据用户id查询购物车中有效的商品
    List<shoppingCartDTO> getByUserIdShoppingCart(Integer userId);

    // 根据用户id查询购物车中无效的商品
    List<shoppingCartDTO> getByUserIdNoAvailShoppingCart(Integer userId);

    // 根据用户id和购物车id将商品从购物车中删除
    void getByUserIdAndShoppingIdDeleteCommodity(Integer userId, Integer shoppingId);


}
