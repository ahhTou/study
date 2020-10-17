package com.ahhtou.security;

import com.ahhtou.bean.User;
import com.ahhtou.redis.AuthRedis;
import com.ahhtou.service.UserService;
import com.ahhtou.utils.jwt.JwtErr;
import com.ahhtou.utils.jwt.JwtToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;
import java.util.Set;

public class ShiroUserRealm extends AuthorizingRealm {

    @Resource
    @Lazy
    private UserService userService;

    @Autowired
    @Lazy
    AuthRedis authRedis;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("Shiro:Realm -> 执行了授权");

        if (principals == null) throw new AuthorizationException("角色为空");

        User dbUser = (User) principals.getPrimaryPrincipal();
        String username = dbUser.getUsername();

        if (username == null) throw new AuthenticationException(JwtErr.TOKEN_NOT_FIND_SOMETHING);
        System.out.println(username);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 设置用户拥有的角色集合，比如“admin,test”
        Set<String> roleSet = authRedis.getUserRolesSet(username);
        // 设置用户拥有的权限集合，比如“sys:role:add,sys:user:add”
        Set<String> permissionSet = authRedis.getUserPermissionsSet(username);

        info.setRoles(roleSet);
        info.addStringPermissions(permissionSet);

        return info;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("Shiro:Realm -> 执行了认证");

        String userToken = (String) token.getCredentials();

        // err1 如果Token为空
        if (userToken == null) throw new AccountException(JwtErr.TOKEN_IS_EMPTY);

        // 验证token是否正确,并返回user
        User dbUser = this.getDbUserByTokenAndVerify(userToken);

        return new SimpleAuthenticationInfo(dbUser, userToken, getName());
    }

    private User getDbUserByTokenAndVerify(String token) throws AuthenticationException {

        User dbUser = null;

        // 1. redis服务器中是否能得到同样的验证用的Token
        String authToken = authRedis.getAuthToken(token);

        if (authToken == null) throw new AuthenticationException(JwtErr.TOKEN_IS_NOT_EXIST);

        // 2. 是否能得到用户名
        String username = JwtUtils.getUsername(authToken);
        if (username == null) throw new AuthenticationException(JwtErr.TOKEN_NOT_FIND_SOMETHING);

        // 3. 检验token是否合格
        try {
            dbUser = userService.getUserByUsername(username);
        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage());
        }

        boolean isOk = JwtUtils.verify(authToken, username, dbUser.getPassword());
        if (!isOk) throw new AuthenticationException(JwtErr.TOKEN_ERR);

        // 4. token 刷新
        String newToken = JwtUtils.sign(dbUser.getUsername(), dbUser.getPassword());
        authRedis.setAuthToken(token, newToken);


        return dbUser;
    }

}
