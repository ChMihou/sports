package com.physical.movement.common.shiro;


import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * 常规shiro生命周期配置
 */
@Configuration
public class CustomSessionListener implements SessionListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 一个回话的生命周期开始
     */
    @Override
    public void onStart(Session session) {
        if (logger.isDebugEnabled()) {
            logger.debug("onStart:{}", session.getId());
        }

    }

    /**
     * 一个回话的生命周期结束
     */
    @Override
    public void onStop(Session session) {
        if (logger.isDebugEnabled()) {
            logger.debug("onStop:{}", session.getId());
        }
    }

    @Override
    public void onExpiration(Session session) {
        if(logger.isDebugEnabled()){
            logger.debug("onExpiration:{}", session.getId());
        }
    }

}

