package com.junqiang.www.system.controller;

import com.junqiang.www.system.service.AccountBiz;
import com.junqiang.www.system.service.UserBiz;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.junqiang.www.entity.User;

/**
 * Created by c0de8ug on 16-2-16.
 */
@Controller

@RequestMapping("account.do")
public class AccountController {
    private static Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Resource(name = "accountBizImpl")
    private AccountBiz accountBiz;
    @Resource(name="userBizImpl")
    private UserBiz userBiz;

    private String username;

    @RequiresAuthentication
    @RequestMapping("profile.view")
    public String profileView() {
        logger.trace("profileView");
        return "/admin/system/account/profile";

    }

    @RequiresAuthentication
    @RequestMapping("update")
    public String update(HttpSession session, String password, String oldPassword, String password2) {
        logger.trace("update");
        User user;
        String id = (String) session.getAttribute("username");
        user = accountBiz.findById(id);
        System.out.print(user+"oldpasswd:"+oldPassword);

        if (password2==password){
            if (user.getPassword()==oldPassword) {
                userBiz.changePassword(id, password);
                //System.out.print(user.getSalt());
                //  accountBiz.updatePassword(id, password);
            }else{
                logger.error("原密码错误");
                System.out.print("原密码错误");
            }
        }else{
            logger.error("两次输入新密码不相等");
            System.out.print("两次输入新密码不相等");
        }
        return "redirect:/account.do/profile.view";
    }
}
