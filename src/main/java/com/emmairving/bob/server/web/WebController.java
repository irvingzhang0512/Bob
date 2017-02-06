package com.emmairving.bob.server.web;

import com.emmairving.bob.server.Server;
import com.emmairving.bob.server.dao.RawLocalDataDao;
import com.emmairving.bob.server.model.RawLocalData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by irving on 17/2/5.
 */
@RestController
public class WebController implements ApplicationContextAware {
    private static final Logger logger = LogManager.getLogger(WebController.class.getName());
    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Autowired
    private RawLocalDataDao rawLocalDataDao;

    private boolean isStart = false;

    @RequestMapping("server")
    public Object startServer() {
        Server server = context.getBean("server", Server.class);
        if( !isStart ) {
            server.start();
            isStart = true;
            return "server start success!";
        }
        return "server is started already!";
    }

    @RequestMapping("test")
    public Object test() {
        return "test";
    }

    @RequestMapping("insert")
    public Object insert() {
        RawLocalData rawLocalData = new RawLocalData();
        try {
            rawLocalDataDao.insert(rawLocalData);
        } catch(Exception e) {
            e.printStackTrace();
            logger.error("INSERT ERROR!");
            return "error";
        }
        return "success";
    }
}
