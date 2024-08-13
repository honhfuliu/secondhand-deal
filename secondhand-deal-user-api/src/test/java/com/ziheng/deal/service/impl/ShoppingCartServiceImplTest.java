package com.ziheng.deal.service.impl;

import com.ziheng.deal.common.domain.DTO.shoppingCartDTO;
import com.ziheng.deal.common.domain.VO.ShoppingCartVO;
import com.ziheng.deal.db.service.ShoppingCartService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ShoppingCartServiceImplTest {
    @Resource
    private ShoppingCartService shoppingCartService;

    @Test
    void test02(){
        List<shoppingCartDTO> list = shoppingCartService.getByUserIdShoppingCart(2);
        for (shoppingCartDTO dto : list) {
            System.out.println(dto);
        }
    }


    @Test
    void test01(){
        ShoppingCartVO vo = new ShoppingCartVO();
        vo.setShoppingNumber(4);
        vo.setCommodityId(3);

        shoppingCartService.getByUserIdAddShoppingCart(vo, 2);
    }
}
