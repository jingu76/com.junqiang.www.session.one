package com.junqiang.www.college.controller;

import com.junqiang.www.college.service.DeptBiz;
import com.junqiang.www.college.service.SpecBiz;
import com.junqiang.www.entity.Spec;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by c0de8ug on 16-2-11.
 */
@Controller
@RequestMapping("spec.do")
public class SpecController {
    Logger logger = LoggerFactory.getLogger(SpecController.class);

    @Resource(name = "specBizImpl")
    SpecBiz specBiz;

    @RequiresRoles("admin")
    @RequestMapping("spec_add.view")
    public String specAddView(Model m) {
        logger.trace("specAddView");
        m.addAttribute("deptNameList", specBiz.findDpet());
        return "/admin/college/spec_add";
    }

    //TODO 该名字deptAndSpec不好,但是不知道如何命名-, -
    @RequiresRoles("admin")
    @RequestMapping("spec.view")
    public String specView(Model m) {
        logger.trace("specView");
        m.addAttribute("deptAndSpec", specBiz.findDeptAndSpec());
        return "/admin/college/spec";
    }
    @RequiresRoles("admin")
    @RequestMapping("spec_update.view")
    public String specUpdateView(Model m) {
        logger.trace("specUpdateView");
        return "/admin/college/spec_update";
    }
    @RequiresRoles("admin")
    @RequestMapping("update")
    public String update(@Param("specName") String newSpecName, @Param("newSpecName") String specName) {
        logger.trace("update");
        specBiz.update(specName, newSpecName);
        return "redirect:/spec.do/spec.view";
    }
    @RequiresRoles("admin")
    @RequestMapping("add")
    public String add(Spec spec) {
        logger.trace("add");
        specBiz.add(spec);
        return "redirect:/spec.do/spec.view";
    }
    @RequiresRoles("admin")
    @RequestMapping("delete")
    public String delete(String specName) {
        logger.trace("delete");
        specBiz.delete(specName);
        return "redirect:/spec.do/spec.view";
    }

}

