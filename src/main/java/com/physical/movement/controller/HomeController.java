package com.physical.movement.controller;

import com.github.pagehelper.PageInfo;
import com.physical.movement.entity.*;
import com.physical.movement.model.Paginator;
import com.physical.movement.model.ResultJson;
import com.physical.movement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.physical.movement.model.SportsType.STATUS_MAP;

@Controller
@RequestMapping("home")
public class HomeController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private AdvisoryService advisoryService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private AnnouncementService announcementService;

    @RequestMapping("/index")

    public ModelAndView home(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, ModelAndView mv) {
        mv.addObject("/home/index");
        //精彩文章
        Advisory advisory = new Advisory();
        advisory.setWonderful(1);
        List<Advisory> advisories = advisoryService.selectAll(advisory, pageNum, pageSize);
        PageInfo wonderlist = new PageInfo(advisories);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, wonderlist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("nlist", wonderlist);

        //体育汇总
        Advisory advisoryAll = new Advisory();
        advisory.setNselect(5);
        List<Advisory> advisoriesAll = advisoryService.selectAll(advisoryAll, pageNum, pageSize);
        PageInfo nlist = new PageInfo(advisoriesAll);
        List pagenumsAll = new ArrayList();
        Paginator.page(pagenumsAll, nlist, pageNum, pageSize);
        mv.addObject("pagenums", pagenumsAll);
        mv.addObject("nlist", nlist);

        //体育趣图
        List<String> images = advisoryService.selectAllImage(pageNum, pageSize);

        PageInfo ilist = new PageInfo(images);
        List pagenumsImage = new ArrayList();
        Paginator.page(pagenumsImage, ilist, pageNum, pageSize);
        mv.addObject("pagenums", pagenumsImage);
        mv.addObject("ilist", ilist);
        //公告
        Announcement announcement = new Announcement();
        List<Announcement> announcements = announcementService.selectAll(announcement, pageNum, pageSize);
        PageInfo announcementsOne = new PageInfo(announcements);
        List pagenumsAnnouncementOne = new ArrayList();
        Paginator.page(pagenumsAnnouncementOne, announcementsOne, pageNum, pageSize);
        mv.addObject("pagenums", pagenumsAnnouncementOne);
        mv.addObject("nlist", announcementsOne);

        return mv;

    }

    @RequestMapping("/about")
    public ModelAndView about(ModelAndView mv) {
        mv.addObject("/home/about");
        return mv;
    }

    @RequestMapping("/cause-single")
    public ModelAndView causesingle(ModelAndView mv, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
        mv.addObject("/home/cause-single");
        Advisory advisory = new Advisory();
        advisory.setNselect(2);
        List<Advisory> advisories = advisoryService.selectAll(advisory, pageNum, pageSize);
        PageInfo nlist = new PageInfo(advisories);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, nlist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("nlist", nlist);
        return mv;
    }

    @RequestMapping("/causes")
    public ModelAndView causes(ModelAndView mv, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
        mv.addObject("/home/causes");
        Advisory advisory = new Advisory();
        advisory.setNselect(5);
        List<Advisory> advisories = advisoryService.selectAll(advisory, pageNum, pageSize);
        PageInfo nlist = new PageInfo(advisories);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, nlist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("nlist", nlist);
        return mv;
    }

    @RequestMapping("article")
    public ModelAndView article(Integer id, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {

        ModelAndView mv = new ModelAndView();
        Advisory advisory = new Advisory();
        advisory.setId(id);
        advisory = advisoryService.select(advisory);
        mv.addObject("notice", advisory);
        mv.addObject("id", id);
        Comment comment = new Comment();
        comment.setId(id);
        List<Comment> userCommentVos = commentService.selectAll(comment, pageNum, pageSize);
        PageInfo clist = new PageInfo(userCommentVos);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, clist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("clist", clist);
        mv.setViewName("article");
        return mv;
    }

    @RequestMapping("/contact")
    public ModelAndView concat(ModelAndView mv) {
        mv.addObject("/home/contact");
        return mv;
    }

    @RequestMapping("/contactSubmit")
    @ResponseBody
    public ResultJson concat(String message, String subject, HttpSession session) {
        Message messages = new Message();
        if (session.getAttribute("uid") != null) {
            messages.setUid((Integer) session.getAttribute("uid"));
        }
        messages.setMess(message);
        messages.setFlag(2);
        messages.setTitle(subject);
        Boolean flag = messageService.insert(messages);
        if (flag) {
            return ResultJson.success("谢谢您的反馈！");
        } else {
            return ResultJson.error("留言失败，请稍后重试!");
        }

    }

    @RequestMapping("/news")
    public ModelAndView news(ModelAndView mv) {
        mv.addObject("/home/news");
        return mv;
    }

    @RequestMapping("/testimonial")
    public ModelAndView testimonial(ModelAndView mv, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
        mv.addObject("/home/testimonial");
        Advisory advisory = new Advisory();
        advisory.setNselect(1);
        List<Advisory> advisories = advisoryService.selectAll(advisory, pageNum, pageSize);
        PageInfo nlist = new PageInfo(advisories);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, nlist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("nlist", nlist);
        return mv;
    }

    @RequestMapping("/schoolteam")
    public ModelAndView schoolteam(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request, HttpSession session, ModelAndView mv) {
        String check = request.getParameter("check");
        mv.addObject("check", check);
        String flag = request.getParameter("flag");
        mv.addObject("flag", flag);
        String key = request.getParameter("key");
        mv.addObject("key", key);
        Team team = new Team();
        if (check != null && !check.equals("0") && !check.equals("")) {
            team.setTeamtype(Integer.parseInt(check));
        }
        team.setTeamname(key);
        team.setFlag((byte) 1);
        List<Team> teamList = teamService.selectAll(team, pageNum, pageSize);
        PageInfo tlist = new PageInfo(teamList);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, tlist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("tlist", tlist);
        mv.addObject("SportsType", STATUS_MAP);
        mv.setViewName("/home/schoolteam");
        return mv;
    }

    @RequestMapping("/gallery")
    public ModelAndView gallery(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "16") int pageSize) {

        ModelAndView mv = new ModelAndView();

        List<String> images = advisoryService.selectAllImage(pageNum, pageSize);

        PageInfo ilist = new PageInfo(images);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, ilist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("ilist", ilist);

        mv.setViewName("/home/gallery");

        return mv;
    }
}
