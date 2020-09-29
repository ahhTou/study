package com.ahhtou.conf;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConf {

    // ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("sm") DefaultWebSecurityManager sm) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        bean.setSecurityManager(sm);
        /*
         * anon 无需认证就可以访问
         * authc 必须认证了才能访问
         * user 必须拥有过记住我功能才能用
         * perms 拥有对某个权限才能访问
         * role 拥有某个角色全新啊才能访问
         *
         * */
        Map<String, String> filterMap = new LinkedHashMap<>();
        bean.setFilterChainDefinitionMap(filterMap);
        // 权限操作
        filterMap.put("/user/add", "perms[ahhTou]");

        // 请求
        filterMap.put("/user/update", "authc");
        filterMap.put("/no/*", "authc");
        filterMap.put("/yes/*", "anon");

        // 验证失败重定向
        bean.setLoginUrl("/login");

        // 设置未授权
        bean.setUnauthorizedUrl("/noAuth");

        return bean;
    }

    // DefaultWebSecurityManager
    @Bean("sm")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;

    }

    // 创建 realm 对象 需要 自定义
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

}
