package com.junqiang.www.college.controller;

import com.junqiang.www.college.service.OrderBookBiz;
import com.junqiang.www.entity.Section;
import com.junqiang.www.entity.custom.ChangedItems;
import com.junqiang.www.entity.custom.OrderBookReviewVo;
import com.junqiang.www.entity.custom.OrderBookVo;
import com.junqiang.www.util.TermContainer;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("report.do")

public class reportController {
    Logger logger = LoggerFactory.getLogger(OrderBookController.class);
//    @Resource(name = "orderBookBizImpl")
//    private OrderBookBiz orderBookBiz;

    @RequiresRoles(value = {"admin"})
    @RequestMapping("report_review.view")
    public String reportView(Model m, HttpSession httpSession) {
//        logger.trace("orderBookView:");
//        String staffId = (String) httpSession.getAttribute("username");
//
//        List<Section> sectionList = orderBookBiz.findSelectedSection(staffId, TermContainer.now());
//        int courseCount = sectionList.size();
//        m.addAttribute("selectedSectionList", sectionList);
//        m.addAttribute("courseCount", courseCount);
        return "/admin/college/report";
    }
//
//    @RequiresRoles(value = {"admin", "teacher"}, logical = Logical.OR)
//    @RequestMapping("orderbook_review.view")
//    public String orderBookReviewView(Model m, HttpSession session) {
//        logger.trace("orderBookReviewView:");
//        //TODO 放到SESSION方便处理
//        session.setAttribute("notReviewedBookList", orderBookBiz.findAllNotReviewedBook());
//        return "/teacher/orderbook_review";
//    }

}
