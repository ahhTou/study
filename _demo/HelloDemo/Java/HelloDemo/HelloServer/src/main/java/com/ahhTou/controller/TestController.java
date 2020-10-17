package com.ahhtou.controller;

import com.ahhtou.exception.ReturnException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class TestController {

    @GetMapping("/t1")
    public String t1(String a, HttpServletRequest req, HttpServletResponse res) throws ReturnException {
        return "Hello Shiro1";
    }

    @GetMapping("/t2")
    public String t2(HttpServletRequest req, HttpServletResponse res) throws ReturnException {
        return "Hello Shiro2";
    }

    @GetMapping("/t3")
    @RequiresPermissions("member")
    public String t3(HttpServletRequest req, HttpServletResponse res) throws ReturnException {
        return "Hello Shiro3";

    }


}
