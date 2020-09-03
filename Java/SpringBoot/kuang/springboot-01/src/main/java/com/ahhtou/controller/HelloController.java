package com.ahhtou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/test")
    @ResponseBody
    public String hello() {
        System.out.println("12231");
        System.out.println("123s");
        return "hello";
    }
}
