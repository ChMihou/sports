package com.physical.movement.controller;

import com.physical.movement.common.LoginContext;
import com.physical.movement.common.shiro.CustomerAuthenticationToken;
import com.physical.movement.entity.SysUser;
import com.physical.movement.model.ResultJson;
import com.physical.movement.service.SysUserService;
import com.physical.movement.utils.RandomValidateCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("login")
public class LoginController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SysUserService sysUserService;

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

    @RequestMapping("loginchecks")
    @ResponseBody
    public ResultJson logincheck(String Username, String Upassword, String checks, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        SysUser sysUser = new SysUser();

        sysUser.setPassword(Upassword);

        sysUser.setUsername(Username);

        // 从session中获取随机数
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");

        SysUser user = sysUserService.select(sysUser);
        if (!random.equalsIgnoreCase(checks)) {
            return ResultJson.error("验证码错误");
        } else if (user != null && random.equalsIgnoreCase(checks)) {

            CustomerAuthenticationToken customerAuthenticationToken = new CustomerAuthenticationToken(Username, Upassword, false);
            Subject subject = SecurityUtils.getSubject();
            try {
                subject.login(customerAuthenticationToken);
            } catch (UnknownAccountException uae) {
                logger.info("对用户[" + Username + "]进行登录验证..验证未通过,未知账户");
                return ResultJson.error("未知账户");
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
                Session session1 = SecurityUtils.getSubject().getSession();
                System.out.println(session1.getTimeout());
                if (user.getStatus() == 0) {
                    return ResultJson.error("您的账户已停用，具体请首页留言联系管理员！");
                }
                LoginContext.doLogin(user, session);
                logger.info("用户[" + Username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                //登陆成功
                return ResultJson.success("登陆成功");
            }
        }
        return ResultJson.error("登陆失败");  //登陆成功
    }
}
