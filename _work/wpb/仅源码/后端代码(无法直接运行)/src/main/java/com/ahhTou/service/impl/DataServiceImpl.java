package com.ahhTou.service.impl;

import com.ahhTou.bean.User;
import com.ahhTou.dao.DataMapper;
import com.ahhTou.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DataServiceImpl implements DataService {

    private DataMapper dataMapper;
    @Autowired
    public void setDataMapper(DataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }


    @Override
    public User getBasicData(String username) {
        return dataMapper.getBasicData(username);
    }
}
