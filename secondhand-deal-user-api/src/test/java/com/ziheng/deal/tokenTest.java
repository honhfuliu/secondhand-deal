package com.ziheng.deal;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ziheng.deal.common.domain.TokenInfo;
import com.ziheng.deal.common.util.TokenUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.security.interfaces.RSAPublicKey;
import java.util.Calendar;
import java.util.UUID;

@SpringBootTest
public class tokenTest {
    @Test
    void test01(){
        // 获取当前时间的Calendar实例
        Calendar instance = Calendar.getInstance();

        // 将时间向后推移两个小时
        instance.add(Calendar.SECOND, 1000);
        String key = "zxcvn ,mnbvc";
        String token = JWT.create()
                .withClaim("username", "张三")
                .withExpiresAt(instance.getTime())
//                .withIssuer("auth0")
                .sign(Algorithm.HMAC256(key));
        System.out.println(token);
    }

    @Test
    void test02(){
        String key = "zxcvn ,mnbvc";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MTMxODk0NzAsInVzZXJuYW1lIjoi5byg5LiJIn0.i_uPyMNz76e0NByJqh9oHuO9ZkQ3bmfB_nA0BrE22B0";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(key)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        System.out.println(decodedJWT.getClaim("username").asString());
        System.out.println("过期时间: "+decodedJWT.getExpiresAt());

    }

    @Resource
    TokenUtil tokenUtil;
    @Test
    void test03(){
        TokenInfo info = new TokenInfo();
        info.setUsername("ziheng");
        info.setUId(1);
        String token = tokenUtil.createToken(info);
        System.out.println(token);
    }

    @Test
    void test04(){
        TokenInfo tokenTuser = tokenUtil.getTokenTuser("eyyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjEsImV4cCI6MTcxMzIwMDA1MywidXNlcm5hbWUiOiJ6aWhlbmcifQ.l3OuPOp8TZ0-6AZVkEWWNFh71fLxJKzwMzswpjdYp54");
        System.out.println(tokenTuser.toString());
    }

    @Test
    void test05() {
        String salt = UUID.randomUUID().toString().toUpperCase();
        String password = "admin123";
        // md5加密算法方法调用（进行三次加密）
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        System.out.println(salt);
        System.out.println(password);

    }


}
