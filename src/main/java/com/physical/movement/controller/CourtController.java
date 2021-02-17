package com.physical.movement.controller;

import com.github.pagehelper.PageInfo;
import com.physical.movement.entity.Court;
import com.physical.movement.model.Paginator;
import com.physical.movement.service.CourtService;
import com.physical.movement.service.TeamService;
import com.physical.movement.utils.GetDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 租场
 */

@Controller
@RequestMapping("court")
public class CourtController {
    @Autowired
    TeamService teamService;

    @Autowired
    CourtService courtService;

    @RequestMapping("/court")
    public ModelAndView teamManage(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize, HttpServletRequest request, HttpSession session, ModelAndView mv) throws ParseException {
        mv.setViewName("/court/court");
        String date = request.getParameter("date");
        mv.addObject("date", date);
        String key = request.getParameter("key");
        mv.addObject("key", key);
        Court court = new Court();
        court.setGmtCreate(date == null || date.equals("") ? GetDate.getTimeToday() : GetDate.StringToDate(date));
        court.setAddress(key);
        List<Court> courts = courtService.selectAll(court, pageNum, pageSize);
        PageInfo clist = new PageInfo(courts);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, clist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("clist", clist);
        return mv;
    }

    @RequestMapping("/test")
    public ModelAndView addCour() throws ParseException {
        courtService.addListCourt();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/login/login");
        return mv;
    }
}
