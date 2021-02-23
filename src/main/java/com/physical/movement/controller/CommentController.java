package com.physical.movement.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.physical.movement.entity.Comment;
import com.physical.movement.entity.SysUser;
import com.physical.movement.model.Paginator;
import com.physical.movement.model.ResultJson;
import com.physical.movement.service.CommentService;
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

@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping("/comment-list")
    public ModelAndView commentList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        ModelAndView mv = new ModelAndView();
        Comment comment = new Comment();
        List<Comment> comments = commentService.selectAll(comment, pageNum, pageSize);
        PageInfo clist = new PageInfo(comments);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, clist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("clist", clist);
        mv.setViewName("/comment/comment-list");
        return mv;
    }

    @RequestMapping("/apply-comment")
    public ModelAndView applycomment(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Integer id = Integer.valueOf(request.getParameter("id"));
        mv.addObject("id", id);
        mv.setViewName("/comment/apply-comment");
        return mv;
    }

    @RequestMapping("/submitApplyComment")
    @ResponseBody
    public ResultJson submitApplycomment(String apply, Integer id, HttpSession session) {
        Comment comment = new Comment();
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");
        comment.setCaboy(sysUser.getUsername());
        comment.setCflag(1);
        comment.setCapply(apply);
        comment.setCid(id);
        int a = commentService.updateByPrimaryKeySelective(comment);
        if (a > 0) {
            return ResultJson.success("回复成功");
        } else {
            return ResultJson.error("回复失败");
        }
    }

    @RequestMapping("deleteOneComment")
    @ResponseBody
    public ResultJson deleteOneComment(Integer id) {
        int i = commentService.deleteByPrimaryKey(id);
        if (i > 0) {
            return ResultJson.success("删除成功");
        }
        return ResultJson.error("删除失败");
    }

    @RequestMapping("deleteListComment")
    @ResponseBody
    public ResultJson deleteListComment(String ids) {
        String[] id = ids.split(",");
        int[] ints = new int[id.length];
        for (int i = 0; i < id.length; i++) {
            ints[i] = Integer.parseInt(id[i]);
        }
        int flag = commentService.deleteListId(ints);
        if (flag > 0) {
            return ResultJson.success("删除成功");
        }
        return ResultJson.error("删除失败");
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
