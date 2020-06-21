package com.ahhTou.service.impl;


import com.ahhTou.bean.User;
import com.ahhTou.dao.LoginMapper;
import com.ahhTou.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    LoginMapper loginMapper;

    @Override
    public User loginCheck(User user) {
        return loginMapper.loginCheck(user);
    }

    @Override
    public void setToken(User user) {
        loginMapper.setToken(user);
    }
}
