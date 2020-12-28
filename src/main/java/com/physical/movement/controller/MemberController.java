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
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("member")
public class MemberController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SysUserService sysUserService;

    @Autowired
    TeamService teamService;

    @Autowired
    UserTeamRefService userTeamRefService;

    @RequestMapping("/member-alter")
    public ModelAndView memberalter(HttpSession session, ModelAndView mv) {
        SysUser user = (SysUser) session.getAttribute("sysUser");
        SysUser flag = new SysUser();
        flag.setId(user.getId());
        SysUser sysUser = sysUserService.select(flag);
        mv.addObject("sysUser", sysUser);
        mv.setViewName("/member/member-alter");
        return mv;
    }

    @RequestMapping("/memberAlter")
    @ResponseBody
    public ResultJson memberAlter(String username, String usex,
                                  String email, String mobile, HttpSession session) throws IOException {
        SysUser user = new SysUser();
        switch (usex) {
            case "1":
                usex = "男";
                break;
            case "2":
                usex = "女";
                break;
        }
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");
        user.setUsername(username);
        user.setId(sysUser.getId());
        user.setSex(usex);
        user.setEmail(email);
        user.setPhone(mobile);
        System.out.println(user.toString());
        int flag1 = sysUserService.updateByPrimaryKeySelective(user);
        if (flag1 > 0) {
            // 修改成功
            return ResultJson.success("修改成功");
        } else {
            // 修改失败
            return ResultJson.error("修改失败");
        }
    }

    @RequestMapping("/image")
    public ModelAndView image(Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("id", id);
        mv.setViewName("/member/image");
        return mv;
    }

    @RequestMapping("/member-list")
    public ModelAndView memberlist(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize, String search, String studentid, HttpServletRequest request, ModelAndView mv) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(search);
        sysUser.setStudentid(studentid);
        List<SysUser> sysUserList = sysUserService.selectAll(sysUser, pageNum, pageSize);
        if (CollectionUtils.isNotEmpty(sysUserList)) {
            PageInfo userolevolist = new PageInfo(sysUserList);
            List pagenums = new ArrayList();
            Paginator.page(pagenums, userolevolist, pageNum, pageSize);
            mv.addObject("pagenums", pagenums);
            mv.addObject("ulist", userolevolist);
        }
        mv.addObject("search", search);
        mv.addObject("studentid", studentid);
        mv.setViewName("/member/member-list");
        return mv;
    }

    @RequestMapping("/authority")
    public ModelAndView authorityList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize, String search, String studentid, HttpServletRequest request, ModelAndView mv) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(search);
        sysUser.setStudentid(studentid);
        List<SysUser> sysUserList = sysUserService.selectAll(sysUser, pageNum, pageSize);
        if (CollectionUtils.isNotEmpty(sysUserList)) {
            PageInfo userolevolist = new PageInfo(sysUserList);
            List pagenums = new ArrayList();
            Paginator.page(pagenums, userolevolist, pageNum, pageSize);
            mv.addObject("pagenums", pagenums);
            mv.addObject("ulist", userolevolist);
        }
        mv.addObject("search", search);
        mv.addObject("studentid", studentid);
        mv.setViewName("/member/authority");
        return mv;
    }

    @RequestMapping("/teamList")
    public ModelAndView affiliateManage(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request, HttpSession session, ModelAndView mv) {
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");
        UserTeamRef userTeamRef = new UserTeamRef();
        userTeamRef.setUserid(sysUser.getId());
        List<UserTeamVo> userTeamVoList = userTeamRefService.selectUserTeam(userTeamRef, pageNum, pageSize);
        PageInfo utlist = new PageInfo(userTeamVoList);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, utlist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("utlist", utlist);
        mv.setViewName("/member/team-list");
        return mv;
    }

    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson updateStatus(Integer id, Byte status) {
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setStatus(status);
        Integer flag = sysUserService.updateByPrimaryKeySelective(sysUser);
        if (flag > 0) {
            return ResultJson.success("操作成功");
        }
        return ResultJson.error("操作失败");
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson deleteUser(String id) throws IOException {
        Integer uid = Integer.valueOf(id);
        int flag = sysUserService.deleteByPrimaryKey(uid);
        if (flag > 0) {
            // 删除用户成功
            return ResultJson.success("删除用户成功");
        } else {
            // 删除用户失败
            return ResultJson.error("删除用户失败");
        }
    }

    @RequestMapping("/authority-alter")
    public ModelAndView authorityAlter(Integer id) {
        ModelAndView mv = new ModelAndView();
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser = sysUserService.select(sysUser);
        mv.addObject("sysUser", sysUser);
        mv.setViewName("/member/authority-alter");
        return mv;
    }

    @RequestMapping(value = "/power", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson poweRedirect(Byte dutyid, Integer id) {
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setUsertype(dutyid);
        int flag = sysUserService.updateByPrimaryKeySelective(sysUser);
        if (flag > 0) {
            return ResultJson.success("权限修改成功");
        } else {
            return ResultJson.error("权限修改失败");
        }
    }
}
