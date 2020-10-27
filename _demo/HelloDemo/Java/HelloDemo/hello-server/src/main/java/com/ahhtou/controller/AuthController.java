package com.ahhtou.controller;

import com.ahhtou.common.pojo.HelloResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("checkLogin")
    public HelloResult checkLogin() {
        return new HelloResult(true, "登陆成功，token有效");
    }

}
