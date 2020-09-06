package com.ahhTou.service.impl;

import com.ahhTou.service.BaseService;
import com.ahhTou.utils.MyObjectMapper;
import com.ahhTou.utils.MyRedis;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BaseServiceImpl implements BaseService {

    @Resource
    MyRedis myRedis;

    @Resource
    MyObjectMapper myObjectMapper;

    final String BG_IMAGES_KEY_NAME = "BgImagesForHelloProject";


    @Override
    public void saveBgImages(Object urls) {
        myRedis.set4String(BG_IMAGES_KEY_NAME, myObjectMapper.obj2String(urls));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getBgImages() {
        String str = (String) myRedis.get(BG_IMAGES_KEY_NAME);
        return myObjectMapper.string2Obj(str, List.class);
    }
}
