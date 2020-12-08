package com.physical.movement.controller;

import com.github.pagehelper.PageInfo;
import com.physical.movement.entity.Advisory;
import com.physical.movement.model.Paginator;
import com.physical.movement.model.ResultJson;
import com.physical.movement.service.AdvisoryService;
import com.physical.movement.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("advisory")
public class AdvisoryController {
    @Autowired
    SysUserService sysUserService;

    @Autowired
    AdvisoryService advisoryService;

    @RequestMapping("basketball")
    public ModelAndView basketball(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String key = request.getParameter("key");
        Advisory advisory = new Advisory();
        advisory.setTitle(key);
        advisory.setNselect(1);
        List<Advisory> advisories = advisoryService.selectAll(advisory, pageNum, pageSize);
        PageInfo nlist = new PageInfo(advisories);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, nlist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("nlist", nlist);
        mv.addObject("key", key);
        mv.setViewName("/advisory/basketball");
        return mv;
    }

    @RequestMapping("sports-all")
    public ModelAndView sportsAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request,ModelAndView mv) {
        String key = request.getParameter("key");
        Advisory advisory = new Advisory();
        advisory.setTitle(key);
        advisory.setNselect(2);
        List<Advisory> advisories = advisoryService.selectAll(advisory, pageNum, pageSize);
        PageInfo nlist = new PageInfo(advisories);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, nlist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("nlist", nlist);
        mv.addObject("key", key);
        mv.setViewName("/advisory/sportsAll");
        return mv;
    }
    @RequestMapping("football")
    public ModelAndView football(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String key = request.getParameter("key");
        Advisory advisory = new Advisory();
        advisory.setTitle(key);
        advisory.setNselect(3);
        List<Advisory> advisories = advisoryService.selectAll(advisory, pageNum, pageSize);
        PageInfo nlist = new PageInfo(advisories);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, nlist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("nlist", nlist);
        mv.addObject("key", key);
        mv.setViewName("/advisory/football");
        return mv;
    }
    @RequestMapping("runSports")
    public ModelAndView runSports(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String key = request.getParameter("key");
        Advisory advisory = new Advisory();
        advisory.setTitle(key);
        advisory.setNselect(4);
        List<Advisory> advisories = advisoryService.selectAll(advisory, pageNum, pageSize);
        PageInfo nlist = new PageInfo(advisories);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, nlist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("nlist", nlist);
        mv.addObject("key", key);
        mv.setViewName("/advisory/runsports");
        return mv;
    }
    @RequestMapping("game")
    public ModelAndView game(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String key = request.getParameter("key");
        Advisory advisory = new Advisory();
        advisory.setTitle(key);
        advisory.setNselect(5);
        List<Advisory> advisories = advisoryService.selectAll(advisory, pageNum, pageSize);
        PageInfo nlist = new PageInfo(advisories);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, nlist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("nlist", nlist);
        mv.addObject("key", key);
        mv.setViewName("/advisory/game");
        return mv;
    }

    @RequestMapping("deleteAdvisory")
    @ResponseBody
    public ResultJson deleteAdvisory(Integer id) {
        int i = advisoryService.deleteByPrimaryKey(id);
        if (i > 0) {
            return ResultJson.success("删除成功");
        }
        return ResultJson.error("删除失败");
    }

    @RequestMapping("deleteListAdvisory")
    @ResponseBody
    public ResultJson deleteListAdvisory(String ids) {
        String[] id = ids.split(",");
        int[] ints = new int[id.length];
        for (int i = 0; i < id.length; i++) {
            ints[i] = Integer.parseInt(id[i]);
        }
        int flag = advisoryService.deleteListId(ints);
        if (flag > 0) {
            return ResultJson.success("删除成功");
        }
        return ResultJson.error("删除失败");
    }

    @RequestMapping("articleAdd")
    public ModelAndView articleAdd(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Integer nselect = Integer.valueOf(request.getParameter("nselect"));
        mv.addObject("nselect", nselect);
        mv.setViewName("/advisory/addAdvisory");
        return mv;
    }

    @RequestMapping("addArticle")
    @ResponseBody
    public ResultJson addArticle(String n_title, String n_article, String n_author, Integer n_select, String images, String nintro) {
        Advisory advisory = new Advisory();
        advisory.setTitle(n_title);
        advisory.setArticle(n_article);
        advisory.setAuthor(n_author);
        advisory.setIntro(nintro);
        advisory.setNselect(n_select);
        advisory.setNimage(images);
        boolean flag = advisoryService.insert(advisory);
        if (flag) {
            return ResultJson.success("添加成功");
        } else {
            return ResultJson.error("添加失败");
        }
    }

    @RequestMapping("editArticle")
    public ModelAndView editArticle(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        int id = Integer.parseInt(request.getParameter("id"));
        Advisory advisory = new Advisory();
        advisory.setId(id);
        advisory = advisoryService.select(advisory);
        mv.addObject("advisory", advisory);
        mv.setViewName("/advisory/edit-article");
        return mv;
    }

    @RequestMapping("updateArticle")
    @ResponseBody
    public ResultJson updateArticle(Integer n_id, String n_title, String n_article, String n_author, String images, String ninro) {
        ModelAndView mv = new ModelAndView();
        Advisory advisory = new Advisory();
        advisory.setId(n_id);
        advisory.setTitle(n_title);
        advisory.setArticle(n_article);
        advisory.setAuthor(n_author);
        advisory.setIntro(ninro);
        advisory.setNimage(images);
        int i = advisoryService.updateByPrimaryKeySelective(advisory);
        mv.addObject("id", advisory.getId());
        if (i > 0)
            return ResultJson.success("更新成功");
        else
            return ResultJson.error("修改失败");
    }

    @RequestMapping("readArticle")
    public ModelAndView readArticle(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Integer id = Integer.valueOf(request.getParameter("id"));
        Advisory advisory = new Advisory();
        advisory.setId(id);
        advisory = advisoryService.select(advisory);
        mv.addObject("notice", advisory);
        mv.setViewName("/advisory/read-article");
        return mv;
    }
}
