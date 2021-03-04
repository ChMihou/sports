package com.physical.movement.config;

import com.physical.movement.common.shiro.AdminFormAuthenticationFilter;
import com.physical.movement.common.shiro.CustomSessionListener;
import com.physical.movement.common.shiro.CustomerLogoutFilter;
import com.physical.movement.common.shiro.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.Filter;
import java.util.*;

/**
 * shiro环境配置
 */
@Configuration
public class ShiroConfiguration {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 后台身份认证realm;
     *
     * @return
     */
    @Bean(name = "adminShiroRealm")
    public ShiroRealm adminShiroRealm() {
        if (logger.isDebugEnabled()) {
            logger.debug("ShiroConfiguration.adminShiroRealm()");
        }
        ShiroRealm adminShiroRealm = new ShiroRealm();
        adminShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return adminShiroRealm;
    }

    /**
     * 当配置多Relam的情景下：
     * Shiro默认提供了三种 AuthenticationStrategy 实现：
     * AtLeastOneSuccessfulStrategy ：其中一个通过则成功。
     * FirstSuccessfulStrategy ：其中一个通过则成功，但只返回第一个通过的Realm提供的验证信息。
     * AllSuccessfulStrategy ：凡是配置到应用中的Realm都必须全部通过。
     * authenticationStrategy
     *
     * @return
     */
    @Bean(name = "authenticationStrategy")
    public AuthenticationStrategy authenticationStrategy() {
        if (logger.isDebugEnabled()) {
            logger.debug("ShiroConfiguration.authenticationStrategy()");
        }
        return new AtLeastOneSuccessfulStrategy();
    }

