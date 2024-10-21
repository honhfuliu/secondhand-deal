package com.ziheng.deal.db.service;

import com.ziheng.deal.common.domain.DTO.CommodityDTO;
import com.ziheng.deal.common.domain.DTO.PageDOT;
import com.ziheng.deal.common.domain.DTO.commodityDisplayDTO;
import com.ziheng.deal.common.domain.DTO.commodityListDTO;
import com.ziheng.deal.common.domain.TokenInfo;
import com.ziheng.deal.common.domain.VO.CommodityVO;
import com.ziheng.deal.common.domain.VO.CommodityVerifyVO;
import com.ziheng.deal.common.domain.VO.commodityPageQueryVO;
import com.ziheng.deal.common.domain.VO.commoditySearchPageVO;
import com.ziheng.deal.common.domain.query.PageQueryBase;
import com.ziheng.deal.db.entity.TCommodity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【t_commodity(商品表)】的数据库操作Service
* @createDate 2024-05-27 22:18:22
*/
public interface TCommodityService extends IService<TCommodity> {

    /*用户相关接口*/
    // 商品信息保存接口
    void CommodityAdd(CommodityVO commodityVO, TokenInfo userInfo);

    // 根据用户id分页查询个人发布的商品
    PageDOT<commodityListDTO>  getByIdCommodityPageQuery(commodityPageQueryVO commodityPageQuery, Integer userId);

    // 根据商品id查询商品信息，并返回
    CommodityDTO getByIdCommodity(Integer commodityId);

    // 根据商品id修改商品
    void getByCommodityIdUpdate(CommodityVO commodityVO, TokenInfo userInfo);

    // 根据商品id删除商品
    void getByIdDeleteCommodity(Integer commodityId, Integer userId);

    // 下架商品
    void removedFromShelvesCommodity(Integer commodityId, Integer userId);

    // 上架商品
    void onshelvesCommodity(Integer commodityId, Integer userId);


    // 随机获取到40条数据用于首页商品展示
    List<commodityDisplayDTO> getCommodity();

    // 根据id查询商品的详情
    CommodityDTO getByIdDetailsInfo(Integer commodityId);

    // 商品搜素
    PageDOT<commodityDisplayDTO> searchCommodity(commoditySearchPageVO commoditySearch);




    /*管理员相关接口*/
    // 分页获取到所有未审核的商品
    PageDOT<commodityListDTO> queryGetPaginationCommodity(PageQueryBase pageQuery);

    // 根据商品id获取到未审核的商品详情信息
    CommodityDTO getByIdDetailsCommodity(Integer commodityId);

    // 商品审核提交
    void getByIdVerifyCommoditySbumit(CommodityVerifyVO commodityVerifyVO, String updateName);


}
