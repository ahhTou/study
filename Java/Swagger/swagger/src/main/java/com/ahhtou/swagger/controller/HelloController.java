package com.ahhtou.swagger.controller;

import com.ahhtou.swagger.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @ApiOperation("你好控制类")
    @GetMapping(path = {"/hello"})
    public String hello() {
        return "hello swagger";
    }

    @GetMapping(path = {"/user"})
    public User user() {
        User user = new User();
        user.pwd = "123";
        user.username = "ahhTou";

        return user;
    }

}
