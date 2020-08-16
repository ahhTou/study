package com.xhr.dao;

import com.xhr.bean.UserSeenList;

import java.util.List;

public interface ListMapper {


    List<UserSeenList> getAllByUsername(String username);

    UserSeenList getOne(Integer movie_id);

    boolean updateOne(UserSeenList userSeenList);

    boolean addOne(UserSeenList seenList);

    boolean delOne(Integer movie_id);

}
