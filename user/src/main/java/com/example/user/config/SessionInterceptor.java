package com.example.user.config;

import com.example.user.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;

public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("----enter---");
        String id = (String) request.getParameter("id");
        if(id==null||id.length()==0){
            return false;
        }
        LocalDateTime pre = LocalDateTime.now();
        request.setAttribute("pre",pre);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LocalDateTime pre = (LocalDateTime) request.getAttribute("pre");
        Duration d = Duration.between(LocalDateTime.now(),pre);
        System.out.println(d.toMillis());
        request.getSession().setAttribute("cost",d.toMillis());
    }
}
