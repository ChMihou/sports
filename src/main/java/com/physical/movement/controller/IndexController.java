package com.physical.movement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登陆页面跳转
 */
@Controller
public class IndexController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("")
    public String index() {
        return "/login/login";
    }


}
