package com.ahhtou.service.impl;

import com.ahhtou.bean.BgList;
import com.ahhtou.service.BaseService;
import com.ahhtou.utils.json.ObjectMapperUtils;
import com.ahhtou.utils.RedisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BaseServiceImpl implements BaseService {

    @Resource
    RedisUtils redisUtils;

    @Resource
    ObjectMapperUtils objectMapperUtils;


    @Override
    public void saveBgImages(Object urls) {
        redisUtils.set(BgList.REDIS_SAVE_KEY, objectMapperUtils.obj2String(urls));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getBgImages() {
        String str = (String) redisUtils.getString(BgList.REDIS_SAVE_KEY);
        return objectMapperUtils.string2Obj(str, List.class);
    }
}
