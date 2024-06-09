package com.ziheng.deal.db.mapper;

import com.ziheng.deal.common.domain.DTO.commodityListDTO;
import com.ziheng.deal.common.domain.VO.commodityPageQueryVO;
import com.ziheng.deal.db.entity.TCommodity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【t_commodity(商品表)】的数据库操作Mapper
* @createDate 2024-05-27 22:18:22
* @Entity com.ziheng.deal.entity.TCommodity
*/
public interface TCommodityMapper extends BaseMapper<TCommodity> {

    //分页商品条件查询接口
    List<commodityListDTO> getByIdPageCommodity(@Param("commodityPageQueryVO") commodityPageQueryVO commodityPageQueryVO, @Param("userId") Integer userId);



}




