package com.ahhtou.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(path = {"/hello/test", "test"})
    public String hello() {
        return "hello";
    }

}
