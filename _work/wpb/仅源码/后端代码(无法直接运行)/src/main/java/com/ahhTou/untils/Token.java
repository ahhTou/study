package com.ahhTou.untils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Token {

    //过期时间
    private static final long EXPIRE_TIME = 15 * 60 * 1000;

    //私钥
    private static final String TOKEN_SECRET = "ahhTou";

    static public String create(String username) {
        try {
            System.out.println("> 生成Token");
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("> 生成Token异常");
            return null;
        }
    }


    public static String verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("> 校对结束");
            return jwt.getClaim("username").asString();
        } catch (Exception e) {
            System.out.println("> 校对异常");
            return null;
        }
    }
}


