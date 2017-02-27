package com.emmairving.bob.server.common;

import com.emmairving.bob.server.Worker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by irving on 17/2/8.
 */
public class RunningThreads {
    private static Logger logger = LogManager.getLogger(RunningThreads.class.getName());

    private static ConcurrentHashMap<String, Worker> runningThreads = new ConcurrentHashMap<String, Worker>();

    private static int runningThreadsCounts = 0;

    /**
     *
     * 判断指定电表号是否存在运行的线程
     *
     * @param meter_number
     * @return
     */
    public static boolean isAlive(String meter_number) {
        if( runningThreads.containsKey(meter_number) ) return true;
        return false;
    }

    /**
     *
     * 添加线程
     * 若当前电表号已经存在运行中的线程，则添加失败。
     *
     * @param meter_number
     * @param worker
     * @return
     */
    public static void addThread(String meter_number, Worker worker) {
        if( runningThreads.containsKey(meter_number) ) {
            runningThreads.get(meter_number).shutdown();
        }
        runningThreads.put(meter_number, worker);
    }

    public static Worker getThread(String meter_number) {
        return runningThreads.get(meter_number);
    }


    /**
     *
     * 关闭线程
     * 关闭成功则返回true
     * 关闭失败则返回false
     *
     * @param meter_number
     * @return
     */
    public static boolean closeThread(String meter_number) {
        Worker worker = runningThreads.get(meter_number);
        if( worker != null ) {
            worker.shutdown();
            return true;
        }
        return false;
    }

    /**
     *
     * 向目标线程发送指令
     * 发送成功则返回true
     * 发送失败则返回false
     *
     * @param meter_number
     * @param order
     * @return
     */
    public static boolean sendOrders(String meter_number, String order) {
        Worker worker = runningThreads.get(meter_number);
        if( worker != null ) {
            try {
                worker.writeOutput(order);
                return true;
            } catch (IOException e) {
                logger.error("Error writing to "+meter_number+"!");
            }
        }
        return false;
    }

    /**
     *
     * 获取正在运行的线程数量
     *
     * @return
     */
    public static int getThreadsCounts() {
        return runningThreads.size();
    }

    /**
     *
     * 查看给定电表号本地系统是否在运行中
     *
     */
    public static boolean isAliveLocalSystem(String meter_number) {
        return runningThreads.containsKey(meter_number);
    }
}
