package com.ahhtou.conf;

import com.ahhtou.pojo.User;
import com.ahhtou.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;

public class UserRealm extends AuthorizingRealm {

    @Resource
    UserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了 -> 授权doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 拿到当前登陆的这个对象
        Subject subject = SecurityUtils.getSubject();
        // 拿到user对象
        User user = (User) subject.getPrincipal();

        info.addStringPermission(user.getName());


        return info;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了 -> 认证doGetAuthenticationInfo");

        String pwd = "123";

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        User user = userService.queryUserByName(userToken.getUsername());

        // 用户名查询
        if (user == null) return null;

        // 密码认证
        return new SimpleAuthenticationInfo(user, user.getPwd(), "");
    }
}
