package com.physical.movement.controller;

import com.github.pagehelper.PageInfo;
import com.physical.movement.entity.Advisory;
import com.physical.movement.entity.Comment;
import com.physical.movement.entity.Message;
import com.physical.movement.entity.Team;
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

    @RequestMapping("/index")

    public ModelAndView home(ModelAndView mv) {
        mv.addObject("/home/index");
        return mv;
    }

    @RequestMapping("/about")
    public ModelAndView about(ModelAndView mv) {
        mv.addObject("/home/about");
        return mv;
    }

    @RequestMapping("/cause-single")
    public ModelAndView causesingle(ModelAndView mv) {
        mv.addObject("/home/cause-single");
        return mv;
    }

    @RequestMapping("/causes")
    public ModelAndView causes(ModelAndView mv) {
        mv.addObject("/home/causes");
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
        messages.setTitle(subject);
        Boolean flag = messageService.insert(messages);
        if (flag) {
            return ResultJson.success("谢谢您的反馈！");
        } else {
            return ResultJson.error("留言失败，请稍后重试!");
        }

    }

    @RequestMapping("/gallery")
    public ModelAndView gallery(ModelAndView mv) {
        mv.addObject("/home/gallery");
        return mv;
    }

    @RequestMapping("/news")
    public ModelAndView news(ModelAndView mv) {
        mv.addObject("/home/news");
        return mv;
    }

    @RequestMapping("/testimonial")
    public ModelAndView testimonial(ModelAndView mv) {
        mv.addObject("/home/testimonial");
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


    @RequestMapping("article")
    public ModelAndView article(Integer id, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {

        ModelAndView mv = new ModelAndView();
        Advisory advisory = new Advisory();
        advisory.setId(id);
        advisory = advisoryService.select(advisory);
        mv.addObject("advisory", advisory);
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
}
