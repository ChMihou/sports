package com.physical.movement.controller;

import com.github.pagehelper.PageInfo;
import com.physical.movement.entity.Announcement;
import com.physical.movement.entity.SysUser;
import com.physical.movement.model.Paginator;
import com.physical.movement.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    AnnouncementService announcementService;
    @Autowired
    AdvisoryService advisoryService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    CommentService commentService;
    @Autowired
    MessageService messageService;
    @Autowired
    TeamService teamService;
    @Autowired
    GameService gameService;

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
    public ModelAndView welcome(ModelAndView mv, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "1") int pageSize, HttpServletRequest request) {
        mv.setViewName("/admin/welcome");
        Announcement announcement = new Announcement();
        List<Announcement> announcements = announcementService.selectAll(announcement, pageNum, pageSize);
        PageInfo nlist = new PageInfo(announcements);
        List pagenums = new ArrayList();
        int advisoryCount = advisoryService.selectCount();
        int sysuserCount = sysUserService.selectCount();
        int commentCount = commentService.selectCount();
        int messageCount = messageService.selectCount();
        int teamCount = teamService.selectCount();
        int gameCount = gameService.selectCount();
        Paginator.page(pagenums, nlist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("nlist", nlist);
        mv.addObject("advisoryCount", advisoryCount);
        mv.addObject("sysuserCount", sysuserCount);
        mv.addObject("commentCount", commentCount);
        mv.addObject("messageCount", messageCount);
        mv.addObject("teamCount", teamCount);
        mv.addObject("gameCount", gameCount);
        return mv;
    }

}
