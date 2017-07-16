package com.junqiang.www.college.controller;

import com.junqiang.www.college.service.ClazzBiz;
import com.junqiang.www.util.TermContainer;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by c0de8ug on 16-2-11.
 */
@Controller
@RequestMapping("clazz.do")
public class ClazzController {
    Logger logger = LoggerFactory.getLogger(ClazzController.class);

    @Resource(name = "clazzBizImpl")
    private ClazzBiz clazzBiz;

    @RequiresRoles("admin")
    @RequestMapping("add")
    public String add(String deptName, String specName, String teamName) {

        logger.trace("add:"+deptName+":"+specName+":"+teamName);
        clazzBiz.add(deptName, specName, teamName);
        return "redirect:/clazz.do/clazz.view";
    }

    @RequiresRoles("admin")
    @RequestMapping("delete")
    public String delete(int clazzId) {
        logger.trace("delete:"+" clazzId:"+clazzId);
        clazzBiz.delete(clazzId);
        return "redirect:/clazz.do/clazz.view";
    }

    @RequiresRoles("admin")
    @RequestMapping("clazz.view")
    public String findAll(Model m) {
        logger.trace("findAll:"+" Model:"+m.toString());
        List<Class> classList = clazzBiz.findAll();
        m.addAttribute("clazzList", classList);
        return "/admin/college/clazz";
    }

    @RequiresRoles("admin")
    @RequestMapping("clazz_add.view")
    public String findDeptAndSpec(Model m) {
        logger.trace("findDeptAndSpec:"+" Model:"+m.toString());
        m.addAttribute("deptAndSpecJson", clazzBiz.findDeptAndSpecJson());
        m.addAttribute("deptNameList", clazzBiz.findDeptNameList());
        m.addAttribute("termList", TermContainer.getTermList());
        return "/admin/college/clazz_add";
    }
}
