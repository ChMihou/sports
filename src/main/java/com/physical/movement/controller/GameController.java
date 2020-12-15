package com.physical.movement.controller;

import com.github.pagehelper.PageInfo;
import com.physical.movement.entity.Advisory;
import com.physical.movement.entity.Game;
import com.physical.movement.entity.SysUser;
import com.physical.movement.entity.Team;
import com.physical.movement.model.Paginator;
import com.physical.movement.model.ResultJson;
import com.physical.movement.service.GameService;
import com.physical.movement.service.MailService;
import com.physical.movement.service.SysUserService;
import com.physical.movement.service.TeamService;
import org.apache.shiro.session.Session;
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
@RequestMapping("game")
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    TeamService teamService;

    @Autowired
    MailService mailService;

    @RequestMapping("/gameAgreement")
    public ModelAndView teamManage(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request, HttpSession session, ModelAndView mv) {
        String check = request.getParameter("check");
        mv.addObject("check", check);
        String flag = request.getParameter("flag");
        mv.addObject("flag", flag);
        String key = request.getParameter("key");
        mv.addObject("key", key);
        int uid = (int) session.getAttribute("uid");
        Team team = new Team();
        if (check != null && !check.equals("0")) {
            team.setTeamtype(Integer.parseInt(check));
        }
        if (flag != null && !flag.equals("0")) {
            team.setTeamleaderid(uid);
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
        mv.setViewName("/game/gameAgreement");
        return mv;
    }

    @RequestMapping("/read-team")
    public ModelAndView readTeam(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Integer mid = Integer.valueOf(request.getParameter("id"));
        Team team = new Team();
        team.setTeamleaderid(mid);
        team = teamService.select(team);
        mv.addObject("team", team);
        mv.setViewName("/game/read-team");
        return mv;
    }

    @RequestMapping("/challenge-team")
    public ModelAndView challengeTeam(HttpServletRequest request, ModelAndView mv) {
        Integer mid = Integer.valueOf(request.getParameter("id"));
        Team team = new Team();
        team.setTeamleaderid(mid);
        team = teamService.select(team);
        SysUser sysUser = new SysUser();
        sysUser.setId(team.getTeamleaderid());
        sysUser = sysUserService.select(sysUser);
        mv.addObject("sysUser", sysUser);
        mv.addObject("team", team);
        mv.setViewName("/game/contact");
        return mv;
    }

    @RequestMapping("/sendGame")
    @ResponseBody
    public ResultJson sendGame(String message, String name, String email, String subject, Integer id, Session session) {
        Game game = new Game();
        boolean flag = mailService.sendWithHtml(email, subject, message);
        SysUser sysUser = (SysUser)session.getAttribute("sysUser");
        Team team = new Team();
        team.setTeamleaderid(sysUser.getId());
        team = teamService.select(team);
        if (team==null){
            return ResultJson.error("您不是队长，不可以约赛");
        }
        SysUser user = new SysUser();


        if (flag) {
            game.getChallenger();
            return ResultJson.success("添加成功");
        } else {
            return ResultJson.error("添加失败");
        }
    }

}
