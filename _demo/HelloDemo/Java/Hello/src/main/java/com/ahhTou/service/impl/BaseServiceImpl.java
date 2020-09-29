package com.ahhTou.service.impl;

import com.ahhTou.bean.BgList;
import com.ahhTou.service.BaseService;
import com.ahhTou.utils.json.ObjectMapperUtils;
import com.ahhTou.utils.RedisUtils;
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
