package com.ahhtou.nosql.service;

import com.ahhtou.common.pojo.BgList;

public interface SearchRedis {

    void saveBgList(BgList bgList);

    BgList getBgList();


}
