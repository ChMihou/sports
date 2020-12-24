package com.physical.movement.controller;

import com.physical.movement.service.CourtService;
import com.physical.movement.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("court")
public class CourtController {
    @Autowired
    TeamService teamService;

    @Autowired
    CourtService courtService;

    @RequestMapping("/court")
    public ModelAndView teamManage(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request, HttpSession session, ModelAndView mv) {
        mv.setViewName("/court/court");
        return mv;
    }
}
