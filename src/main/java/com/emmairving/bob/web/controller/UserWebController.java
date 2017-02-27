package com.emmairving.bob.web.controller;

import com.emmairving.bob.api.service.UserService;
import com.emmairving.bob.server.common.RunningThreads;
import com.emmairving.bob.web.model.UpdateUserResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by irving on 17/2/18.
 */
@Controller
@RequestMapping("user")
public class UserWebController {
    private static Logger logger = LogManager.getLogger(UserWebController.class);

    private UserService userService;

//    public UpdateUserResult(Integer id, String )

    @RequestMapping("isAliveLocalSystem")
    public String isAliveLocalSystem(HttpServletRequest req) {
        if( RunningThreads.isAliveLocalSystem((String)req.getSession().getAttribute("meter_number")) )
            req.getSession().setAttribute("isAlive", "运行中");
        else req.getSession().setAttribute("isAlive", "未运行");
        return "redirect:/me";
    }

    @RequestMapping("battery")
    public String useBattery(HttpServletRequest req) {
        logger.debug("user/battery");
        RunningThreads.sendOrders((String)req.getSession().getAttribute("meter_number"), "CTRL RELAY1 CONNECT");
        return "redirect:/me";
    }

    @RequestMapping("supply")
    public String useSupply(HttpServletRequest req) {
        logger.debug("user/supply");
        RunningThreads.sendOrders((String)req.getSession().getAttribute("meter_number"), "CTRL RELAY1 DISCONNECT");
        return "redirect:/me";
    }
}
