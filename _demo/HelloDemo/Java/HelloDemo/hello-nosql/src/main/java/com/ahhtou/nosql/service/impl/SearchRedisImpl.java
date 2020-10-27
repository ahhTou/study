package com.ahhtou.nosql.service.impl;

import com.ahhtou.common.pojo.BgList;
import com.ahhtou.common.utils.CommonConstant;
import com.ahhtou.common.utils.OmUtils;
import com.ahhtou.nosql.config.RedisUtils;
import com.ahhtou.nosql.service.SearchRedis;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SearchRedisImpl implements SearchRedis {

    @Resource
    RedisUtils redisUtils;

    @Resource
    OmUtils omUtils;

    @Override
    public void saveBgList(BgList bgList) {
        String s = omUtils.obj2String(bgList);
        redisUtils.set(CommonConstant.REDIS_SAVE_KEY_BG_LIST, s);
    }

    @Override
    public BgList getBgList() {
        String s = redisUtils.getForString(CommonConstant.REDIS_SAVE_KEY_BG_LIST);
        return omUtils.string2Obj(s, BgList.class);
    }
}
