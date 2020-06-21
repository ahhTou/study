package com.ahhTou.service.impl;

import com.ahhTou.bean.Anime;
import com.ahhTou.dao.AnimeMapper;
import com.ahhTou.dao.AnimeTypesMapper;
import com.ahhTou.service.AnimeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimeServiceImpl implements AnimeService {

    @Resource
    AnimeMapper animeMapper;

    @Resource
    AnimeTypesMapper animeTypesMapper;

    @Override
    public Integer getHowMuchColumn() {
        return animeMapper.getHowMuchColumn();
    }

    @Override
    public List<Anime> getOnePage(Integer page) {
        return animeMapper.getOnePage(page);
    }

    @Override
    @Transactional
    public Boolean delAnimeById(Integer id) {
        Boolean aBoolean = animeMapper.delAnimeById(id);

        if (aBoolean) System.out.println("> 删除动画数据");
        else return false;

        Boolean aBoolean1 = animeTypesMapper.delAllTypesByAnimeId(id);

        if (aBoolean1) System.out.println("> 删除动画类型映射");
        else return false;

        return true;

    }
}
