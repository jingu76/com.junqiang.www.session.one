package com.junqiang.www.system.controller;

import com.junqiang.www.entity.User;
import com.junqiang.www.system.service.RoleBiz;
import com.junqiang.www.system.service.UserBiz;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by c0de8ug on 16-2-9.
 */

@Controller
@RequiresRoles("admin")
@RequestMapping("user.do")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource(name = "userBizImpl")
    private UserBiz userBiz;

    @Resource(name = "roleBizImpl")
    private RoleBiz roleBiz;

    @RequiresRoles("admin")
    @RequestMapping("user.view")
    public String userView(Model m) throws InvocationTargetException, IllegalAccessException {
        logger.trace("userView");
        m.addAttribute("userList", userBiz.findAll());
        return "/admin/system/user/user";
    }

    @RequiresRoles("admin")
    @RequestMapping("user_add.view")
    public String userAddView(Model m) {
        logger.trace("userAddView");
        m.addAttribute("roleList", roleBiz.findAll());
        return "/admin/system/user/user_add";
    }

    @RequiresRoles("admin")
    @RequestMapping("findById")
    public String findById(String id, Model m) {
        logger.trace("findById:"+id);
        //todo 这里要做非空判断
        m.addAttribute("user", userBiz.findById(id));
        return "/admin/system/user/user_update";
    }

    @RequiresRoles("admin")
    @RequestMapping("update")
    public String update(User user) {
        logger.trace("update:"+user.getUsername());
        userBiz.update(user);
        return "redirect:/user.do/user.view";
    }

    @RequiresRoles("admin")
    @RequestMapping("add")
    public String add(User user) {
        logger.trace("add");
        userBiz.add(user);
        return "redirect:/user.do/user.view";
    }

    @RequiresRoles("admin")
    @RequestMapping("delete")
    public String delete(String id) {
        logger.trace("delete");
        userBiz.delete(id);
        return "redirect:/user.do/user.view";
    }

}
