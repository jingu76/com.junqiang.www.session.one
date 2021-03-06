package com.junqiang.www.college.controller;

import com.junqiang.www.college.service.CourseBiz;
import com.junqiang.www.entity.Course;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("course.do")
public class CourseController {
    Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Resource(name = "courseBizImpl")
    private CourseBiz courseBiz;

    @RequiresRoles("admin")
    @RequestMapping("course.view")
    private String courseView(Model m) {
        logger.trace("courseView:"+m.toString());
        m.addAttribute("courseList", courseBiz.findAll());
        return "/admin/college/course";
    }

    @RequiresRoles("admin")
    @RequestMapping("course_add.view")
    private String courseAddView(Model m) {
        logger.trace("courseAddView:"+m.toString());
        m.addAttribute("specNameList", courseBiz.findAllSpecName());
        return "/admin/college/course_add";
    }

    @RequiresRoles("admin")
    @RequestMapping("add")
    private String add(Course course) {
        logger.trace("add:"+course.getCourseTitle());
        courseBiz.add(course);
        return "redirect:/course.do/course.view";
    }

    @RequiresRoles("admin")
    @RequestMapping("delete")
    private String delete(String courseTitle) {
        logger.trace("delete:"+courseTitle);
        courseBiz.delete(courseTitle);
        return "redirect:/course.do/course.view";
    }
}

