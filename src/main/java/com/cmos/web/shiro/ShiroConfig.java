package com.cmos.web.shiro;

import com.cmos.web.controller.sys.LoginController;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author shiro配置
 * @date 创建时间：2018年1月29日17:26:45
 */
@Configuration
public class ShiroConfig {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
//
//	/**
//	 * ShiroFilterFactoryBean 处理拦截资源文件问题。
//	 * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
//	 * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
//	 * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
//	 * 3、部分过滤器可指定参数，如perms，roles
//	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		logger.info("启动Shiro的Web过滤器-->shiroFilter", ShiroFilterFactoryBean.class);
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		//自定义拦截器
		Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
		//限制同一帐号同时在线的个数。
		//filtersMap.put("kickout", kickoutSessionControlFilter());
		shiroFilterFactoryBean.setFilters(filtersMap);
		// 权限控制map.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 配置不会被拦截的链接 顺序判断
		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		// 从数据库获取动态的权限
		// filterChainDefinitionMap.put("/add", "perms[权限添加]");
		// <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		//logout这个拦截器是shiro已经实现好了的。
		// 从数据库获取
		/*List<SysPermissionInit> list = sysPermissionInitService.selectAll();

		for (SysPermissionInit sysPermissionInit : list) {
			filterChainDefinitionMap.put(sysPermissionInit.getUrl(),
					sysPermissionInit.getPermissionInit());
		}*/
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/*
	**
	* 错误方式，ShiroRealm这个类应该是，跟securityManager一样  注入的而不上能  然后set到securityManager
	* @Bean(name = "securityManager")
	*	public SecurityManager securityManager() {
	*			DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
	*			MyRealm myRealm = new MyRealm();
	*			manager.setRealm(myRealm);
	*			return manager;
	* }
	*	这里是正确的方式
	*	网上说了很多诸如是Spring加载顺序，shiroFilter在Spring自动装配bean之前的问题，其实也有可能忽略如下低级错误。
	*	在ShiroConfiguration中要使用@Bean在ApplicationContext注入MyRealm，不能直接new对象。
	*	道理和Controller中调用Service一样，都要是SpringBean，不能自己new。
	*/
	@Bean(name = "shiroRealm")
	public ShiroRealm shiroRealm() {
		ShiroRealm shiroRealm = new ShiroRealm();
		return shiroRealm;
	}

	@Bean(name = "securityManager")
	public SecurityManager securityManager(@Qualifier("shiroRealm")ShiroRealm shiroRealm) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(shiroRealm);
		return manager;
	}
//
//	/**
//	 * cookie对象;
//	 * @return
//	 */
//	public SimpleCookie rememberMeCookie(){
//		//这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
//		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
//		//<!-- 记住我cookie生效时间30天 ,单位秒;-->
//		simpleCookie.setMaxAge(2592000);
//		return simpleCookie;
//	}
//
//	/**
//	 * cookie管理对象;记住我功能
//	 * @return
//	 */
//	public CookieRememberMeManager rememberMeManager(){
//		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//		cookieRememberMeManager.setCookie(rememberMeCookie());
//		//rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
//		cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
//		return cookieRememberMeManager;
//	}
}
