package com.ahhtou.controller;


import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/err")
    public String err() {
        throw new RuntimeException("123");
    }

}