    /***
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     *  所以我们需要修改下doGetAuthenticationInfo中的代码）
     * @return
     */
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 散列的次数，比如散列两次，相当于md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);
        // 表示是否存储散列后的密码为16进制，需要和生成密码时的一样，默认是base64；
        //hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    /**
     * 注入securityManager
     *
     * @return
     * @see org.apache.shiro.mgt.SecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManage() {
        if (logger.isDebugEnabled()) {
            logger.debug("ShiroConfiguration.getDefaultWebSecurityManage()");
        }
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //注入认证
        Map<String, Object> shiroAuthenticatorRealms = new HashMap<>(5);
        shiroAuthenticatorRealms.put("adminShiroRealm", adminShiroRealm());
        //注入权限
        Collection<Realm> shiroAuthorizerRealms = new ArrayList<Realm>();
        shiroAuthorizerRealms.add(adminShiroRealm());
        securityManager.setRealms(shiroAuthorizerRealms);
        securityManager.setSubjectFactory(new DefaultWebSubjectFactory());
        //securityManager.setCacheManager(redisCacheManager());
        //securityManager.setSessionManager(defaultWebSessionManager());
        return securityManager;
    }

    /**
     * 代理过滤器（重写fileter就行，影响不大）
     *
     * @return
     */
    @Bean(name = "filterProxy")
    public FilterRegistrationBean filterProxy() {
        if (logger.isDebugEnabled()) {
            logger.debug("ShiroConfiguration.filterProxy()");
        }
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        registrationBean.setFilter(proxy);
        return registrationBean;
    }


    /**
     * SimpleAuthenticationInfo
     * 传统的加密验证，本demo用的是双重MD5认证
     *
     * @return
     */
    @Bean(name = "customHashedCredentialsMatcher")
    public HashedCredentialsMatcher customHashedCredentialsMatcher() {
        if (logger.isDebugEnabled()) {
            logger.debug("ShiroConfiguration.adminHashedCredentialsMatcher()");
        }
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列的次数，当于 m比如散列两次，相d5("");
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }

    /**
     * shiro生命周期的管理
     * 注入LifecycleBeanPostProcessor
     *
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        if (logger.isDebugEnabled()) {
            logger.debug("ShiroConfiguration.lifecycleBeanPostProcessor()");
        }
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 指定配置：
     * shiro跳转的指定错误页面
     *
     * @return
     */
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();

        /*未授权处理页*/
        properties.setProperty("org.apache.shiro.authz.UnauthorizedException", "admin/unauthorized");
        /*身份没有验证*/
        properties.setProperty("org.apache.shiro.authz.UnauthenticatedException", "login/login");
        resolver.setExceptionMappings(properties);
        return resolver;
    }

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * Filter Chain定义说明
     * 1、一个URL可以配置多个Filter，使用逗号分隔
     * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        if (logger.isDebugEnabled()) {
            logger.debug("ShiroConfiguration.shirFilter()");
        }
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //增加自定义过滤器
        Map<String, Filter> filters = new HashMap<>(5);
        filters.put("admin", new AdminFormAuthenticationFilter());
        CustomerLogoutFilter logoutFilter = new CustomerLogoutFilter();
        filters.put("logout", logoutFilter);
        shiroFilterFactoryBean.setFilters(filters);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        /**
         * anon（匿名）  org.apache.shiro.web.filter.authc.AnonymousFilter
         * authc（身份验证）       org.apache.shiro.web.filter.authc.FormAuthenticationFilter
         * authcBasic（http基本验证）    org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
         * logout（退出）        org.apache.shiro.web.filter.authc.LogoutFilter
         * noSessionCreation（不创建session） org.apache.shiro.web.filter.session.NoSessionCreationFilter
         * perms(许可验证)  org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter
         * port（端口验证）   org.apache.shiro.web.filter.authz.PortFilter
         * rest  (rest方面)  org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter
         * roles（权限验证）  org.apache.shiro.web.filter.authz.RolesAuthorizationFilter
         * ssl （ssl方面）   org.apache.shiro.web.filter.authz.SslFilter
         * member （用户方面）  org.apache.shiro.web.filter.authc.UserFilter
         * user  表示用户不一定已通过认证,只要曾被Shiro记住过登录状态的用户就可以正常发起请求,比如rememberMe
         */

        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/login/logout", "logout");
        filterChainDefinitionMap.put("/login/**", "anon");
        filterChainDefinitionMap.put("/news/**", "anon");
        //配置记住我或认证通过可以访问的地址
        filterChainDefinitionMap.put("/admin/**", "admin");
        filterChainDefinitionMap.put("/advisory/**", "admin");
        filterChainDefinitionMap.put("/payment/**", "admin");
        filterChainDefinitionMap.put("/announcement/**", "admin");
        filterChainDefinitionMap.put("/comment/**", "admin");
        filterChainDefinitionMap.put("/game/**", "admin");
        filterChainDefinitionMap.put("/message/**", "admin");
        filterChainDefinitionMap.put("/team/**", "admin");
        filterChainDefinitionMap.put("/court/**", "admin");
        filterChainDefinitionMap.put("/websocket/**", "admin");
        filterChainDefinitionMap.put("/member/**", "admin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    /**
     * 配置点击退出后返回的url
     * 配置LogoutFilter
     *
     * @return
     */
    public CustomerLogoutFilter shiroLogoutFilter() {
        CustomerLogoutFilter shiroLogoutFilter = new CustomerLogoutFilter();
        //配置登出后重定向的地址，等出后配置跳转到登录接口
        shiroLogoutFilter.setRedirectUrl("/login/login");
        return shiroLogoutFilter;
    }

    /**
     * @param securityManager
     * @return
     * @RequiresPermissions 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     */
    @Bean(name = "authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        if (logger.isDebugEnabled()) {
            logger.debug("ShiroConfiguration.authorizationAttributeSourceAdvisor()");
        }
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

//    /**
//     * @see DefaultWebSessionManager
//     * @return
//     */
//    @Bean(name="sessionManager")
//    public DefaultWebSessionManager defaultWebSessionManager() {
//        if(logger.isDebugEnabled()){
//            logger.debug("ShiroConfiguration.defaultWebSessionManager()");
//        }
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        //用户信息必须是序列化格式，要不创建用户信息创建不过去，此坑很大，
//        sessionManager.setSessionDAO(redisSessionDAO());
//        Collection<SessionListener> sessionListeners = new ArrayList<>();
//        sessionListeners.add(customSessionListener());
//        sessionManager.setSessionListeners(sessionListeners);
//        //单位为毫秒（1秒=1000毫秒） 3600000毫秒为1个小时
//        sessionManager.setSessionValidationInterval(3600000*12);
//        //3600000 milliseconds = 1 hour
//        sessionManager.setGlobalSessionTimeout(3600000*12);
//        //是否删除无效的，默认也是开启
//        sessionManager.setDeleteInvalidSessions(true);
//        //是否开启 检测，默认开启
//        sessionManager.setSessionValidationSchedulerEnabled(true);
//        //创建会话Cookie
//        Cookie cookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
//        cookie.setName("WEBID");
//        cookie.setHttpOnly(true);
//        sessionManager.setSessionIdCookie(cookie);
//        return sessionManager;
//    }
//
//    /**
//     * 配置redisSessionDao进行默认redis存储
//     * @return
//     */
//    @Bean(name = "redisSessionDAO")
//    public RedisSessionDAO redisSessionDAO(){
//        if(logger.isDebugEnabled()){
//            logger.debug("ShiroConfiguration.redisSessionDAO()");
//        }
//        return new RedisSessionDAO();
//    }
//
//    /**
//     * 配置redis缓存Manager
//     * @return
//     */
//    @Bean(name = "redisCacheManager")
//    public RedisShiroCacheManager redisCacheManager() {
//        if(logger.isDebugEnabled()){
//            logger.debug("ShiroConfiguration.redisCacheManager()");
//        }
//        return new RedisShiroCacheManager();
//    }

    /**
     * 配置Session监听器
     *
     * @return
     */
    @Bean(name = "customSessionListener")
    public CustomSessionListener customSessionListener() {
        if (logger.isDebugEnabled()) {
            logger.debug("ShiroConfiguration.customSessionListener()");
        }
        return new CustomSessionListener();
    }

}
