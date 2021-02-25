package com.oe.shiro;

/*
* shiro配置类
*
* */

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration //声明该类是个配置类
public class ShrioConfig {

  //  @Autowired
    //private UserRealm userRealm;

    //shiro基于过滤器
    //创建ShiroFilterFactoryBean是过滤器类 shiro以实现 需要将此类讲给IOC容器
   @Bean //声明交给IOC容器                                   注入安全管理器里得信息
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Autowired DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //设置安全管理器
       shiroFilterFactoryBean.setSecurityManager(securityManager);//可以直接调用方法
      //设置那些资源放过 哪些拦截  要按照配置得顺序拦截
       //添加Shiro内置过滤器
       /**
        * Shiro内置过滤器，可以实现权限相关的拦截器
        *    常用的过滤器：
        *       anon: 无需认证（登录）可以访问
        *       authc: 必须认证才可以访问
        *       user: 如果使用rememberMe的功能可以直接访问
        *       perms： 该资源必须得到资源权限才可以访问
        *       role: 该资源必须得到角色权限才可以访问
        */
       Map<String, String> filterMap=new LinkedHashMap<>();//linked...保证顺序
        filterMap.put("/img/**","anon");
        filterMap.put("/css/**","anon");
        filterMap.put("/js/**","anon");
        filterMap.put("/plugins/**","anon");
       filterMap.put("/userController/**","anon");//放过首页 必须下方过在拦截
       //filterMap.put("/**","anon");//放过首页 必须下方过在拦截

//       filterMap.put("/**","authc");//拦截所有

       //修改登录得跳转页面   因为默认是login.jsp 咱们没有所以改
       shiroFilterFactoryBean.setLoginUrl("/toLogin");
       //设置未授权提示页面
      // shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
       //讲map存到shiro得过滤连中
       shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return  shiroFilterFactoryBean;
    }

    //创建安全管理 SecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Autowired UserRealm userRealm){
       DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
       // securityManager.setRealm(userRealm()); //以下都可以直接调用方法 不使用@Bean 去掉类似@Autowired UserRealm userRealm之类得
       return securityManager;
    }

    //创建realm 关联数据库 //两种方法 1.把Userlearm注入springboot中@Comp.. 然后@Aut..调用 2.使用bean注入
    @Bean
    public UserRealm userRealm(@Autowired HashedCredentialsMatcher hashedCredentialsMatcher){
        UserRealm userRealm = new UserRealm();
        //设置密码加密方式
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return userRealm;
    }
    //加密方式
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式，MD5，sha-1
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数 1234 -> xxxabc -> yyyde
        hashedCredentialsMatcher.setHashIterations(3);
        //加密的编码
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    /*shiro 扩展标签库：是对tymeleaf的支持
    * 配置ShirDialect ，用于thymelact和shiro标签配合使用‘
    * 启动thyemleaf标签
    * */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
