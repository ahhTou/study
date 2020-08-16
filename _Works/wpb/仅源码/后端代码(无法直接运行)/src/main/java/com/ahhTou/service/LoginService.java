package com.ahhTou.service;

import com.ahhTou.bean.User;

public interface LoginService {

    User loginCheck(User user);

    void setToken(User user);


}
