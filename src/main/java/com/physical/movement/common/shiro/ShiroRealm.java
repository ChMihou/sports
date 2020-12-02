package com.physical.movement.common.shiro;

import com.physical.movement.entity.SysUser;
import com.physical.movement.service.SysUserService;
import com.physical.movement.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SysUserService sysUserService;

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        if (logger.isDebugEnabled()) {
            logger.info("后台登录：AdminShiroRealm.doGetAuthenticationInfo()");
        }
        String username = (String) token.getPrincipal();

        SysUser sysUser = new SysUser();

        sysUser.setUsername(username);
        SysUser user = sysUserService.select(sysUser);

        if (user != null) {
            throw new UnknownAccountException();
        }

        String oringnPassword = new String((char[]) token.getCredentials());

        System.out.println("传入的用户名和密码：" + username + "  " + oringnPassword);

        String encodedPassword = ShiroUtils.shiroEncryption(oringnPassword, user.getSalt());

        System.out.println("加密后的用户名和密码：" + username + "  " + encodedPassword);

        return new SimpleAuthenticationInfo(
                //用户名
                user,
                //密码
                user.getPassword(),
                //加盐加密
                ByteSource.Util.bytes(user.getSalt()),
                //realm name
                user.getUsername()
        );
    }

    /**
     * 清空当前用户权限信息
     */
    public void clearCachedAuthorizationInfo() {
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 认证的数据清除
     * 指定principalCollection 清除
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}
