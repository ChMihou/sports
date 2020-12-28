package com.physical.movement.controller;

import com.github.pagehelper.PageInfo;
import com.physical.movement.entity.SysUser;
import com.physical.movement.entity.Team;
import com.physical.movement.entity.UserTeamRef;
import com.physical.movement.entity.vo.UserTeamVo;
import com.physical.movement.model.Paginator;
import com.physical.movement.model.ResultJson;
import com.physical.movement.service.SysUserService;
import com.physical.movement.service.TeamService;
import com.physical.movement.service.UserTeamRefService;
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
@RequestMapping("team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @Autowired
    UserTeamRefService userTeamRefService;

    @Autowired
    SysUserService sysUserService;

    @RequestMapping("/teamManage")
    public ModelAndView teamManage(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request, HttpSession session, ModelAndView mv) {
        String check = request.getParameter("check");
        mv.addObject("check", check);
        String flag = request.getParameter("flag");
        mv.addObject("flag", flag);
        String key = request.getParameter("key");
        mv.addObject("key", key);
        int uid = (int) session.getAttribute("uid");
        Team team = new Team();
        if (check != null && !check.equals("0") && !check.equals("")) {
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
        mv.setViewName("/team/teamManage");
        return mv;
    }

    @RequestMapping("/teamBuild")
    public ModelAndView teamBuild(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request, HttpSession session, ModelAndView mv) {
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");
        String check = request.getParameter("check");
        mv.addObject("check", check);
        String key = request.getParameter("key");
        mv.addObject("key", key);
        int uid = (int) session.getAttribute("uid");
        Team team = new Team();
        if (check != null && !check.equals("0") && !check.equals("")) {
            team.setTeamtype(Integer.parseInt(check));
        }
        if (sysUser.getUsertype() == 0) {
            team.setTeamleaderid(uid);
        }
        team.setTeamname(key);
        team.setFlag((byte) 0);
        List<Team> teamList = teamService.selectAll(team, pageNum, pageSize);
        PageInfo tlist = new PageInfo(teamList);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, tlist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("tlist", tlist);
        mv.addObject("SportsType", STATUS_MAP);
        mv.setViewName("/team/teamBuild");
        return mv;
    }

    @RequestMapping("deleteOneTeam")
    @ResponseBody
    public ResultJson deleteAdvisory(Integer id) {
        int i = teamService.deleteByPrimaryKey(id);
        if (i > 0) {
            return ResultJson.success("删除成功");
        }
        return ResultJson.error("删除失败");
    }

    @RequestMapping("/read-team")
    public ModelAndView readTeam(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Integer mid = Integer.valueOf(request.getParameter("id"));
        Team team = new Team();
        team.setTeamleaderid(mid);
        team = teamService.select(team);
        mv.addObject("team", team);
        mv.setViewName("/team/read-team");
        return mv;
    }

    @RequestMapping("/apply-team")
    public ModelAndView applymessage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Integer mid = Integer.valueOf(request.getParameter("id"));
        Team team = new Team();
        team.setId(mid);
        team = teamService.select(team);
        mv.addObject("team", team);
        mv.setViewName("/team/apply-team");
        return mv;
    }

    @RequestMapping("/submitApply")
    @ResponseBody
    public ResultJson submitApply(String intro, Integer mid, HttpSession session) {
        Team team = new Team();
        team.setId(mid);
        team.setIntro(intro);
        int a = teamService.updateByPrimaryKeySelective(team);
        if (a > 0) {
            return ResultJson.success("修改成功");
        } else {
            return ResultJson.error("修改失败");
        }
    }

    @RequestMapping("/add-team")
    public ModelAndView addTeam(ModelAndView mv) {
        mv.addObject("SportsType", STATUS_MAP);
        mv.setViewName("/team/add-team");
        return mv;
    }

    @RequestMapping("/addTeam")
    @ResponseBody
    public ResultJson addTeam(String n_title, String n_article, Integer check, HttpServletRequest request) {
        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");
        Team team = new Team();
        Team flagTeam = new Team();
        flagTeam.setTeamleaderid(sysUser.getId());
        flagTeam = teamService.select(team);
        if (flagTeam != null) {
            return ResultJson.error("一个人只能拥有一支队伍或者您申请的队伍正在审核中，请勿重复申请");
        }
        team.setTeamname(n_title);
        if (teamService.select(team) != null) {
            return ResultJson.error("队伍名称已存在");
        }
        team.setTeamleaderid(sysUser.getId());
        team.setFlag((byte) 0);
        team.setCause(n_article);
        team.setTeamleader(sysUser.getUsername());
        team.setTeamemail(sysUser.getEmail());
        if (check != null && !check.equals("0") && !check.equals("")) {
            team.setTeamtype(check);
        } else {
            team.setTeamtype(5);
        }
        Boolean i = teamService.insert(team);
        if (i)
            return ResultJson.success("添加成功");
        else
            return ResultJson.error("添加失败");
    }

    @RequestMapping("/check-team")
    public ModelAndView checkTeam(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Team team = new Team();
        team.setId(Integer.valueOf(request.getParameter("id")));
        team = teamService.select(team);
        mv.addObject("team", team);
        mv.setViewName("/team/check-team");
        return mv;
    }

    @RequestMapping("/submitCheck")
    @ResponseBody
    public ResultJson submitCheck(int nid, int n_flag, String n_cause, HttpSession session) {
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");
        UserTeamRef userTeamRef = new UserTeamRef();
        userTeamRef.setUserid(sysUser.getId());
        userTeamRef.setTeamid(nid);
        userTeamRefService.insert(userTeamRef);
        Team team = new Team();
        team.setId(nid);
        if (n_flag == 1) {
            team.setFlag((byte) 1);
        } else {
            team.setFlag((byte) 0);
            team.setReason(n_cause);
        }
        int a = teamService.updateByPrimaryKeySelective(team);
        if (a > 0) {
            return ResultJson.success("审核成功");
        } else {
            return ResultJson.error("审核失败");
        }
    }

    @RequestMapping("/affiliateManage")
    public ModelAndView affiliateManage(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request, HttpSession session, ModelAndView mv) {
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");
        UserTeamRef userTeamRef = new UserTeamRef();
        Team team = new Team();
        team.setTeamleaderid(sysUser.getId());
        team.setFlag((byte) 0);
        team = teamService.select(team);
        if (team != null) {
            userTeamRef.setTeamid(team.getId());
        }
        userTeamRef.setUserid(sysUser.getId());
        List<UserTeamVo> userTeamVoList = userTeamRefService.selectUserTeam(userTeamRef, pageNum, pageSize);
        PageInfo utlist = new PageInfo(userTeamVoList);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, utlist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("utlist", utlist);
        mv.setViewName("/team/affiliateManage");
        return mv;
    }

    @RequestMapping("/add-userTeam")
    public ModelAndView addUserTeam(ModelAndView mv) {
        mv.setViewName("/team/add-userteam");
        return mv;
    }

    @RequestMapping("/deleteOneUserTeam")
    @ResponseBody
    public ResultJson deleteOneUserTeam(Integer id) {
        int i = userTeamRefService.deleteByPrimaryKey(id);
        if (i > 0) {
            return ResultJson.success("删除成功");
        }
        return ResultJson.error("删除失败");
    }

    @RequestMapping("/addTeammate")
    @ResponseBody
    public ResultJson addTeammate(String n_title, HttpServletRequest request) {
        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");
        SysUser user = new SysUser();
        user.setUsername(n_title);
        Team team = new Team();
        team.setTeamleaderid(sysUser.getId());
        team = teamService.select(team);
        user = sysUserService.select(user);
        if (team == null) {
            return ResultJson.error("您还没有队伍，请申请一支队伍吧");
        } else if (user == null) {
            return ResultJson.error("系统查无此人");
        }
        UserTeamRef userTeamRef = new UserTeamRef();
        userTeamRef.setTeamid(team.getId());
        userTeamRef.setUserid(sysUser.getId());
        Boolean i = userTeamRefService.insert(userTeamRef);
        if (i)
            return ResultJson.success("添加成功");
        else
            return ResultJson.error("添加失败");
    }
}
