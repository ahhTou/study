package com.ahhTou.service.impl;


import com.ahhTou.bean.Anime;
import com.ahhTou.dao.AnimeMapper;
import com.ahhTou.dao.AnimeTypesMapper;
import com.ahhTou.service.AnimeAndTypesService;
import com.ahhTou.service.AnimeService;
import com.ahhTou.untils.MyTypesFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class AnimeAndTypesServiceImpl implements AnimeAndTypesService {

    @Resource
    AnimeMapper animeMapper;

    @Resource
    AnimeTypesMapper animeTypesMapper;

    @Resource
    MyTypesFilter myTypesFilter;

    @Override
    public Boolean addOneWithTypesMapper(Anime anime) {
        System.out.println("    > 事务管理开始");

        System.out.println("    > 添加动画");

        Boolean isAddAnime = animeMapper.addOneAnime(anime);

        if (isAddAnime) System.out.println("    > 添加动画成功");
        else return false;

        System.out.println("    > 添加动画类型映射");
        List<Integer> integerList = myTypesFilter.stringTypesToIntegerTypes(anime.getTypes());
        System.out.println("    > 转换类型成功 准备添加");
        Boolean aBoolean = animeTypesMapper.addTypesMapper(anime.getId(), integerList);

        if (aBoolean) System.out.println("    > 添加成功");
        else return false;

        return true;


    }


    @Override
    public Boolean editOneWithTypesMapper(Anime anime) {
        System.out.println("    > 事务管理开始");


        System.out.println("    > 删除全部类型映射");
        Boolean isDelMapperAll = animeTypesMapper.delAllTypesByAnimeId(anime.getId());
        if (isDelMapperAll) System.out.println("    > 删除全部类型映射 成功");
        else System.out.println("    > 删除失败，可能是因为原来没有类型");

        System.out.println("    > 添加动画类型映射");
        List<Integer> integerList = myTypesFilter.stringTypesToIntegerTypes(anime.getTypes());
        System.out.println("    > 转换类型成功 准备添加");

        if (integerList.size() > 0) {

            Boolean isAddNewMapper = animeTypesMapper.addTypesMapper(anime.getId(), integerList);
            if (isAddNewMapper) System.out.println("    > 添加映射成功");
            else return false;

        } else {
            System.out.println("    > 没有动画类型可以添加");
        }

        Boolean isAddAnime = animeMapper.updateOneAnime(anime);
        if (isAddAnime) System.out.println("    > 添加动画成功");
        else return false;

        return true;
    }
}
