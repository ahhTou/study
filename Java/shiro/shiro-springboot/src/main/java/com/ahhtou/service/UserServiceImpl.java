package com.ahhtou.service;

import com.ahhtou.mapper.UserMapper;
import com.ahhtou.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;


    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }
}
