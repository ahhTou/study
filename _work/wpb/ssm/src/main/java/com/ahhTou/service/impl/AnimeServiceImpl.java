package com.ahhTou.service.impl;

import com.ahhTou.bean.Anime;
import com.ahhTou.dao.AnimeMapper;
import com.ahhTou.service.AnimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class AnimeServiceImpl implements AnimeService {

    @Resource
    AnimeMapper animeMapper;


    @Override
    public Integer getHowMuchColumn() {
        return animeMapper.getHowMuchColumn();
    }

    @Override
    public ArrayList<Anime> getOnePage(Integer page) {
        return animeMapper.getOnePage(page);
    }
}
