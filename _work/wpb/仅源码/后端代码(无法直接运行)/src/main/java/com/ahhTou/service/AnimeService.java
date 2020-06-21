package com.ahhTou.service;

import com.ahhTou.bean.Anime;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface AnimeService {

    Integer getHowMuchColumn();

    List<Anime> getOnePage(@Param("page") Integer page);

    Boolean delAnimeById(Integer id);

}
