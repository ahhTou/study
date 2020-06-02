package com.xhr.dao;


import com.xhr.bean.UserBaseData;
import org.apache.ibatis.annotations.Param;


public interface UserMapper {

    UserBaseData getUserBaseDataByUsername(String Username);

    UserBaseData checkLoginByUsernameAndPassword(@Param("username") String username, @Param("password") String password);


}
