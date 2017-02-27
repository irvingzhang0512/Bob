package com.emmairving.bob.web.controller;

import com.emmairving.bob.api.model.User;
import com.emmairving.bob.web.service.UserWebService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by irving on 17/2/17.
 */
@Controller
public class HomeWebController {
    private static Logger logger = LogManager.getLogger(HomeWebController.class);

    @Autowired
    private UserWebService userWebService;

    @RequestMapping("/Home/Index")
    public String webLogin(
            @RequestParam(value = "username")
            String username,

            @RequestParam(value = "password")
            String password,
            HttpServletRequest req
    ) {
        logger.debug("Use HomeWebController webLogin");
        logger.debug("username = "+username);
        logger.debug("password = "+password);


        User user = userWebService.getUserByUserName(username);

        // 当前用户不存在
        if( user == null ) {
            logger.debug("Get User Empty");
            return "login";
        } else {
            logger.debug("Get User: "+ user.toString());
        }

        // 密码不错误
        if( !user.getPassword().equals(password) ) {
            logger.debug("Password Wrong: "+user.getPassword()+" vs "+password);
            return "login";
        }

        //正常登陆
        HttpSession session = req.getSession();
        session.setAttribute("name", user.getName());
        session.setAttribute("id", user.getId());
        session.setAttribute("meter_number", user.getMeter_number());
        session.setAttribute("joinDate", user.getJoinDate().substring(0, 10));


        return "index";
    }


    @RequestMapping("/")
    public String index() {
        return "login";
    }
}
