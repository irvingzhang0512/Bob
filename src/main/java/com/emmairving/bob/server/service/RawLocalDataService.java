package com.emmairving.bob.server.service;


import com.emmairving.bob.server.common.ConstantValueForServer;
import com.emmairving.bob.server.dao.RawLocalDataDao;
import com.emmairving.bob.server.model.RawLocalData;
import com.emmairving.bob.server.model.RawLocalData_Select;
import com.emmairving.bob.server.utils.CommandsUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by irving on 17/2/4.
 */
@Service
public class RawLocalDataService {
    private static final Logger logger = LogManager.getLogger(RawLocalDataService.class.getName());
    @Autowired
    private RawLocalDataDao rawLocalDataDao;

    /**
     *
     * 是否可以设置rawLocalData的值
     *
     * @param commands
     * @param rawLocalData
     * @return
     */
    public static boolean canSetRawLocalDataByCommands(String commands, RawLocalData rawLocalData) {
        if( !CommandsUtils.isLegalRawLocalDataCommands(commands) ) return false;

        String[] words = commands.split(" ");
        Integer index = ConstantValueForServer.RAW_DATA_INDEX_MAP.get(words[2]);
        logger.debug("canSetRawLocalDataByCommands index = "+index);
        return rawLocalData.canSetObjectByIndex(index);
    }

    /**
     *
     * 设置rawLocalData值
     * 在调用此方法前，调用canSetRawLocalDataByCommands方法
     *
     * @param commands
     * @param rawLocalData
     */
    public static void setRawLocalDataByCommands(String commands, RawLocalData rawLocalData) {
        String[] words = commands.split(" ");
        String meter_number = words[1];
        Double d = Double.parseDouble(words[3]);
        Integer index = ConstantValueForServer.RAW_DATA_INDEX_MAP.get(words[2]);

        rawLocalData.setMeter_number(meter_number);
        rawLocalData.setObjectByIndex(d, index);
    }

    /**
     *
     * 从命令中获取电表号
     * 调用该方法前，必须调用CommandsUtils.isLegalRawLocalDataCommands(commands)方法并返回true
     *
     * @param commands
     */
    public static String getMeterNumberFromCommands(String commands) {
        String[] words = commands.split(" ");
        return words[1];
    }

    /**
     *
     * 插入RawLocalData
     *
     * @param rawLocalData
     */
    public void insertRawLocalData(RawLocalData rawLocalData) {
        try {
            rawLocalDataDao.insert(rawLocalData);
        } catch(Exception e) {
            logger.error("Insert RawLocalData ERROR!");
        }
    }

    /**
     *
     * 根据条件获取列表
     *
     * @param rawLocalData_select
     * @return
     */
    public List<RawLocalData> getList(RawLocalData_Select rawLocalData_select) {
        return rawLocalDataDao.getList(rawLocalData_select);
    }

    public Integer getCount(RawLocalData_Select rawLocalData_select) {
        return rawLocalDataDao.getCount(rawLocalData_select);
    }
}
