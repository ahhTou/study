package com.ahhtou.utils.jwt;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class JwtErr {

    public static final String JWT_ERR_KEY = "JWT_ERR_KEY";

    public static final String JWT_EXPIRE = "Token 失效或者不存在";

    public static final String TOKEN_IS_EMPTY = "Token 不存在";

    public static final String TOKEN_IS_NOT_EXIST = "Token 对照失败";

    public static final String TOKEN_NOT_FIND_SOMETHING = "Token 无法得到指定的信息";

    public static final String TOKEN_ERR = "Token 并不正确,可能是过期";

    public JwtErr(ServletRequest request, ServletResponse response) {

    }
}
