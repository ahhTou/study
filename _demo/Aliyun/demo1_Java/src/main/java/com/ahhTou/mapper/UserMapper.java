package com.ahhTou.mapper;


import com.ahhTou.bean.User;
import com.ahhTou.conf.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.cache.decorators.LruCache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

/*

    <cache type="com.ahhTou.conf.MybatisRedisCache">
        <property name="eviction" value="LRU"/>
        <property name="flushInterval" value="6000000"/>
        <property name="size" value="1024"/>
        <property name="readOnly" value="false"/>
    </cache>

*/
@Repository
public interface UserMapper {

    List<String> checkUsernameUnique(String username);

    List<String> checkEmailUnique(String email);

    Integer register(User user);

    User getUserMsgByUsername(String username);
}
