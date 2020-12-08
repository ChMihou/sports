package com.physical.movement.controller;

import com.github.pagehelper.PageInfo;
import com.physical.movement.entity.Announcement;
import com.physical.movement.model.Paginator;
import com.physical.movement.model.ResultJson;
import com.physical.movement.service.AnnouncementService;
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

@Controller
@RequestMapping("announcement")
public class AnnouncementController {
    @Autowired
    AnnouncementService announcementService;

    @RequestMapping("announcement")
    public ModelAndView announcement(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String key = request.getParameter("key");
        Announcement announcement = new Announcement();
        announcement.setTitle(key);
        List<Announcement> announcements = announcementService.selectAll(announcement, pageNum, pageSize);
        PageInfo nlist = new PageInfo(announcements);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, nlist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("nlist", nlist);
        mv.addObject("key", key);
        mv.setViewName("/announcement/announcement");
        return mv;
    }

    @RequestMapping("deleteAnnouncement")
    @ResponseBody
    public ResultJson deleteAnnouncement(Integer id) {
        int i = announcementService.deleteByPrimaryKey(id);
        if (i > 0) {
            return ResultJson.success("删除成功");
        }
        return ResultJson.error("删除失败");
    }

    @RequestMapping("deleteListAnnouncement")
    @ResponseBody
    public ResultJson deleteListAnnouncement(String ids) {
        String[] id = ids.split(",");
        int[] ints = new int[id.length];
        for (int i = 0; i < id.length; i++) {
            ints[i] = Integer.parseInt(id[i]);
        }
        int flag = announcementService.deleteListId(ints);
        if (flag > 0) {
            return ResultJson.success("删除成功");
        }
        return ResultJson.error("删除失败");
    }

    @RequestMapping("announcementAdd")
    public ModelAndView announcementAdd(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/announcement/addAnnouncement");
        return mv;
    }

    @RequestMapping("addAnnouncement")
    @ResponseBody
    public ResultJson addAnnouncement(String n_title, String n_article, String n_author, String images, HttpSession session) {
        Announcement announcement = new Announcement();
        announcement.setTitle(n_title);
        announcement.setArticle(n_article);
        announcement.setAuthor(n_author);
        int uid = (int) session.getAttribute("uid");
        announcement.setId(uid);
        announcement.setImage(images);
        boolean flag = announcementService.insert(announcement);
        if (flag) {
            return ResultJson.success("添加成功");
        } else {
            return ResultJson.error("添加失败");
        }
    }

    @RequestMapping("editAnnouncement")
    public ModelAndView editAnnouncement(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        int id = Integer.parseInt(request.getParameter("id"));
        Announcement announcement = new Announcement();
        announcement.setId(id);
        announcement = announcementService.select(announcement);
        mv.addObject("advisory", announcement);
        mv.setViewName("/announcement/edit-announcement");
        return mv;
    }

    @RequestMapping("updateAnnouncement")
    @ResponseBody
    public ResultJson updateAnnouncement(Integer n_id, String n_title, String n_article, String n_author, String images, String ninro) {
        ModelAndView mv = new ModelAndView();
        Announcement announcement = new Announcement();
        announcement.setId(n_id);
        announcement.setTitle(n_title);
        announcement.setArticle(n_article);
        announcement.setAuthor(n_author);
        announcement.setImage(images);
        int i = announcementService.updateByPrimaryKeySelective(announcement);
        mv.addObject("id", announcement.getId());
        if (i > 0)
            return ResultJson.success("更新成功");
        else
            return ResultJson.error("修改失败");
    }

    @RequestMapping("readAnnouncement")
    public ModelAndView readAnnouncement(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Integer id = Integer.valueOf(request.getParameter("id"));
        Announcement announcement = new Announcement();
        announcement.setId(id);
        announcement = announcementService.select(announcement);
        mv.addObject("notice", announcement);
        mv.setViewName("/announcement/read-announcement");
        return mv;
    }
}
