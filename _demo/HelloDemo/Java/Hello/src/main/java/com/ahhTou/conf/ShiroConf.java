package com.ahhTou.conf;

import com.ahhTou.filter.JwtFilter;
import com.ahhTou.security.ShiroUserRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConf {
    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     *
     * @return ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        // 拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/error/**", "anon");
        filterChainDefinitionMap.put("/assets/**", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");

        filterChainDefinitionMap.put("/account/toLogin", "anon"); //登录接口排除
        filterChainDefinitionMap.put("/account/logout", "anon"); //登出接口排除

        filterChainDefinitionMap.put("/msgBoard/**", "anon");
        filterChainDefinitionMap.put("/base/**", "anon");

        // 设置自己的过滤器
        Map<String, Filter> filterMap = new HashMap<>(1);
        filterMap.put("JwtFilter", new JwtFilter());

        // 过滤链定义，从上向下顺序执行，一般将放在最为下边
        filterChainDefinitionMap.put("/**", "JwtFilter");

        filterChainDefinitionMap.put("/t1", "perms[member]");
        filterChainDefinitionMap.put("/t2", "perms[member]");


        //未授权界面返回JSON
        bean.setFilters(filterMap);
        bean.setUnauthorizedUrl("/sys/common/403-NoAuthz");
        bean.setLoginUrl("/sys/common/403-NoLogin");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return bean;
    }

    // DefaultWebSecurityManager
    @Bean("sm")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("ShiroRealm") ShiroUserRealm shiroUserRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroUserRealm);

        // 关闭shiro自带的session，详情见文档
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);


        return securityManager;

    }

    // 创建 realm 对象 需要 自定义
    @Bean("ShiroRealm")
    public ShiroUserRealm shiroUserRealm() {
        return new ShiroUserRealm();
    }

    // 下面的代码是添加注解支持
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
