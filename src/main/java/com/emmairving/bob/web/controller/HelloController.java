package com.emmairving.bob.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by irving on 17/2/18.
 */
@Controller
public class HelloController {
    private static Logger logger = LogManager.getLogger(HelloController.class);

    @Value("${application.hello:Hello Angel}")
    private String hello;


    @RequestMapping("/welcome")
    public String welcome(Map<String,Object> map){
        logger.debug("HelloController.welcome().hello="+hello);
        map.put("hello", hello);
        return "welcome";
    }
}
