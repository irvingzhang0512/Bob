package com.emmairving.bob.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by irving on 17/2/5.
 */
@Component("com")
public class Server extends Thread implements ApplicationContextAware {
    private static final Logger logger = LogManager.getLogger(Server.class.getName());

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public void run() {
        ServerSocket server = null;
        Socket socket = null;
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
            logger.info("local host is: "+ addr.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            logger.error("fail to get local IP information!");
        }
        try {
            server = new ServerSocket(8086);
            while(true) {
                logger.info("Loop in Bootstrap, listening port 8086, waiting for requests");
                socket = server.accept();
                Worker worker = context.getBean("worker", Worker.class);
                worker.init(socket);
                worker.start();
            }
        } catch(Exception e) {
            e.printStackTrace();
            logger.error("fail to accept socket from requests!");
        }
    }
}
