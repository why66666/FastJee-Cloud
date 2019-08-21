package cn.chi365.fastjee.cloud.web.admin.commons.shiro;

import cn.chi365.fastjee.cloud.web.admin.commons.utils.StringUtil;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisClusterManager;
import org.crazycake.shiro.RedisSentinelManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;


/**
 * shiro配置类
 * @author Yusir
 * @version ：1.0.0
 * @date Created in 2019/4/24 21:23
 */
@Configuration
public class ShiroConfiguration {
    protected  static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

    private static final String CACHE_KEY = "shiro:cache:";
    private static final String SESSION_KEY = "shiro:session:";
    private static final String NAME = "custom.name";
    private static final String VALUE = "/";

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager,KickoutSessionControlFilter kickoutSessionControlFilter) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //配置登录的url和登录成功的url
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/login");
        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        //限制同一帐号同时在线的个数。
        filtersMap.put("kickout", kickoutSessionControlFilter);
        bean.setFilters(filtersMap);
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        /* 放行静态资源 */
        filterChainDefinitionMap.put("/bower_components/**","anon");
        filterChainDefinitionMap.put("/dist/**","anon");
        filterChainDefinitionMap.put("/plugins/**","anon");
        /* ./放行静态资源 */
        filterChainDefinitionMap.put("/login*", "anon");
        filterChainDefinitionMap.put("/doLogin*", "anon");
        filterChainDefinitionMap.put("/logout*", "anon");
        filterChainDefinitionMap.put("/error", "anon");
        //filterChainDefinitionMap.put("/index*", "anon");
        //放行踢出页面
        filterChainDefinitionMap.put("/kickout", "anon");
        /* 需要认证 */
        filterChainDefinitionMap.put("/*", "kickout,authc");
        filterChainDefinitionMap.put("/**", "kickout,authc");
        filterChainDefinitionMap.put("/*.*", "kickout,authc");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    /**
     *
     * 配置核心安全事务管理器
     *
     * */
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm, RedisCacheManager cacheManager, DefaultWebSessionManager sessionManager) {
        logger.info("###################shiro开始加载##################");
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //设置realm
        manager.setRealm(authRealm);
        // 自定义缓存实现 使用redis
        manager.setCacheManager(cacheManager);
        // 自定义session管理 使用redis
        manager.setSessionManager(sessionManager);
        return manager;
    }

    /**
     *
     * 配置自定义的权限登录器
     *
     * */
    @Bean(name = "authRealm")
    public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
        AuthRealm authRealm = new AuthRealm();
        //authRealm.setAuthorizationCachingEnabled(false);
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }

    /**
     * 配置自定义的密码比较器
     * @return
     */
    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager(RedisSentinelManager  redisManager) {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setExpire(86400);
        redisCacheManager.setKeyPrefix(CACHE_KEY);
        redisCacheManager.setRedisManager(redisManager);
        return redisCacheManager;
    }

    /**
     * redis-sentinel集群
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    @Bean
    public RedisSentinelManager redisManager(RedisProperties redisProperties) {
        RedisSentinelManager  redisManager = new RedisSentinelManager ();
        redisManager.setHost(StringUtil.getString(redisProperties.getSentinel().getNodes()));
        redisManager.setMasterName(redisProperties.getSentinel().getMaster());
        // redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * Session Manager
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public DefaultWebSessionManager sessionManager(RedisSessionDAO redisSessionDAO, SimpleCookie simpleCookie) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(simpleCookie);
        return sessionManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO(RedisSentinelManager  redisManager) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager);
        //存活时间
        redisSessionDAO.setExpire(86400);
        //前缀
        redisSessionDAO.setKeyPrefix(SESSION_KEY);
        return redisSessionDAO;
    }

    /**
     * 限制同一账号登录同时登录人数控制
     * @return
     */
    @Bean
    public KickoutSessionControlFilter kickoutSessionControlFilter(RedisCacheManager cacheManager,DefaultWebSessionManager sessionManager) {
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        kickoutSessionControlFilter.setCacheManager(cacheManager);
        kickoutSessionControlFilter.setSessionManager(sessionManager);
        kickoutSessionControlFilter.setKickoutAfter(false);
        kickoutSessionControlFilter.setMaxSession(1);
        kickoutSessionControlFilter.setKickoutUrl("/kickout");
        return kickoutSessionControlFilter;
    }

    /**
     * Cookie格式化
     * @return
     */
    @Bean
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName(NAME);
        simpleCookie.setValue(VALUE);
        return simpleCookie;
    }

    /**
     * Shiro生命周期处理器
     *
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    /***
     * 授权所用配置
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }


    /***
     * 使授权注解起作用不如不想配置可以在pom文件中加入
     * <dependency>
     *<groupId>org.springframework.boot</groupId>
     *<artifactId>spring-boot-starter-aop</artifactId>
     *</dependency>
     *
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

}