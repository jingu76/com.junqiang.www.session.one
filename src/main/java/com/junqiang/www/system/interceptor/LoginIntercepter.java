package com.junqiang.www.system.interceptor;


import com.junqiang.www.system.controller.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by c0de8ug on 16-2-14.
 */
public class LoginIntercepter implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.trace("preHandle");
        String url = httpServletRequest.getRequestURI();

        if (url.indexOf("login") > 0) {
            return true;
        }

        HttpSession session = httpServletRequest.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return true;
        }

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//
//
//        String redirectStr = "redirect:";
//        int strLength = 9;
//
//
//        String path = modelAndView.getViewName();
//        if (path.indexOf(redirectStr) == 0) {
//            String temp = path.substring(strLength);
//
//            modelAndView.setViewName(redirectStr + contextPath + temp);
//        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.trace("afterCompletion");
    }
}
