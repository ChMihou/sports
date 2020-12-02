package com.physical.movement.common.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 * 拦截未认证页面，自动跳转登录页
 * 覆盖authc的认证
 */
public class AdminFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    public void setLoginUrl(String loginUrl) {
        super.setLoginUrl("/login/login");
    }

    @Override
    public void setSuccessUrl(String successUrl) {
        super.setSuccessUrl("/admin/index");
    }

}
