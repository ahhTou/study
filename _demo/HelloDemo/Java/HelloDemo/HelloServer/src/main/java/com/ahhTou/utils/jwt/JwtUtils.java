package com.ahhtou.utils.jwt;

import com.ahhtou.utils.CommonConstant;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtUtils {
    // 过期时间30分钟
    public static final int EXPIRE_TIME_MIN = 30;

    // 校验token是否正确
    public static boolean verify(String token, String username, String secret) {
        try {
            // 根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            // 效验TOKEN
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 获得token中的信息无需secret解密也能获得 token中包含的用户名
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    // 生成签名,5min后过期 返回加密的token
    public static String sign(String username, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME_MIN * 60 * 1000);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create().withClaim("username", username).withExpiresAt(date).sign(algorithm);

    }

    // 根据request中的token获取用户账号
    public static String getUserNameByToken(HttpServletRequest request) throws Exception {
        String accessToken = request.getHeader(CommonConstant.ACCESS_TOKEN);
        String username = getUsername(accessToken);
        if (username == null) {
            throw new Exception("未获取到用户");
        }
        return username;
    }
}
