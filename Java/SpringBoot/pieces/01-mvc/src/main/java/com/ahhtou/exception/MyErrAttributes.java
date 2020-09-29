package com.ahhtou.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class MyErrAttributes extends DefaultErrorAttributes {
    // 继承以替代增强原来的DefaultErrorAttributes
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, options);
        map.put("ahhTou", "Pro");
        map.put("data", webRequest.getAttribute("ext", 0));
        return map;
    }
}
