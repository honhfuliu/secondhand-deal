package com.ziheng.deal.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ziheng.deal.common.domain.TokenInfo;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class TokenUtil {

    private static final String SIGNATURE = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MTMxODk0NzAsInVzZXJuYW1lIjoi5byg5LiJIn0.i_uPyMNz76e0NByJqh9oHuO9ZkQ3bmfB_nA0BrE22B0";

    // 生成token
    public String createToken(TokenInfo info){
        // 设置token的有效时间
        // 获取当前时间的Calendar实例
        Calendar instance = Calendar.getInstance();
        // 将时间向后推移两个小时
        instance.add(Calendar.HOUR_OF_DAY, 2);

        String token = JWT.create()
                .withClaim("username", info.getUsername())
                .withClaim("uid", info.getUId())
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGNATURE));
        return token;
    }

    // 获取用户信息
    public TokenInfo getTokenTuser(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SIGNATURE)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        TokenInfo info = new TokenInfo();
        info.setUsername(decodedJWT.getClaim("username").asString());
        info.setUId(decodedJWT.getClaim("uid").asInt());
        return info;
    }

    // token 验证
    public boolean verifyToken(String token) {
        return true;
    }
}
