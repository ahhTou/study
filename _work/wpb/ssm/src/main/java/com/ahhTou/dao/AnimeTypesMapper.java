package com.ahhTou.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnimeTypesMapper {

    List<String> getTypesByAnimeId(@Param("id") Integer id);

    List<String> getAllTypes();

}
