package com.emmairving.bob.server;

import com.emmairving.bob.server.model.RawLocalData;
import com.emmairving.bob.server.service.RawLocalDataService;
import com.emmairving.bob.server.utils.CommandsUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by irving on 17/2/3.
 */
@Component("worker")
@Scope("prototype")
public class Worker extends Thread {
    private static final Logger logger = LogManager.getLogger(Worker.class.getName());

    private Socket socket = null;
    private InputStream input = null;
    private OutputStream output = null;

    private boolean shutdown = false;

    @Autowired
    private RawLocalDataService rawLocalDataService;

    public Worker(){}

    public void init(Socket socket) {
        this.socket = socket;
        try {
            input = socket.getInputStream();
            output = socket.getOutputStream();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void shutdown() {
        shutdown = true;
    }

    public void run() {
        RawLocalData rawLocalData = new RawLocalData();
        while( !shutdown ) {
            logger.debug("Loop In Worker!");

            String commands = null;
            try {
                commands = readInput();
            } catch (IOException e) {
                logger.error("Read Worker Input ERROR!");
                shutdown();
            }

            //错误命令！
            if(!CommandsUtils.isLegalRawLocalDataCommands(commands)) continue;

            if( rawLocalDataService.canSetRawLocalDataByCommands(commands, rawLocalData) ) {
                rawLocalDataService.setRawLocalDataByCommands(commands, rawLocalData);
            } else {
                rawLocalDataService.insertRawLocalData(rawLocalData);
                rawLocalData = new RawLocalData();
            }

        }
        logger.info("socket closed!");
    }

    private String readInput() throws IOException{
        byte[] bytes = new byte[2048];
        int length = 0;
        length = input.read(bytes);
        String rev = new String(bytes, 0, length);
        logger.debug("Worker read bytes length = "+ rev);
        return rev;
    }

    private void writeOutput(String str) {
        try {
            output.write(str.getBytes());
            output.flush();
        } catch(Exception e) {
            logger.error("Write Worker Output ERROR!");
        }
    }
}
