package com.ahhTou.service;


import com.ahhTou.bean.User;

public interface UserService {

    Boolean checkUsernameUnique(String username);

    Boolean checkEmailUnique(String email);

    Boolean register(User user);

    Object getUserMsgByUsername(String username);

}
