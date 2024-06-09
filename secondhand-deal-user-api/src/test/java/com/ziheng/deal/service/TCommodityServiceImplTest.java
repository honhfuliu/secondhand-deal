package com.ziheng.deal.service;

import com.ziheng.deal.common.domain.DTO.PageDOT;
import com.ziheng.deal.common.domain.DTO.commodityListDTO;
import com.ziheng.deal.common.domain.TokenInfo;
import com.ziheng.deal.common.domain.VO.CommodityVO;
import com.ziheng.deal.common.domain.VO.commodityPageQueryVO;
import com.ziheng.deal.db.mapper.TCommodityMapper;
import com.ziheng.deal.db.service.TCommodityService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TCommodityServiceImplTest {
    @Resource
    TCommodityService commodityService;

    @Resource
    TCommodityMapper commodityMapper;


    @Test
    void test01(){
        CommodityVO commodityVO = new CommodityVO();
        commodityVO.setCommodityTitle("123456");
        TokenInfo info = new TokenInfo();
        commodityService.CommodityAdd(commodityVO, info);
    }

    @Test
    void test02(){
        commodityPageQueryVO c = new commodityPageQueryVO();
        c.setPageNo(0);
        c.setPageSize(5);
        c.setStatus(0);
//        c.setCommodityTitle("暗");
//        c.setSortBy("c_price");
//        List<commodityDTO> byIdPageCommodity = commodityMapper.getByIdPageCommodity(c, 2);
//        System.out.println(commodityMapper.getByIdPageWhereCount(c, 2));
//
//        for (commodityDTO c1 : byIdPageCommodity) {
//            System.out.println(c1);
//        }
        PageDOT byIdCommodityPageQuery = commodityService.getByIdCommodityPageQuery(c, 2);
    }

    @Test
    void test03(){
        commodityService.getByIdCommodity(6);
    }


}