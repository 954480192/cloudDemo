package com.example.user.config.oauth.controller;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**授权页面地址
 * http://localhost:8866/oauth/authorize?client_id=test1&redirect_uri=http://www.baidu.com&response_type=code&scope=all&state=1001
 *
 * 获取token地址
 * http://localhost:8866/oauth/token?grant_type=authorization_code&client_id=test1&client_secret=test1111&redirect_uri=http://www.baidu.com&code=SpoRxf&scope=all
 */

@Controller
@SessionAttributes("authorizationRequest")
public class BootGrantController {

    //@RequestMapping("/oauth/confirm_access")  
    @RequestMapping("/custom/confirm_access")
    public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {

        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");


        ModelAndView view = new ModelAndView();
        view.setViewName("base-grant");

        view.addObject("clientId", authorizationRequest.getClientId());

        view.addObject("scopes", authorizationRequest.getScope());

        return view;
    }
}