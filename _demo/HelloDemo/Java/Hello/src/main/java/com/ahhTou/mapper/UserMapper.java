package com.ahhTou.mapper;


import com.ahhTou.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<String> checkUsernameUnique(String username);

    List<String> checkEmailUnique(String email);

    Integer register(User user);

    User getUserMsgByUsername(String username);

    List<User> getUserByLogin(User user);

    List<User> getUserByUsername(String username);
}
