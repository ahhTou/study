package com.ahhTou.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class HelloErrorAttributes extends DefaultErrorAttributes {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        return super.resolveException(request, response, handler, ex);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {

        Map<String, Object> map = super.getErrorAttributes(webRequest, options);
        Map<String, Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", 0);
        map.put("data", ext);
        map.put("message", "出现错误");
        map.put("success", false);
        return map;
    }
}
