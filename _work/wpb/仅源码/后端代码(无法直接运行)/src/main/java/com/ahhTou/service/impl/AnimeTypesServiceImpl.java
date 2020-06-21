package com.ahhTou.service.impl;



import com.ahhTou.dao.AnimeTypesMapper;
import com.ahhTou.service.AnimeTypesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AnimeTypesServiceImpl implements AnimeTypesService {

    @Resource
    AnimeTypesMapper animeTypesMapper;

    @Override
    public List<String> getAllTypes() {
        return animeTypesMapper.getAllTypes();
    }

}
