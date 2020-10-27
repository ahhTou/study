package com.ahhtou.exception;

import com.ahhtou.common.pojo.ReturnException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class HelloExceptionHandler {

    @ExceptionHandler(value = ReturnException.class)
    public String handler1(HttpServletResponse res, HttpServletRequest req, ReturnException e) throws Exception {
        e.printStackTrace();

        req.setCharacterEncoding("utf-8");
        res.setCharacterEncoding("utf-8");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", e.getCode());
        map.put("message", e.getMsg());
        req.setAttribute("javax.servlet.error.status_code", e.getCode());

        //将自己的异常信息加入到request
        req.setAttribute("ext", map);
        req.setCharacterEncoding("utf-8");
        return "forward:/error";

    }

    @ExceptionHandler(value = Exception.class)
    public String handler2(HttpServletResponse res, HttpServletRequest req, Exception e) throws Exception {
        e.printStackTrace();

        req.setCharacterEncoding("utf-8");
        res.setCharacterEncoding("utf-8");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", "服务器发生错误");
        map.put("message", e.getMessage());
        req.setAttribute("javax.servlet.error.status_code", 500);

        //将自己的异常信息加入到request
        req.setAttribute("ext", map);
        req.setCharacterEncoding("utf-8");
        return "forward:/error";

    }

}
