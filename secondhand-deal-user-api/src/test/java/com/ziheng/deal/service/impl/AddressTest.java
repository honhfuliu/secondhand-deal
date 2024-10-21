package com.ziheng.deal.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ziheng.deal.common.domain.DTO.AddressDTO;
import com.ziheng.deal.common.domain.VO.AddressVO;
import com.ziheng.deal.db.entity.CommoditySku;
import com.ziheng.deal.db.mapper.TAddressMapper;
import com.ziheng.deal.db.service.TAddressService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class AddressTest {
    @Resource
    private TAddressService addressService;

    @Resource
    private TAddressMapper addressMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void test013(){
        Integer integer = 2;
        Integer integer1 = 3;
        System.out.println(integer1.compareTo(integer) > 0);
    }

    @Test
    void test012(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("颜色","白色");
        map.put("low_stock",20);
        map.put("尺寸",18);
        map.put("price",20);
        map.put("stock",6000);

        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("颜色","白色");
        map1.put("low_stock",20);
        map1.put("尺寸",18);
        map1.put("price",20);
        map1.put("stock",6001);
        System.out.println(map1.equals(map));

        String jsonStr = JSONUtil.toJsonStr(map);
        System.out.println(jsonStr);
        String jsonStr1 = JSONUtil.toJsonStr(map1);
        System.out.println(jsonStr1);


        System.out.println(jsonStr.equals(jsonStr1));

        System.out.println("---------------------");
        Map bean = JSONUtil.toBean(jsonStr, Map.class);
        System.out.println(bean.equals(map1));

    }

    @Test
    void test011(){
        ArrayList<HashMap<String, Object>> skus  =  new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("颜色","白色");
        map.put("low_stock",20);
        map.put("尺寸",18);
        map.put("price",20);
        map.put("stock",6000);

        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("颜色","白色");
        map1.put("low_stock",20);
        map1.put("尺寸",20);
        map1.put("price",30);
        map1.put("stock",9000);


        skus.add(map);
        skus.add(map1);
        String jsonStr = JSONUtil.toJsonStr(map1); // 将map转换为字符串
        System.out.println(jsonStr.getClass().toString());

        Map resulMap = JSONUtil.toBean(jsonStr, Map.class); // 将JSON字符串转换为map
        Set set = resulMap.keySet();
        for (Object o : set) {
            System.out.println(o);
        }
        System.out.println(resulMap.getClass().toString());



        /*
        ArrayList<CommoditySku> commoditySkus = new ArrayList<>();

        for (HashMap<String, Object> maps : skus) {
            CommoditySku commoditySku = new CommoditySku();

            commoditySku.setPrice(new BigDecimal( Integer.parseInt ( maps.get("price").toString() )));
            commoditySku.setCommodityNumber((Integer) maps.get("stock"));
            commoditySku.setLowCommodityNumber((Integer) maps.get("low_stock"));
            maps.remove("price");
            maps.remove("stock");
            maps.remove("low_stock");
            commoditySku.setCommoditySku(maps);
//                System.out.println(maps.get(s));

            commoditySkus.add(commoditySku);
        }
        for (CommoditySku sku : commoditySkus) {
            System.out.println(sku);
        }*/
    }

    @Test
    void test101(){
//        System.out.println(new Date().getTime());
        String s = "290812-1723471051111379692";
        Pattern pattern = Pattern.compile("\\d{6}-\\d+");
        Matcher matcher = pattern.matcher(s);

        if (matcher.matches()) {
            System.out.println("运行订单取消");
            // 处理过期订单逻辑
        } else {
            System.out.println("未运行订单取消");
        }
    }

    @Test
    void test09(){
        // 获取当前时间
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        // 添加15分钟
        calendar.add(Calendar.MINUTE, 15);

        // 获取新的时间
        Date newTime = calendar.getTime();

        // 将 Date 转换为 LocalDateTime
        Instant instant = newTime.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        // 格式化输出
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = localDateTime.format(formatter);
        System.out.println("格式化后的时间：" + formattedTime);
    }

    @Test
    void test08(){
        redisTemplate.opsForValue().set("rencai1", "123456", 15, TimeUnit.SECONDS);
    }

    @Test
    void test07(){
        System.out.println(12315);
    }

    @Test
    void test06(){
        String password = "123456";
        String salt = "05711FB0-5564-4776-B786-D573FD88B15A";

        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }

        System.out.println(password);
    }


    @Test
    void test03(){
        AddressVO addressVO = new AddressVO();
        addressVO.setAddressId(11);
        addressService.updateAddress(addressVO, 2);
    }


    @Test
    void test02(){
        List<AddressDTO> allAddress = addressMapper.getAllAddress(2);
        for (AddressDTO address : allAddress) {
            System.out.println(address);
        }
    }


    @Test
    void test01(){
        AddressVO addressVO = new AddressVO();
        addressVO.setAddressName("人才");
        addressVO.setCity(10001515L);
        addressVO.setContact("18869700309");

        addressService.AddShippingAddressBasedUserId(1, "人才", addressVO);
    }
}
