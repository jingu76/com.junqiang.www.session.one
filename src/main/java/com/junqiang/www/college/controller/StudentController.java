package com.junqiang.www.college.controller;

import com.junqiang.www.college.service.StudentBiz;
import com.junqiang.www.entity.Student;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.annotation.MultipartConfig;
import java.io.IOException;

/**
 * Created by c0de8ug on 16-2-12.
 */
@Controller
@RequestMapping("student.do")
public class StudentController {
    Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Resource(name = "studentBizImpl")
    StudentBiz studentBiz;

    @RequiresRoles("admin")
    @RequestMapping("student.view")
    public String studentView(Model m) {
        logger.trace("studentView");
        //TODO 将biz命名为和业务有关的函数方法,不知道是否正确留个吭
        m.addAttribute("studentList", studentBiz.studentView());
        return "/admin/college/student";
    }

    @RequiresRoles("admin")
    @RequestMapping("student_add.view")
    public String studentAddView(Model m) {
        logger.trace("studentAddView");
        return "/admin/college/student_add";
    }

    @RequiresRoles("admin")
    @RequestMapping("student_update.view")
    public String studentUpdateView(Model m) {
        logger.trace("studentUpdateView");
        return "/admin/college/student_update";
    }

    @RequiresRoles("admin")
    @RequestMapping("add")
    public String add(Model m, MultipartFile pic, Student student) throws IOException {
        logger.trace("add");
        studentBiz.add(student, pic);
        return "redirect:/student.do/student.view";
    }

    @RequiresRoles("admin")
    @RequestMapping("delete")
    public String delete(Model m, int studentId) {
        logger.trace("delete");
        studentBiz.delete(studentId);
        return "redirect:/student.do/student.view";
    }

}
