package com.physical.movement.controller;

import com.physical.movement.common.shiro.CustomerAuthenticationToken;
import com.physical.movement.entity.SysUser;
import com.physical.movement.model.ResultJson;
import com.physical.movement.service.SysUserService;
import com.physical.movement.utils.RandomValidateCode;
import com.physical.movement.utils.SendSms;
import com.physical.movement.utils.ShiroUtils;
import org.apache.commons.httpclient.HttpException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("login")
public class LoginController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SysUserService sysUserService;

    @RequestMapping("/login")
    public String index() {
        return "/login/login";
    }

    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/loginchecks")
    @ResponseBody
    public ResultJson logincheck(String Username, String Upassword, String checks, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        // 从session中获取随机数
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
        if (random != null) {
            if (!random.equalsIgnoreCase(checks)) {
                return ResultJson.error("验证码错误");
            } else {
                CustomerAuthenticationToken customerAuthenticationToken = new CustomerAuthenticationToken(Username, Upassword, false);
                Subject subject = SecurityUtils.getSubject();
                try {
                    subject.login(customerAuthenticationToken);
                } catch (UnknownAccountException uae) {
                    logger.info("对用户[" + Username + "]进行登录验证..验证未通过,未知账户");
                    return ResultJson.error("未知账户或账户未注册");
                } catch (IncorrectCredentialsException ice) {
                    logger.info("对用户[" + Username + "]进行登录验证..验证未通过,错误的凭证");
                    return ResultJson.error("密码不正确");
                } catch (ExcessiveAttemptsException eae) {
                    logger.info("对用户[" + Username + "]进行登录验证..验证未通过,错误次数过多");
                    return ResultJson.error("用户名或密码错误次数过多");
                } catch (AuthenticationException ae) {
                    //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
                    logger.info("对用户[" + Username + "]进行登录验证..验证未通过,堆栈轨迹如下");
                    ae.printStackTrace();
                    return ResultJson.error("用户名或密码不正确");
                }
                //验证是否登录成功
                if (subject.isAuthenticated()) {
                    SysUser sysUser = new SysUser();
                    sysUser.setUsername(Username);
                    SysUser user = sysUserService.select(sysUser);
                    session.setAttribute("sysUser", user);
                    session.setAttribute("uid", user.getId());
                    session.setAttribute("username", user.getUsername());
                    if (user.getStatus() == 0) {
                        return ResultJson.error("您的账户已停用，具体请首页留言联系管理员！");
                    }
                    logger.info("用户[" + Username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                }
                //登陆成功
                return ResultJson.success("登陆成功");
            }
        }
        return ResultJson.error("验证码错误");
    }


    //处理注册用户
    @RequestMapping("/adduser")
    @ResponseBody
    public ResultJson adduser(String username, String pass, String Usex,
                              String email, String mobile, String truename, String studentid) throws IOException {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        if (sysUserService.select(sysUser) != null) {
            // 用户名已被注册
            return ResultJson.error("用户名已被注册");
        } else {
            SysUser user = new SysUser();
            switch (Usex) {
                case "1":
                    Usex = "男";
                    break;
                case "2":
                    Usex = "女";
                    break;
            }
            // shiro 自带的工具类生成salt
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            String password = ShiroUtils.shiroEncryption(pass, salt);
            user.setSalt(salt);
            user.setTruename(truename);
            user.setStudentid(studentid);
            user.setImage("/images/now-logo.jpg");
            user.setUsername(username);
            user.setPassword(password);
            user.setStatus((byte) 1);
            user.setUsertype((byte) 0);
            user.setSex(Usex);
            user.setEmail(email);
            user.setPhone(mobile);
            Boolean flag1 = sysUserService.insert(user);
            if (flag1) {
                // 添加用户成功
                return ResultJson.success("添加用户成功");
            } else {
                // 添加用户失败
                return ResultJson.error("添加用户失败");
            }
        }
    }

    @RequestMapping(value = "/sendMe", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson sendme(String username, String mobile, HttpServletRequest request) throws HttpException, IOException {
        SysUser user = new SysUser();
        user.setUsername(username);
        user.setPhone(mobile);
        SysUser flag = sysUserService.select(user);
        if (flag != null) {
            HashMap<String, String> hashMap = SendSms.getMessageStatus(mobile);
            String result = hashMap.get("result");
            if (result.trim().equals("1")) {
                String code = hashMap.get("code");
                HttpSession session = request.getSession();
                session.setAttribute(mobile + "code", code);
                session.setMaxInactiveInterval(60 * 5);
                return ResultJson.success("验证码发送成功");
            } else {
                return ResultJson.error("验证码发送失败");
            }
        } else {
            return ResultJson.error("用户名或手机号码错误");
        }
    }

    @RequestMapping(value = "/comparecode", method = RequestMethod.POST)
    @ResponseBody
    public String comparecode(String username, String mobile, String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String sessionCode = (String) session.getAttribute(mobile + "code");
        if (sessionCode != null && code.equals(sessionCode)) {
            // 把comparecode放进session，用于判断请求修改密码页面时是否通过了手机验证
            session.setAttribute("comparecode", "1");
            session.setAttribute("username", username);   // 把修改密码的用户名放进session
            return "1";     // 验证码正确
        } else if (sessionCode != null && !code.equals(sessionCode)) {
            return "2";     // 验证码错误
        } else {
            return "3";     // 没有按发送验真码按钮
        }
    }

    @RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson updatepassword(String newpassword, String repassword, HttpServletRequest request) throws IOException {
        if (newpassword.equals(repassword)) {
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            SysUser sysUser = new SysUser();
            sysUser.setUsername(username);
            SysUser user = sysUserService.select(sysUser);
            user.setUsername(username);
            String password = ShiroUtils.shiroEncryption(newpassword, user.getSalt());
            user.setPassword(password);
            int flag = sysUserService.updateByPrimaryKeySelective(user);
            if (flag > 0) {
                session.invalidate();
                // 密码修改成功
                return ResultJson.success("密码修改成功");
            } else {
                System.out.println("未知错误");
                return ResultJson.error("未知错误");
            }
        } else {
            // 两次密码输入不一致
            return ResultJson.error("两次密码输入不一致");
        }
    }

    @RequestMapping("/member-add")
    public ModelAndView memberAdd(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/member-add");
        return mv;
    }

    @RequestMapping("/user-lostpassword")
    public ModelAndView lostPassword(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/user-lostpassword");
        return mv;
    }

}
