package com.physical.movement.controller;

import com.physical.movement.entity.SysUser;
import com.physical.movement.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class AdminController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SysUserService sysUserService;

    @RequestMapping("index")
    public ModelAndView AdminIndex() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/index");
        return mv;
    }

    @RequestMapping("member")
    public ModelAndView checkMember(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        SysUser user = (SysUser) session.getAttribute("sysUser");
        SysUser flag = new SysUser();
        flag.setId(user.getId());
        SysUser sysUser = sysUserService.select(flag);
        mv.addObject("sysUser", sysUser);
        mv.setViewName("member/member");
        return mv;
    }

    @RequestMapping("/welcome")
    public ModelAndView welcome(ModelAndView mv) {
        mv.setViewName("/admin/welcome");
        return mv;
    }

}
