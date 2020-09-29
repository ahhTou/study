package com.ahhTou.filter;

import com.ahhTou.utils.jwt.JwtToken;
import com.ahhTou.utils.var.CommonConstant;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JwtFilter extends BasicHttpAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        System.out.println("Shiro:JwtFilter -> 执行了JwtFilter");
        try {
            executeLogin(request, response);
            System.out.println("Shiro:JwtFilter -> JwtFilter 完成了生命周期");
            return true;
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", 403);
            map.put("message", e.getMessage());
            request.setAttribute("ext", map);
            request.setAttribute("javax.servlet.error.status_code", 403);
            e.printStackTrace();
            throw new AuthenticationException(e.getMessage());
        }
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        // 从头部取出 token
        String token = httpServletRequest.getHeader(CommonConstant.ACCESS_TOKEN);


        JwtToken jwtToken = new JwtToken(token);

        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);

        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
