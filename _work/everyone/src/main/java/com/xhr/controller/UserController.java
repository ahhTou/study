package com.xhr.controller;


import com.xhr.bean.UserBaseData;
import com.xhr.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller("UserController")
@RequestMapping("/user")
public class UserController {

    UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @RequestMapping("/getUserBaseDataByUsername")
    public @ResponseBody
    UserBaseData getUser(@RequestBody String username) {
        System.out.println("\r\n> 请求获取用户基本数据" + username);
        UserBaseData userBaseDataByUsername = userMapper.getUserBaseDataByUsername(username);
        System.out.println("> 返回所有数据");
        return userBaseDataByUsername;
    }


}
