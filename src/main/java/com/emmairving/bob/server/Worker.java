package com.emmairving.bob.server;

import com.emmairving.bob.server.common.RunningThreads;
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

    // 通过Socket初始化Worker线程
    private Socket socket = null;
    private InputStream input = null;
    private OutputStream output = null;

    // 当前线程是否无线循环
    private boolean shutdown = false;

    // 当前线程所对应的电表号
    private String meter_number = null;

    // 电表数据插入数据库次数
    private int insertCnt = 0;

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

    public void shutdown() {
        shutdown = true;
        logger.info("try to shutdown thread "+ Thread.currentThread().getName());
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
                break;
            }

            //错误命令！
            if(!CommandsUtils.isLegalRawLocalDataCommands(commands)) continue;

            // 获取电表号，并将当前Worker添加到RunningThreads中
            if( meter_number == null ) {
                meter_number = rawLocalDataService.getMeterNumberFromCommands(commands);
                RunningThreads.addThread(meter_number, this);
            }

            if( rawLocalDataService.canSetRawLocalDataByCommands(commands, rawLocalData) ) {
                rawLocalDataService.setRawLocalDataByCommands(commands, rawLocalData);
            } else {
                rawLocalDataService.insertRawLocalData(rawLocalData);
                rawLocalData = new RawLocalData();
                {
                    try {
                        writeOutput("RESPONSE GET RawLocalData " + insertCnt);
                    } catch (IOException e) {
                        logger.error("Error Writing To " + meter_number);
                    }
                }
                insertCnt++;
            }

        }
        logger.info("socket closed!");
    }

    /**
     * 读取Socket数据
     *
     * @return
     * @throws IOException
     */
    private String readInput() throws IOException{
        byte[] bytes = new byte[2048];
        int length = 0;
        length = input.read(bytes);
        String rev = new String(bytes, 0, length);
        logger.debug("Worker read bytes length = "+ rev);
        return rev;
    }

    /**
     *
     * 向Socket写数据
     *
     * @param str
     * @throws IOException
     */
    public void writeOutput(String str) throws IOException {
        output.write(str.getBytes());
        output.flush();
        logger.info("Sending order to "+ meter_number+" :"+ str);
    }
}
