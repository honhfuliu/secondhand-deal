package com.ziheng.deal.service;

import com.ziheng.deal.common.domain.DTO.CommodityDTO;
import com.ziheng.deal.common.domain.DTO.PageDOT;
import com.ziheng.deal.common.domain.DTO.commodityDisplayDTO;
import com.ziheng.deal.common.domain.DTO.commodityListDTO;
import com.ziheng.deal.common.domain.TokenInfo;
import com.ziheng.deal.common.domain.VO.CommodityVO;
import com.ziheng.deal.common.domain.VO.commodityPageQueryVO;
import com.ziheng.deal.common.domain.VO.commoditySearchPageVO;
import com.ziheng.deal.common.domain.query.PageQueryBase;
import com.ziheng.deal.db.mapper.TCommodityMapper;
import com.ziheng.deal.db.service.TCommodityService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class TCommodityServiceImplTest {
    @Resource
    TCommodityService commodityService;

    @Resource
    TCommodityMapper commodityMapper;


    @Test
    void test11(){
        commoditySearchPageVO vo = new commoditySearchPageVO();
        vo.setSearchName("男装");
        vo.setPageNo(1);
        vo.setPageSize(40);
        vo.setSortBy("6");
        PageDOT<commodityDisplayDTO> dtoList = commodityService.searchCommodity(vo);
        for (commodityDisplayDTO dto : dtoList.getList()) {
            System.out.println(dto);
        }
    }

    @Test
    void test10(){
        commoditySearchPageVO vo = new commoditySearchPageVO();
        vo.setSearchName("暗影");
        List<commodityDisplayDTO> commodityDisplayDTOS = commodityMapper.searchCommodity(vo, null);
        for (commodityDisplayDTO dto : commodityDisplayDTOS) {
            System.out.println(dto);
        }
    }

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
        System.out.println(byIdCommodityPageQuery);
    }

    @Test
    void test03(){
        commodityService.getByIdCommodity(6);
    }


    @Test
    void test04(){
        CommodityVO c =  new CommodityVO();
        c.setCId(8);
        TokenInfo t =  new TokenInfo();
        t.setUId(2);
        commodityService.getByCommodityIdUpdate(c, t);
    }

    @Test
    void test05(){
        commodityService.getByIdDeleteCommodity(1, 2);
    }

    @Test
    void test06(){
        PageQueryBase pageQueryBase = new PageQueryBase();
        pageQueryBase.setPageNo(1);
        pageQueryBase.setPageSize(5);
        PageDOT<commodityListDTO> dot = commodityService.queryGetPaginationCommodity(pageQueryBase);
        for (commodityListDTO a : dot.getList()) {
            System.out.println(a);
        }
    }

    @Test
    void test07(){
        commodityService.getByIdDetailsCommodity(9);
    }


    @Test
    void test08(){
        List<commodityDisplayDTO> commodity = commodityService.getCommodity();
        System.out.println(commodity);
    }

    @Test
    void test09(){
        CommodityDTO byIdDetailsInfo = commodityService.getByIdDetailsInfo(6);
        System.out.println(byIdDetailsInfo);
    }


}