package com.ziheng.deal.service.impl;

import com.ziheng.deal.common.domain.DTO.MyOrderDTO;
import com.ziheng.deal.common.domain.DTO.PageDOT;
import com.ziheng.deal.common.domain.VO.MyOrderVO;
import com.ziheng.deal.common.domain.VO.ShoppingCartCommoditySettlementVO;
import com.ziheng.deal.db.service.TOrderService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class OrderTest {
    @Resource
    private TOrderService orderService;

    @Test
    void tes04(){
        MyOrderVO orderVO = new MyOrderVO();
        orderVO.setPageNo(1);
        orderVO.setPageSize(5);
        PageDOT<List<MyOrderDTO>> listPageDOT = orderService.showMyOrder(orderVO, 2);
        System.out.println(listPageDOT.getTotal());
        for (List<MyOrderDTO> dtos : listPageDOT.getList()) {
            System.out.println(dtos);
        }
    }

    @Test
    void test03(){
        Integer a = 1000; // 积分
        float i = (float) a / 1000; // 减免金额
        System.out.println(i / 3);
        System.out.println(i % 3);
    }


    @Test
    void test02(){
        List<ShoppingCartCommoditySettlementVO> list = new ArrayList<>();
        ShoppingCartCommoditySettlementVO commoditySettlement = new ShoppingCartCommoditySettlementVO();
        commoditySettlement.setCommodityId(3);
        commoditySettlement.setBuyQuantity(2);
        commoditySettlement.setShoppingCartId(1);
        commoditySettlement.setUnitPrice(BigDecimal.valueOf(1.00));
        commoditySettlement.setTotalPrice(BigDecimal.valueOf(2.00));
        list.add(commoditySettlement);
        ShoppingCartCommoditySettlementVO commoditySettlement1 = new ShoppingCartCommoditySettlementVO();
        commoditySettlement1.setCommodityId(8);
        commoditySettlement1.setBuyQuantity(2);
        commoditySettlement1.setShoppingCartId(2);
        commoditySettlement1.setUnitPrice(BigDecimal.valueOf(1.00));
        commoditySettlement1.setTotalPrice(BigDecimal.valueOf(2.00));
        list.add(commoditySettlement1);
        orderService.ShoppingCartCommoditySettlement(list, 2, "ziheng");
    }

    @Test
    void test01(){
        SimpleDateFormat dateFormat  = new SimpleDateFormat("yyMMdd");
        String day = dateFormat .format(new Date());
        System.out.println(day);
        System.out.println(new Date().getTime());
        Random random = new Random();
        int i = random.nextInt(900000) + 100000;
        System.out.println(day + '-' + new Date().getTime() + i);

    }
}
