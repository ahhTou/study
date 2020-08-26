package com.ahhTou.controller;

import com.ahhTou.annotation.MailUnique;
import com.sun.istack.internal.NotNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/t")
public class TestController {

    @RequestMapping("/t")
    public String t1(@NotNull String a) {
        return a;
    }
}
