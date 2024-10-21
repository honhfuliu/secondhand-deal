package com.ziheng.deal.db.mapper;

import com.ziheng.deal.db.entity.CommoditySku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【commodity_sku(存储商品 SKU 及其相关信息的表)】的数据库操作Mapper
* @createDate 2024-08-29 14:11:33
* @Entity com.ziheng.deal.entity.CommoditySku
*/
public interface CommoditySkuMapper extends BaseMapper<CommoditySku> {

    // 根据商品分页的信息查询商品的价格
    List<CommoditySku> getCommoditySkuPrice(@Param("commodityIds") List<Integer> commodityIds);

}




