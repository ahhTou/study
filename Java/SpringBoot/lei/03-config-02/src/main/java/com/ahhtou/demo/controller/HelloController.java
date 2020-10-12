package com.ahhtou.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 世界上最完美的字体
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "<h1>hello</h1>";
    }

}
