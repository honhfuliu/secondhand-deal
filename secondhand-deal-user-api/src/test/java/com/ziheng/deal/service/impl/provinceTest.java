package com.ziheng.deal.service.impl;

import com.ziheng.deal.common.domain.DTO.provinceDTO;
import com.ziheng.deal.db.mapper.ProvinceMapper;
import com.ziheng.deal.db.service.ProvinceService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class provinceTest {

    @Resource
    private ProvinceMapper provinceMapper;

    @Resource
    private ProvinceService provinceService;


    @Test
    void test03(){
        int bitOffset = LocalDate.now().getDayOfYear();
        System.out.println(bitOffset);
        // 当前时间
        LocalDateTime now = LocalDateTime.now();

        // 今天的午夜时间
        LocalDateTime midnight = now.toLocalDate().atStartOfDay().plusDays(1);

        // 计算时间差
        Duration duration = Duration.between(now, midnight);
        long secondsUntilMidnight = duration.getSeconds();

        System.out.println("Seconds until midnight: " + secondsUntilMidnight);
    }


    @Test
    void test02(){
        List<provinceDTO> city = provinceMapper.getProvinceCodeAllCity(530000000000L);
        for (provinceDTO dto : city) {
            System.out.println(dto);
        }
    }

    @Test
    void test01(){
        List<provinceDTO> allProvince = provinceMapper.getAllProvince();
        for (provinceDTO dto : allProvince) {
            System.out.println(dto);
        }
    }
}
