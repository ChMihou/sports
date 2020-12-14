package com.physical.movement.controller;

import com.github.pagehelper.PageInfo;
import com.physical.movement.entity.Message;
import com.physical.movement.entity.SysUser;
import com.physical.movement.model.Paginator;
import com.physical.movement.model.ResultJson;
import com.physical.movement.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping("/message")
    public ModelAndView message(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request, HttpSession session) {
        ModelAndView model = new ModelAndView();
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");
        Integer flag;
        String check = request.getParameter("check");
        model.addObject("check", check);
        if (request.getParameter("check") == "" || request.getParameter("check") == null) {
            flag = null;
        } else {
            flag = Integer.valueOf(request.getParameter("check"));
        }
        Message message = new Message();
        String key = request.getParameter("key");
        int uid = (int) session.getAttribute("uid");
        model.addObject("key", key);
        message.setTitle(key);
        message.setFlag(flag);
        if (sysUser.getUsertype() == 0) {
            message.setUid(uid);
        }
        List<Message> messageList = messageService.selectAll(message, pageNum, pageSize);
        PageInfo mlist = new PageInfo(messageList);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, mlist, pageNum, pageSize);
        model.addObject("pagenums", pagenums);
        model.addObject("mlist", mlist);
        model.setViewName("/message/message");
        return model;
    }

    @RequestMapping("/add-message")
    public ModelAndView addmessage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/message/add-message");
        return mv;
    }

    @RequestMapping("/addMessage")
    @ResponseBody
    public ResultJson addMessage(String n_title, String n_article, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String Mname = (String) session.getAttribute("username");
        Date date = new Date();
        Timestamp mtime = new Timestamp(date.getTime());
        Message message = new Message();
        message.setTitle(n_title);
        message.setName(Mname);
        message.setFlag(0);
        message.setMess(n_article);
        Boolean i = messageService.insert(message);
        if (i)
            return ResultJson.success("添加成功");
        else
            return ResultJson.error("添加失败");
    }

    @RequestMapping("/read-message")
    public ModelAndView readmessage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Integer mid = Integer.valueOf(request.getParameter("id"));
        Message message = new Message();
        message.setUid(mid);
        message = messageService.select(message);
        mv.addObject("message", message);
        mv.setViewName("/message/read-message");
        return mv;
    }

    @RequestMapping("/apply-message")
    public ModelAndView applymessage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Integer mid = Integer.valueOf(request.getParameter("id"));
        Message message = new Message();
        message.setUid(mid);
        message = messageService.select(message);
        mv.addObject("message", message);
        mv.setViewName("/message/apply-message");
        return mv;
    }

    @RequestMapping("/submitApply")
    @ResponseBody
    public ResultJson submitApply(String mess, Integer mid, HttpSession session) {
        Message message = new Message();
        String Mcheckboy = (String) session.getAttribute("username");
        message.setId(mid);
        message.setFlag(1);
        message.setMess(mess);
        message.setCheckboy(Mcheckboy);
        int a = messageService.updateByPrimaryKeySelective(message);
        if (a > 0) {
            return ResultJson.success("回复成功");
        } else {
            return ResultJson.error("回复失败");
        }
    }
}
