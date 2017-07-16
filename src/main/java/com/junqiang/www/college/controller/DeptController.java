package com.junqiang.www.college.controller;

import com.junqiang.www.college.service.DeptBiz;
import com.junqiang.www.entity.Dept;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;


@Controller
@RequestMapping("dept.do")
public class DeptController {
    Logger logger = LoggerFactory.getLogger(DeptController.class);
    @Resource(name = "deptBizImpl")
    private DeptBiz deptBiz;

    @RequiresRoles("admin")
    @RequestMapping("dept.view")
    public String deptView(Model m) {
        logger.trace("deptView:"+m.toString());
        m.addAttribute("deptList", deptBiz.findAll());
        return "/admin/college/dept";
    }

    @RequiresRoles("admin")
    @RequestMapping("dept_add.view")
    public String deptAddView(Model m) {
        logger.trace("deptAddView:"+m.toString());
        return "/admin/college/dept_add";
    }

    @RequiresRoles("admin")
    @RequestMapping("dept_update.view")
    public String deptUpdateView(Model m) {
        logger.trace("deptUpdateView:"+m.toString());
        return "/admin/college/dept_update";
    }

    @RequiresRoles("admin")
    @RequestMapping("add")
    public String add(String deptName) {
        logger.trace("add:" + deptName);
        deptBiz.add(deptName);
        return "redirect:/dept.do/dept.view";
    }

    @RequiresRoles("admin")
    @RequestMapping("update")
    public String update(Dept dept) {
        logger.trace("update:"+dept.getDeptName());
        deptBiz.update(dept);
        return "redirect:/dept.do/dept.view";
    }

    @RequiresRoles("admin")
    @RequestMapping("delete")
    public String delete(int deptId) {
        logger.trace("delete:" + deptId);
        deptBiz.delete(deptId);
        return "redirect:/dept.do/dept.view";
    }


}
