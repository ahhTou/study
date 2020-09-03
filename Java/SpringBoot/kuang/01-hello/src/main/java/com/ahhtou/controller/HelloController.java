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
        System.out.println("12qwewq12e31");
        System.out.println("1222fdf3dsfs");
        return "hello";
    }
}
