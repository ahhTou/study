package com.ahhTou.dao;

import com.ahhTou.bean.User;
import org.springframework.stereotype.Repository;

public interface LoginMapper {


    User loginCheck(User user);

    void setToken(User user);


}
