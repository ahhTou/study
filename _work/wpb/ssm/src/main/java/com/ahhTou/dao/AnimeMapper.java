package com.ahhTou.dao;


import com.ahhTou.bean.Anime;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AnimeMapper {

    Anime getOneAnimeById(Integer id);

    Integer getHowMuchColumn();

    List<Anime> getOnePage(@Param("page") Integer page);

    Boolean delAnimeById(Integer id);

    Boolean updateOneAnime(Anime anime);

    Boolean addOneAnime(Anime anime);


}
