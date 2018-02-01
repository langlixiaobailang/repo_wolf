package com.cmos.web.shiro;

import com.cmos.web.beans.sys.SysUser;
import com.cmos.web.iservice.sys.ISysUserSV;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public class ShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
    @Autowired
    private ISysUserSV sysUserSV;

    /**
     * authenticationToken
     * 认证
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("******** start identification user ***********");
        // 第一步从token中取出用户名
        String loginName = (String) authenticationToken.getPrincipal();
        // 第二步：根据用户输入的loginName从数据库查询
        if(StringUtils.isNotBlank(loginName)){
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("loginName",loginName);
            SysUser sysUser = null;
            try {
                sysUser = sysUserSV.selectByMap(paramMap);
            } catch (Exception e) {
                logger.error("search user error",e);
            }
            if (sysUser != null) {
                String password = sysUser.getPassword();
                 //* 认证的用户,正确的密码
                AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(sysUser, password, this.getName());
                return authcInfo;
            }
        }
        return  null;
    }
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 返回null将会导致用户访问任何被拦截的请求时都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }


}
