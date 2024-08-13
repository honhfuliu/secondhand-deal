package com.ziheng.deal.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ziheng.deal.common.domain.DTO.CollectCommodityDTO;
import com.ziheng.deal.common.ex.customizeErorr.serviceErorr.*;
import com.ziheng.deal.db.entity.CommodityCollect;
import com.ziheng.deal.db.entity.TCommodity;
import com.ziheng.deal.db.mapper.TCommodityMapper;
import com.ziheng.deal.db.service.CommodityCollectService;
import com.ziheng.deal.db.mapper.CommodityCollectMapper;
import com.ziheng.deal.db.service.TCommodityService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author Administrator
* @description 针对表【commodity_collect(商品收藏表)】的数据库操作Service实现
* @createDate 2024-06-30 19:41:58
*/
@Service
public class CommodityCollectServiceImpl extends ServiceImpl<CommodityCollectMapper, CommodityCollect>
    implements CommodityCollectService{
    @Resource
    private CommodityCollectMapper commodityCollectMapper;

    @Resource
    private TCommodityMapper commodityMapper;

    /**
     * 根据商品的id和用户id收藏商品
     * @param userId 用户id
     * @param commodityId 商品id
     */
    @Override
    public void getByUserIdCollectCommodity(Integer userId, Integer commodityId) {
        if (commodityId == null) {
            throw new CollectCommodityIdNotNull("收藏的商品id不能为空");
        }

        // 判断商品是否存在
        TCommodity tCommodity = commodityMapper.selectById(commodityId);
        if (tCommodity == null) {
            throw new CommodityDoesNotExistException();
        }


        // 先根据用户id和商品id查询该商品是否已经被收藏
        CommodityCollect result = lambdaQuery()
                .eq(CommodityCollect::getCommodityId, commodityId)
                .eq(CommodityCollect::getIsDelete, 0)
                .eq(CommodityCollect::getUserId, userId)
                .one();
        if (result != null) {
            throw new CollectCommodityExisted("收藏商品已存在");
        }

        // 补全数据信息
        CommodityCollect c = new CommodityCollect();
        c.setUserId(userId);
        c.setCommodityId(commodityId);
        c.setCollectTime(new Date());
        c.setIsDelete(0);

        // 添加到数据库中
        int insert = commodityCollectMapper.insert(c);
        if (insert != 1) {
            throw new InsertException();
        }

    }

    /**
     * 根据用户id查询出用户收藏的商品
     * @param userId 用户id
     * @return 收藏商品数据
     */
    @Override
    public List<CollectCommodityDTO> getByUserIdCollectCommodity(Integer userId) {
        List<CollectCommodityDTO> byUserIdCollectCommodity = commodityCollectMapper.getByUserIdCollectCommodity(userId);
        return byUserIdCollectCommodity;
    }

    /**
     * 根据用户id和收藏商品id来删除商品
     * @param userId 用户id
     * @param commodityId 收藏商品id
     */
    @Override
    public void getByCollectIdDelete(Integer userId, Integer commodityId) {
        if (commodityId == null) {
            throw new CollectCommodityIdNotNull("收藏的商品id不能为空");
        }
        CommodityCollect result = lambdaQuery()
                .eq(CommodityCollect::getCommodityId, commodityId)
                .eq(CommodityCollect::getUserId, userId)
                .one();

        if (result == null) {
            throw new CollectCommodityNotExisted("收藏商品不存在");
        }

        result.setIsDelete(1);
        int i = commodityCollectMapper.updateById(result);
        if (i != 1) {
            throw new InsertException();
        }
    }
}




