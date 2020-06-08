package com.ahhTou.dao;

import com.ahhTou.bean.User;
import org.springframework.stereotype.Repository;

public interface DataMapper {

    User getBasicData(String username);

}
