package com.ahhTou.dao;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AnimeTypesMapper {

    List<String> getTypesByAnimeId(@Param("id") Integer id);

    List<String> getAllTypes();

    @MapKey("type_id")
    <T> Map<String, Map<String, T>> getAllTypesMapper();

    Boolean addTypesMapper(@Param("id") Integer id, @Param("list") List<Integer> list);

    Boolean delAllTypesByAnimeId(@Param("id") Integer id);

}
