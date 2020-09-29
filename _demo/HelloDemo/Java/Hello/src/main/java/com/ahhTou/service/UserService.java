package com.ahhTou.service;


import com.ahhTou.bean.User;
import com.ahhTou.exception.ReturnException;


public interface UserService {

    Boolean checkUsernameUnique(String username);

    Boolean checkEmailUnique(String email);


    Object getUserMsgByUsername(String username);


    Boolean register(User user);

    User loginAndBackUser(User user) throws ReturnException;

    User getUserByUsername(String username) throws ReturnException;

}
