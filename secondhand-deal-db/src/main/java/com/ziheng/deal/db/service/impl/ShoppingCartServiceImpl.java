package com.ziheng.deal.db.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ziheng.deal.common.domain.DTO.shoppingCartDTO;
import com.ziheng.deal.common.domain.VO.CommodityVO;
import com.ziheng.deal.common.domain.VO.ShoppingCartVO;
import com.ziheng.deal.common.ex.customizeErorr.serviceErorr.*;
import com.ziheng.deal.db.entity.CommoditySku;
import com.ziheng.deal.db.entity.ShoppingCart;
import com.ziheng.deal.db.entity.TCommodity;
import com.ziheng.deal.db.mapper.CommoditySkuMapper;
import com.ziheng.deal.db.mapper.TCommodityMapper;
import com.ziheng.deal.db.service.ShoppingCartService;
import com.ziheng.deal.db.mapper.ShoppingCartMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
* @author Administrator
* @description 针对表【shopping_cart(购物车表)】的数据库操作Service实现
* @createDate 2024-06-30 19:41:58
*/
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>
    implements ShoppingCartService{

    @Resource
    private TCommodityMapper commodityMapper;

    @Resource
    private ShoppingCartMapper shoppingCartMapper;

    @Resource
    private CommoditySkuMapper commoditySkuMapper;


    /**
     * 根据商品id和用户id将商品添加到购物车中
     * @param shoppingCartVO 商品信息
     * @param userId 用户id
     */
    @Override
    public void getByUserIdAddShoppingCart(ShoppingCartVO shoppingCartVO, Integer userId) {
        //根据商品id查询商品是否存在
        TCommodity resultCommodity = commodityMapper.selectById(shoppingCartVO.getCommodityId());

        if (resultCommodity == null) {
            throw new CommodityDoesNotExistException();
        }

        // 根据商品id查询出商品的sku
        LambdaQueryWrapper<CommoditySku> skuWrapper = new LambdaQueryWrapper<>();
        skuWrapper.eq(CommoditySku::getCommodityId, resultCommodity.getcId());

        List<CommoditySku> commoditySkuList = commoditySkuMapper.selectList(skuWrapper);

        CommoditySku commoditySku = null;


        String jsonStr = JSONUtil.toJsonStr(shoppingCartVO.getCommoditySku());
        for (CommoditySku sku : commoditySkuList) {
            if (sku.getCommoditySku().equals(jsonStr)) {
                commoditySku = sku;
                break;
            }
        }

        if (commoditySku == null) {
            // 抛出异常商品sku错误
            throw new CommoditySkuException("商品sku错误异常");
        }


        // 查询这个sku是否存在于购物车中
        LambdaQueryWrapper<ShoppingCart> cartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        cartLambdaQueryWrapper.eq(ShoppingCart::getSkuId, commoditySku.getSkuId());
        cartLambdaQueryWrapper.eq(ShoppingCart::getUserId, userId);
        cartLambdaQueryWrapper.eq(ShoppingCart::getCommodityId, resultCommodity.getcId());
        cartLambdaQueryWrapper.eq(ShoppingCart::getShoppingStatus, 0);
        ShoppingCart resultShoppingCart = shoppingCartMapper.selectOne(cartLambdaQueryWrapper);

        if (resultShoppingCart == null) {
            // 该商品未添加到购物车表的处理
            ShoppingCart cart = new ShoppingCart();
            cart.setUserId(userId);
            cart.setCommodityId(shoppingCartVO.getCommodityId());
            cart.setShoppingNumber(shoppingCartVO.getShoppingNumber());
            cart.setSkuId(commoditySku.getSkuId());
            cart.setShoppingStatus(0);
            cart.setShoppingTime(new Date());
            cart.setShoppingUpdate(new Date());
            // 购物车价格计算
            BigDecimal NumberDecimal = new BigDecimal(shoppingCartVO.getShoppingNumber());
            cart.setShoppingPrice(commoditySku.getPrice().multiply(NumberDecimal));

            // 插入到数据库中
            int i = shoppingCartMapper.insert(cart);
            if (i != 1) {
                throw new InsertException();
            }


        }else {
            // 该商品已在数据库中的处理

            resultShoppingCart.setShoppingNumber(resultShoppingCart.getShoppingNumber() + shoppingCartVO.getShoppingNumber());

            BigDecimal NumberDecimal = new BigDecimal(resultShoppingCart.getShoppingNumber());
            resultShoppingCart.setShoppingPrice(commoditySku.getPrice().multiply(NumberDecimal));

            int i = shoppingCartMapper.updateById(resultShoppingCart);
            if (i != 1) {
                throw new UpdateException();
            }
        }

    }

    /**
     * 根据用户id获取到用户购物车中有效的商品
     * @param userId 用户id
     * @return 购物车有效商品信息
     */
    @Override
    public List<shoppingCartDTO> getByUserIdShoppingCart(Integer userId) {
        List<shoppingCartDTO> dtoList = shoppingCartMapper.getByUserIdObtainShoppingCartCommodfity(userId);
        for (shoppingCartDTO dto : dtoList) {
            dto.setCommoditySku(JSONUtil.toBean(dto.getStingSku(), HashMap.class));
            dto.setStingSku(null);
        }
        return dtoList;
    }

    /**
     * 根据用户id获取到用户购物车中无效的商品
     * @param userId 用户id
     * @return 购物车无效商品信息
     */
    @Override
    public List<shoppingCartDTO> getByUserIdNoAvailShoppingCart(Integer userId) {
        List<shoppingCartDTO> dtoList = shoppingCartMapper.getByUserIdObtainNoAvailShoppingCartCommodfity(userId);
        for (shoppingCartDTO dto : dtoList) {
            dto.setCommoditySku(JSONUtil.toBean(dto.getStingSku(), HashMap.class));
            dto.setStingSku(null);
        }
        return dtoList;
    }

    /**
     * 根据用户id和购物车id将商品从购物车中删除
     * @param userId 用户id
     * @param shoppingId 商品id
     */
    @Override
    public void getByUserIdAndShoppingIdDeleteCommodity(Integer userId, Integer shoppingId) {
        // 判断购物车商品id是否为空
        if (shoppingId == null) {
            throw new ShoppingCartIdNotNull();
        }

        // 根据用户id和购物车商品id查询是否有该条记录
        ShoppingCart resultShoppingCart = lambdaQuery()
                .eq(ShoppingCart::getUserId, userId)
                .eq(ShoppingCart::getShoppingId, shoppingId)
                .one();

        if (resultShoppingCart == null) {
            throw new ShoppingCartInfoNull();
        }

        // 将数据删除
        resultShoppingCart.setShoppingStatus(1);
        resultShoppingCart.setShoppingUpdate(new Date());
        int i = shoppingCartMapper.updateById(resultShoppingCart);
        if (i != 1) {
            throw new UpdateException();
        }

    }
}




