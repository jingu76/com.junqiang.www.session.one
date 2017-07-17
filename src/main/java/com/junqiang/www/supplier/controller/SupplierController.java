package com.junqiang.www.supplier.controller;

import com.junqiang.www.supplier.service.SupplierBiz;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by c0de8ug on 16-2-16.
 */

@Controller

@RequestMapping("supplier.do")
public class SupplierController {

    private static Logger logger = LoggerFactory.getLogger(SupplierController.class);
    @Resource(name = "supplierBizImpl")
    SupplierBiz supplierBiz;

    @RequiresRoles(value = {"admin", "supplier"}, logical = Logical.OR)
    @RequestMapping("supplier.view")
    public String supplierView(Model m) {
        logger.trace("supplierView");
        m.addAttribute("reviewedBookList", supplierBiz.findAllReviewedBook());
        return "/supplier/supplier";
    }
}
