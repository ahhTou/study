package com.ahhtou.utils;

import org.apache.shiro.subject.Subject;

public class ShiroUtils {

    public static final String SHIRO_ROLES_MEMBER = "member";

    public static boolean isMember(Subject subject) {
        return subject.hasRole(ShiroUtils.SHIRO_ROLES_MEMBER);
    }
}
