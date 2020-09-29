package com.ahhtou.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {
    //不能自适应
/*    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Map<String, Object> handler(Exception e) {
        e.printStackTrace();
        Map<String, Object> map = new HashMap<>();
        map.put("code", "888");
        map.put("message", e.getMessage());
        return map;
    }*/
    @ExceptionHandler(Exception.class)
    public String handler(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        Map<String, Object> map = new HashMap<>();
        // 根据底层，需要传入自适应的状态码
        request.setAttribute("javax.servlet.error.status_code", 404);
        map.put("code", "888");
        map.put("message", e.getMessage());
        request.setAttribute("ext", map);
        // 转发到error，springboot的底层才能自适应返回
        return "forward:/error";
    }
}
