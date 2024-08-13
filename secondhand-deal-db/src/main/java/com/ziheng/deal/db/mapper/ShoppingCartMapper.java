package com.ziheng.deal.db.mapper;

import com.ziheng.deal.common.domain.DTO.shoppingCartDTO;
import com.ziheng.deal.db.entity.ShoppingCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【shopping_cart(购物车表)】的数据库操作Mapper
* @createDate 2024-06-30 19:41:58
* @Entity com.ziheng.deal.entity.ShoppingCart
*/
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
    // 根据用户id查找购物车中有效的商品
    List<shoppingCartDTO> getByUserIdObtainShoppingCartCommodfity(@Param("userId") Integer userId);

    // 根据用户id查找购物车中无效的商品
    List<shoppingCartDTO> getByUserIdObtainNoAvailShoppingCartCommodfity(@Param("userId") Integer userId);
}




