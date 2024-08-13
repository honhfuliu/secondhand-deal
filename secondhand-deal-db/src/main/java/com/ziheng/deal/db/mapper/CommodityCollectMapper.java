package com.ziheng.deal.db.mapper;

import com.ziheng.deal.common.domain.DTO.CollectCommodityDTO;
import com.ziheng.deal.db.entity.CommodityCollect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【commodity_collect(商品收藏表)】的数据库操作Mapper
* @createDate 2024-06-30 19:41:58
* @Entity com.ziheng.deal.entity.CommodityCollect
*/
public interface CommodityCollectMapper extends BaseMapper<CommodityCollect> {

    // 根据用户id收藏商品查询
    List<CollectCommodityDTO> getByUserIdCollectCommodity(@Param("id") Integer id);

}




