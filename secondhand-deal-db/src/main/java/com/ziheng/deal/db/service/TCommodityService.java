package com.ziheng.deal.db.service;

import com.ziheng.deal.common.domain.DTO.CommodityDTO;
import com.ziheng.deal.common.domain.DTO.PageDOT;
import com.ziheng.deal.common.domain.DTO.commodityListDTO;
import com.ziheng.deal.common.domain.TokenInfo;
import com.ziheng.deal.common.domain.VO.CommodityVO;
import com.ziheng.deal.common.domain.VO.commodityPageQueryVO;
import com.ziheng.deal.db.entity.TCommodity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【t_commodity(商品表)】的数据库操作Service
* @createDate 2024-05-27 22:18:22
*/
public interface TCommodityService extends IService<TCommodity> {
    // 商品信息保存接口
    void CommodityAdd(CommodityVO commodityVO, TokenInfo userInfo);

    // 根据用户id分页查询个人发布的商品
    PageDOT<commodityListDTO>  getByIdCommodityPageQuery(commodityPageQueryVO commodityPageQuery, Integer userId);

    // 根据商品id查询商品信息，并返回
    CommodityDTO getByIdCommodity(Integer commodityId);
}
