package com.ahhTou.redis.impl;

import com.ahhTou.redis.AuthRedis;
import com.ahhTou.utils.RedisUtils;
import com.ahhTou.utils.jwt.JwtUtils;
import com.ahhTou.utils.var.CommonConstant;
import org.apache.ibatis.reflection.ArrayUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Repository
public class AuthRedisImpl implements AuthRedis {

    @Resource
    RedisUtils redisUtils;

    @Override
    public void setAuthToken(String token) {
        redisUtils
                .set(CommonConstant.REDIS_TOKEN_PREFIX_KEY + token, token)
                .expire(CommonConstant.REDIS_TOKEN_PREFIX_KEY, JwtUtils.EXPIRE_TIME_MIN);
    }

    @Override
    public void setAuthToken(String oldToken, String newToken) {
        redisUtils
                .set(CommonConstant.REDIS_TOKEN_PREFIX_KEY + oldToken, newToken)
                .expire(CommonConstant.REDIS_TOKEN_PREFIX_KEY, JwtUtils.EXPIRE_TIME_MIN);
    }

    @Override
    public String getAuthToken(String token) {
        return (String) redisUtils.getString(CommonConstant.REDIS_TOKEN_PREFIX_KEY + token);
    }

    @Override
    public Set<String> getUserRolesSet(String username) {
        return redisUtils.getSet(CommonConstant.REDIS_SHIRO_ROLES_PREFIX_KEY + username);

    }

    @Override
    public Set<String> getUserPermissionsSet(String username) {
        return redisUtils.getSet(CommonConstant.REDIS_SHIRO_PERMS_PREFIX_KEY + username);
    }

    @Override
    public void setUserRole(String role, String username) {
        Set<String> set = Collections.singleton(role);
        redisUtils.set(CommonConstant.REDIS_SHIRO_ROLES_PREFIX_KEY + username, set);
    }

    @Override
    public void setUserRoles(Set<String> roles, String username) {
        redisUtils.set(CommonConstant.REDIS_SHIRO_ROLES_PREFIX_KEY + username, roles);
    }

    @Override
    public void setPermission(String prem, String username) {
        redisUtils.set(CommonConstant.REDIS_SHIRO_PERMS_PREFIX_KEY + username, Collections.singleton(prem));
    }

    @Override
    public void setPermissions(Set<String> perms, String username) {
        redisUtils.set(CommonConstant.REDIS_SHIRO_PERMS_PREFIX_KEY + username, perms);
    }
}
