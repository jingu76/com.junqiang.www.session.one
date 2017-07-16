package com.junqiang.www.college.controller;

import com.junqiang.www.college.service.SectionBiz;
import com.junqiang.www.entity.Section;
import com.junqiang.www.entity.Timetable;
import com.junqiang.www.util.TermContainer;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.Timeable;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by c0de8ug on 16-2-12.
 */
@Controller
@RequestMapping("section.do")
public class SectionController {
    Logger logger = LoggerFactory.getLogger(SectionController.class);

    @Resource(name = "sectionBizImpl")
    private SectionBiz sectionBiz;

    @RequiresRoles("admin")
    @RequestMapping("section.view")
    public String sectionView(Model m) {
        logger.trace("sectionView:");
        m.addAttribute("sectionList", sectionBiz.findAllCustom());
        return "/admin/college/section";
    }

    @RequiresRoles("admin")
    @RequestMapping("section_add.view")
    public String sectionAddView(Model m) {
        logger.trace("sectionAddView:");
        m.addAttribute("courseTitleList", sectionBiz.findAllCourseTitle());
        m.addAttribute("staffList", sectionBiz.findAllStaff());
        m.addAttribute("termList", TermContainer.getTermList());
        return "/admin/college/section_add";
    }

    @RequiresRoles("admin")
    @RequestMapping("section_timetable_add.view")
    public String sectionTimetableAdd(Model m) {
        logger.trace("sectionTimetableView:");
        return "/admin/college/section_timetable_add";
    }

    @RequiresRoles("admin")
    @RequestMapping("add")
    public String add(Section section, HttpSession session) {
        logger.trace("add");
        sectionBiz.add(section);
        return "redirect:section.view";
    }

    @RequiresRoles("admin")
    @RequestMapping("addTimetable")
    public String addTimetable(Timetable timetable) {
        logger.trace("addTimetable");
        sectionBiz.addTimetable(timetable);
        return "redirect:section.view";
    }

    @RequiresRoles("admin")
    @RequestMapping("delete")
    public String delete(int secId) {
        logger.trace("delete");
        sectionBiz.delete(secId);
        return "redirect:section.view";
    }
}
