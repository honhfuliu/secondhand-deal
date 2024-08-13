package com.ziheng.deal.db.service;

import com.ziheng.deal.common.domain.DTO.CollectCommodityDTO;
import com.ziheng.deal.db.entity.CommodityCollect;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【commodity_collect(商品收藏表)】的数据库操作Service
* @createDate 2024-06-30 19:41:58
*/
public interface CommodityCollectService extends IService<CommodityCollect> {

    // 收藏商品
    void getByUserIdCollectCommodity(Integer userId, Integer commodityId);

    // 根据用户id查找用户收藏商品
    List<CollectCommodityDTO> getByUserIdCollectCommodity(Integer userId);

    // 根据收藏id和用户id来删除收藏商品
    void getByCollectIdDelete(Integer userId, Integer commodityId);

}
