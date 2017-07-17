package com.junqiang.www.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by c0de8ug on 16-2-16.
 */

//TODO 由于放在WEB-INF所以在对主页跳转不能直接链接访问,等程序写多后要善于总结比较后改正，这样写太坑
@Controller
@RequestMapping("main.do")
public class MainController {

    private static Logger logger = LoggerFactory.getLogger(MainController.class);
    @RequestMapping("admin")
    public String adminMainView() {
        logger.trace("adminMainView");
        return "/admin/main";
    }

    @RequestMapping("student")
    public String studentMainView() {
        logger.trace("studentMainView");
        return "/student/main";
    }

    @RequestMapping("teacher")
    public String teacherMainView() {
        logger.trace("teacherMainView");
        return "/teacher/main";
    }
}
