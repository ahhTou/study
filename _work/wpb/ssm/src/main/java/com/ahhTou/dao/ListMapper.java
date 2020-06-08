package com.ahhTou.dao;

import com.ahhTou.bean.User;
import com.ahhTou.bean.MyAnime;

public interface ListMapper {


    MyAnime getWatchedListByAnimeId(User user);


}
