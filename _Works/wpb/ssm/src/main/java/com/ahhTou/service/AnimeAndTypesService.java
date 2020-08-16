package com.ahhTou.service;

import com.ahhTou.bean.Anime;
import org.springframework.stereotype.Service;

public interface AnimeAndTypesService {

    Boolean addOneWithTypesMapper(Anime anime);

    Boolean editOneWithTypesMapper(Anime anime);

}
