package com.ahhtou.service;


import com.ahhtou.bean.User;
import com.ahhtou.exception.ReturnException;


public interface UserService {

    Boolean checkUsernameUnique(String username);

    Boolean checkEmailUnique(String email);


    User getUserMsgByUsername(String username) throws ReturnException;


    Boolean register(User user) throws ReturnException;

    User loginAndBackUser(User user) throws ReturnException;

    User getUserByUsername(String username) throws ReturnException;

}
