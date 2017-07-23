package com.junqiang.www.system.controller;

import com.junqiang.www.entity.User;
import com.junqiang.www.system.dao.UserDao;
import com.junqiang.www.system.service.AccountBiz;
import com.junqiang.www.system.service.UserBiz;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Resource(name = "userBizImpl")
    private UserBiz userBiz;
    private User user;

    @RequestMapping("login")
    public String login(HttpServletRequest req, Model model, HttpSession session) {
        logger.trace("login");
        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        String error = "";
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        System.out.printf(error);

        //TODO 这里以后可以把角色更换成资源控制后动态生成页面,（-->这里有疑问-->是不是可以使用自定义角色？shiro张开涛的16章有个自定义标签扫描出的角色）

        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        boolean isAuthenticated = subject.isAuthenticated();

        if (isAuthenticated) {
            String principal = (String) subject.getPrincipal();

            session.setAttribute("username", principal);
            System.out.print("登录："+ principal);

            user = userBiz.findByUsername(principal);

            System.out.printf("登录：%s", user.getRoleIdsStr());

            switch (user.getRoleIdsStr()) {
                case "1":
                    return "/admin/main";
                case "3":
                    return "/teacher/main";
                case "2":
                    return "/student/main";
                case "4":
                    return "redirect:supplier.do/supplier.view";
                default:
                    return "/unauthorized";
            }
        }

        return "redirect:login.jsp";
    }


}
