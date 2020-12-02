package com.physical.movement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class AdminController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("index")
    public ModelAndView AdminIndex() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/index");
        return mv;
    }
}
