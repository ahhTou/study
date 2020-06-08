package com.ahhTou.dao;


import com.ahhTou.bean.Anime;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface AnimeMapper {

    Anime getOneAnimeById(Integer id);

    ArrayList<Anime> getOnePage(@Param("page") Integer page);
}
