package com.junqiang.www.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.Normalizer;

public class FormLoginFilter extends PathMatchingFilter {
    Logger logger = LoggerFactory.getLogger(FormLoginFilter.class);
    private String loginUrl = "/login";
    private String successUrl = "/";

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        logger.trace("onPreHandle");
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return true;//已经登录过
        }
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (isLoginRequest(req)) {
            if ("post".equalsIgnoreCase(req.getMethod())) {//form表单提交
                boolean loginSuccess = login(req); //登录
                if (loginSuccess) {
                    return true;
                }
            }
        }
        return true;
    }

    private boolean redirectToSuccessUrl(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.trace("redirectToSuccessUrl");
        WebUtils.redirectToSavedRequest(req, resp, successUrl);
        return false;
    }

    private void saveRequestAndRedirectToLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.trace("saveRequestAndRedirectToLogin");
        WebUtils.saveRequest(req);
        WebUtils.issueRedirect(req, resp, loginUrl);
    }

    private boolean login(HttpServletRequest req) {
        logger.trace("login");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password));
        } catch (Exception e) {
//            req.setAttribute("shiroLoginFailure", e.getClass());
            req.setAttribute("shiroLoginFailure", e.getClass().getName());
            return false;
        }
        return true;
    }

    private boolean isLoginRequest(HttpServletRequest req) {
        boolean ret = pathsMatch(loginUrl, WebUtils.getPathWithinApplication(req));
        logger.trace("isLoginRequest:"+ret);
        return ret;
    }
}
