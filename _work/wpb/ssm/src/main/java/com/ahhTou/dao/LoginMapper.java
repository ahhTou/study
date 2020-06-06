package com.ahhTou.dao;

import com.ahhTou.bean.User;

public interface LoginMapper {


    User loginCheck(User user);

    void setToken(User user);


}
