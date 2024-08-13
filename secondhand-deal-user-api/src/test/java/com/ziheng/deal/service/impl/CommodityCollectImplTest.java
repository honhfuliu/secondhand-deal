package com.ziheng.deal.service.impl;


import com.ziheng.deal.common.domain.DTO.CollectCommodityDTO;
import com.ziheng.deal.db.service.CommodityCollectService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CommodityCollectImplTest {
    @Resource
    CommodityCollectService commodityCollectService;

    @Test
    void test02(){
        List<CollectCommodityDTO> byUserIdCollectCommodity = commodityCollectService.getByUserIdCollectCommodity(2);
        for (CollectCommodityDTO dto : byUserIdCollectCommodity) {
            System.out.println(dto);
        }
    }

    @Test
    void test01(){
        commodityCollectService.getByUserIdCollectCommodity(2, 1);
    }
}
