package com.xhr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {
    @RequestMapping("/app")
    public void ttTest() {
        System.out.println("访问我了");
    }
}
