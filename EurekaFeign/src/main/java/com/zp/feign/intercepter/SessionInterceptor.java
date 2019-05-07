package com.zp.feign.intercepter;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class SessionInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println(request.getRequestURI());
        response.addHeader("Token", UUID.randomUUID().toString());
        //登录不做拦截
        if(request.getRequestURI().equals("/user/login") || request.getRequestURI().equals("/user/login_view"))
        {
            return true;
        }
        String token = request.getHeader("Token");
//        if (StringUtils.isEmpty(token) || "undefined".equals(token)) {
//            response.addHeader("login_url","/login");
//            response.setStatus(401);
//            return false;
//        }
        //验证session是否存在
//        Object obj = request.getSession().getAttribute("_session_user");
//        if(obj == null)
//        {
//            response.sendRedirect("/user/login_view");
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
