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
    public String update(HttpSession session, String password) {
        logger.trace("update");
        String id = (String) session.getAttribute("username");
        User user;

        System.out.print(id);
        System.out.print("....."+password +"\n\r");
        user = accountBiz.findById(id);
        userBiz.changePassword(id, password);
        //System.out.print(user.getSalt());
      //  accountBiz.updatePassword(id, password);
        return "redirect:/account.do/profile.view";
    }
}
