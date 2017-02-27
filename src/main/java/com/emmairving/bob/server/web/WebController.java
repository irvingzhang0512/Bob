package com.emmairving.bob.server.web;

import com.emmairving.bob.server.Server;
import com.emmairving.bob.server.common.RunningThreads;
import com.emmairving.bob.server.dao.RawLocalDataDao;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


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


    @ApiIgnore
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

    @ApiOperation(
        value = "向目标线程发送指令",
        notes = "通过电表号，向目标线本地能量管理系统发送指令",
        httpMethod = "POST"
    )
    @RequestMapping("orders")
    public Object orders(
            String meter_number,
            String order
    ) {
        if( RunningThreads.sendOrders(meter_number, order) ) return "success";
        return "error";
    }

    @ApiIgnore
    @RequestMapping("counts")
    public Integer threadCnts() {
        return RunningThreads.getThreadsCounts();
    }
}
