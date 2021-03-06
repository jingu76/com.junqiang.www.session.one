package com.junqiang.www.college.controller;

import com.junqiang.www.college.service.ClazzBiz;
import com.junqiang.www.util.TermContainer;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("clazz.do")
public class ClazzController {

    @Resource(name = "clazzBizImpl")
    private ClazzBiz clazzBiz;

    @RequiresRoles("admin")
    @RequestMapping("add")
    public String add(String compName, String deptName, String teamName) {

        System.out.print(compName+":"+deptName+":"+teamName);
        clazzBiz.add(compName, deptName, teamName);
        return "redirect:/clazz.do/clazz.view";
    }

    @RequiresRoles("admin")
    @RequestMapping("delete")
    public String delete(String teamName) {
        clazzBiz.delete(teamName);
        return "redirect:/clazz.do/clazz.view";
    }
//    public String delete(int clazzId) {
//        clazzBiz.delete(clazzId);
//        return "redirect:/clazz.do/clazz.view";
//    }

    @RequiresRoles("admin")
    @RequestMapping("clazz.view")
    public String findAll(Model m) {
        m.addAttribute("clazzList", clazzBiz.findAll());
        return "/admin/college/clazz";
    }

    @RequiresRoles("admin")
    @RequestMapping("clazz_add.view")
    public String findDeptAndSpec(Model m) {
        m.addAttribute("deptAndSpecJson", clazzBiz.findDeptAndSpecJson());
        m.addAttribute("deptNameList", clazzBiz.findDeptNameList());
        m.addAttribute("termList", TermContainer.getTermList());
        return "/admin/college/clazz_add";
    }
}
