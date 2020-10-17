package com.ahhtou.redis;

import java.util.Set;

public interface AuthRedis {

    void setAuthToken(String token);

    void setAuthToken(String oldToken, String newToken);

    String getAuthToken(String oldToken);

    Set<String> getUserRolesSet(String username);

    Set<String> getUserPermissionsSet(String username);

    void setUserRole(String role, String username);

    void setUserRoles(Set<String> roles, String username);

    void setPermission(String prem, String username);

    void setPermissions(Set<String> perms, String username);

}
