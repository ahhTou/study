package com.ahhtou.dao.mapper;


import com.ahhtou.common.pojo.User;

import java.util.List;

public interface UserMapper {
    List<String> checkUsernameUnique(String username);

    List<String> checkEmailUnique(String email);

    Integer register(User user);

    User getUserMsgByUsername(String username);

    List<User> getUserByLogin(User user);

    List<User> getUserByUsername(String username);

}
