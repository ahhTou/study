package com.ahhTou.controller;

import com.sun.istack.internal.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/t")
public class TestController {

    @PostMapping("/t")
    public String t1(@NotNull String a) {
        return a;
    }
}
