package com.junqiang.www.student.controller;

import com.junqiang.www.student.service.ElectiveBiz;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by c0de8ug on 16-2-16.
 */
@Controller

@RequestMapping("elective.do")
public class ElectiveController {
    private  static Logger logger = LoggerFactory.getLogger(ElectiveController.class);
    @Resource(name = "electiveBizImpl")
    private ElectiveBiz electiveBiz;

    @RequiresRoles(value = {"admin", "student"}, logical = Logical.OR)
    @RequestMapping("elective.view")
    public String electiveView(Model m) {
        logger.trace("electiveView");
        m.addAttribute("sectionList", electiveBiz.findAllSection());
        return "/student/elective";
    }

    @RequiresRoles(value = {"admin", "student"}, logical = Logical.OR)
    @RequestMapping("add")
    public String add(int secId, HttpSession session) {
        logger.trace("add");
        String stdId = (String) session.getAttribute("username");
        electiveBiz.add(secId, stdId);
        return "redirect:/elective.do/elective.view";
    }

    @RequiresRoles(value = {"admin", "student"}, logical = Logical.OR)
    @RequestMapping("delete")
    public String delete(int secId, HttpSession session) {
        logger.trace("delete");
        String stdId = (String) session.getAttribute("username");
        electiveBiz.delete(secId, stdId);
        return "redirect:/elective.do/elective.view";
    }

}
