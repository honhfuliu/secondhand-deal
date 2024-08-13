package com.ziheng.deal.service.impl;

import com.ziheng.deal.common.domain.VO.UserRegisterVO;
import com.ziheng.deal.db.service.TUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TUserServiceImplTest {
    @Autowired
    TUserService userService;
    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    void test01(){
        UserRegisterVO userRegisterVO = new UserRegisterVO();
        userRegisterVO.setEmail("123@qq.com");
        userRegisterVO.setPassword("12345");
        userRegisterVO.setUsername("lllll");
        userService.RegisterUser(userRegisterVO);
    }

    @Test
    void test02() {
        redisTemplate.opsForValue().set("code1", "6666");
    }

    @Test
    void test03() {
        String s = redisTemplate.opsForValue().get("code");
        System.out.println(s );
    }

    @Test
    void test04() {
        UserRegisterVO userRegisterVO = new UserRegisterVO();
        userRegisterVO.setUsername("zihen11g");
        userRegisterVO.setPassword(".");
        String token = userService.UserLogin(userRegisterVO);
        System.out.println(token);
    }

    @Test
    void test06(){
        String password = "123456";
        String salt = "05711FB0-5564-4776-B786-D573FD88B15A";

        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
    }




}