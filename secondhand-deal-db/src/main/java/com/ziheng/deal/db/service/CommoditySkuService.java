package com.ziheng.deal.db.service;

import com.ziheng.deal.db.entity.CommoditySku;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;

/**
* @author Administrator
* @description 针对表【commodity_sku(存储商品 SKU 及其相关信息的表)】的数据库操作Service
* @createDate 2024-08-29 14:11:33
*/
public interface CommoditySkuService extends IService<CommoditySku> {

    // 商品sku信息保存
    boolean commoditySkuSave(ArrayList<CommoditySku> commoditySkus);

}
