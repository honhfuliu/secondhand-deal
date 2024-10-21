package com.ziheng.deal.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ziheng.deal.db.entity.CommoditySku;
import com.ziheng.deal.db.service.CommoditySkuService;
import com.ziheng.deal.db.mapper.CommoditySkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
* @author Administrator
* @description 针对表【commodity_sku(存储商品 SKU 及其相关信息的表)】的数据库操作Service实现
* @createDate 2024-08-29 14:11:33
*/
@Service
public class CommoditySkuServiceImpl extends ServiceImpl<CommoditySkuMapper, CommoditySku>
    implements CommoditySkuService{

    @Autowired
    private CommoditySkuMapper commoditySkuMapper;

    /**
     * 商品sku信息存储
     * @param commoditySkus 商品sku信息
     * @return true false
     */
    @Override
    public boolean commoditySkuSave(ArrayList<CommoditySku> commoditySkus) {
        return saveBatch(commoditySkus);

    }
}




