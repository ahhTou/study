package com.ahhTou.service;

import com.ahhTou.bean.Anime;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface AnimeService {

    Integer getHowMuchColumn();

    ArrayList<Anime> getOnePage(@Param("page") Integer page);

}
