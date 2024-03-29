package com.physical.movement.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.physical.movement.entity.Advisory;
import com.physical.movement.model.Paginator;
import com.physical.movement.model.ResultJson;
import com.physical.movement.service.AdvisoryService;
import com.physical.movement.service.SysUserService;
import com.physical.movement.utils.RandomUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章管理
 */
@Controller
@RequestMapping("advisory")
public class AdvisoryController {
    @Autowired
    SysUserService sysUserService;

    @Autowired
    AdvisoryService advisoryService;

    @RequestMapping("wonderful")
    public ModelAndView wonderful(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String key = request.getParameter("key");
        Advisory advisory = new Advisory();
        advisory.setTitle(key);
        advisory.setWonderful(1);
        List<Advisory> advisories = advisoryService.selectAll(advisory, pageNum, pageSize);
        PageInfo nlist = new PageInfo(advisories);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, nlist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("nlist", nlist);
        mv.addObject("key", key);
        mv.setViewName("/advisory/wonderful");
        return mv;
    }

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
    public ModelAndView sportsAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request, ModelAndView mv) {
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
        mv.setViewName("/advisory/sportsAll");
        return mv;
    }

    @RequestMapping("football")
    public ModelAndView football(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
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
        mv.setViewName("/advisory/football");
        return mv;
    }

    @RequestMapping("runSports")
    public ModelAndView runSports(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request) {
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
        mv.setViewName("/advisory/runsports");
        return mv;
    }

    @RequestMapping("game")
    public ModelAndView game(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request) {
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

    @RequestMapping("wonderfulAdd")
    @ResponseBody
    public ResultJson wonderfulAdd(Integer id) {
        Advisory advisory = new Advisory();
        advisory.setId(id);
        advisory.setWonderful(1);
        int i = advisoryService.updateByPrimaryKeySelective(advisory);
        if (i > 0) {
            return ResultJson.success("添加成功");
        }
        return ResultJson.error("添加失败");
    }

    @RequestMapping("wonderfulDel")
    @ResponseBody
    public ResultJson wonderfulDel(Integer id) {
        Advisory advisory = new Advisory();
        advisory.setId(id);
        advisory.setWonderful(0);
        int i = advisoryService.updateByPrimaryKeySelective(advisory);
        if (i > 0) {
            return ResultJson.success("撤消成功");
        }
        return ResultJson.error("撤消失败");
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
        int id;
        if (request.getParameter("id") == null) {
            id = 1;
        } else {
            id = Integer.parseInt(request.getParameter("id"));
        }
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

    @RequestMapping(value = "/upload", produces = {"text/html;charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public Object uploadReportFile(@RequestParam(value = "myFileName", required = false) MultipartFile cardFile,
                                   HttpServletRequest request, HttpSession session) {
        String path = Class.class.getClass().getResource("/").getPath();
        path = path + "static" + File.separator + "uploadfiles";


        Map<String, String> map = new HashMap<String, String>();
        if (cardFile != null) {
            String oldFileName = cardFile.getOriginalFilename();

            String prefix = FilenameUtils.getExtension(oldFileName);

            if (cardFile.getSize() > 10000000) {
                return "1";
            } else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                    || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) {
                String fileName = System.currentTimeMillis() + RandomUtil.getRandNum() + "_IDcard.jpg";
                File targetFile = new File(path, fileName);
                // 检测是否存在目录
                if (!targetFile.getParentFile().exists()) {
                    targetFile.getParentFile().mkdirs();
                }

                try {

                    cardFile.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String url = request.getContextPath() + "/uploadfiles/" + fileName;
                map.put("data", url);
                return JSONArray.toJSONString(map);

            } else {
                return "2";
            }
        }

        return null;
    }
}
