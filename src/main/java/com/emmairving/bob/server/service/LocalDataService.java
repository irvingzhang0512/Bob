package com.emmairving.bob.server.service;

import com.emmairving.bob.api.service.UserDetailService;
import com.emmairving.bob.server.dao.LocalDataDao;
import com.emmairving.bob.server.dao.RawLocalDataDao;
import com.emmairving.bob.server.model.LocalData;
import com.emmairving.bob.server.model.LocalDataEnergy_Select;
import com.emmairving.bob.server.model.RawLocalData;
import com.emmairving.bob.server.model.RawLocalData_Select;
import com.emmairving.bob.server.utils.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by irving on 17/2/16.
 */
@Service
public class LocalDataService {
    private static Logger logger = LogManager.getLogger(LocalDataService.class);

    @Autowired
    private LocalDataDao localDataDao;
    @Autowired
    private RawLocalDataDao rawLocalDataDao;
    @Autowired
    private UserDetailService userDetailService;

    private static int INFINITY_PAGE_SIZE = 100000;

    private static int NUMBER_PER_TIME = 1000;

    /**
     *
     * 根据条件，获取能量消耗
     * 条件可以是
     * user_id
     * meter_number
     * year
     * month
     * day
     * hour
     * minute
     *
     * @param localDataEnergy_select
     * @return
     */
    public Double getEnergy(LocalDataEnergy_Select localDataEnergy_select) {
        return localDataDao.getEnergy(localDataEnergy_select);
    }

    /**
     *
     *
     * 处理RawLocalData数据
     *
     * @param user_id 数据拥有者
     * @param meter_number 拥有者智能电表号
     */
    private void rawLocalDataHandler(int user_id, String meter_number) {
        //需要处理的数据的起始编号，即上次处理数据的最后一条编号
        int start = userDetailService.getUserPageStart(user_id);

        RawLocalData_Select rawLocalData_select = new RawLocalData_Select();
        // 获取一共需要处理的RawLocalData数量
        rawLocalData_select.setMeter_number(meter_number);
        rawLocalData_select.setPageStart(start);
        rawLocalData_select.setPageSize(INFINITY_PAGE_SIZE);
        int cnt = rawLocalDataDao.getCount(rawLocalData_select);

        // 设置t_userdetail中的raw_local_data_page_start字段
        // 即下次进行处理的rawlocaldata下标
        userDetailService.setUserPageStart(user_id, start+cnt-1);

        logger.debug("待处理的rawLocalData数量: "+ cnt);

        // 开始进行操作
        boolean isFirstTime = true;
        int cur_cnt = 0;
        List<RawLocalData> rawLocalDataList = null;
        Calendar pre_time = null;
        Calendar cur_time = null;
        Double pre_electricEnergy = null;
        RawLocalData rawLocalData = null;


        // 循环查询数据，并进行处理
        while (cur_cnt < cnt) {
            // 查询数据
            rawLocalData_select.setPageStart(start+cur_cnt);
            rawLocalData_select.setPageSize(Math.min(NUMBER_PER_TIME, cnt - cur_cnt));
            rawLocalDataList = rawLocalDataDao.getList(rawLocalData_select);

            logger.debug("cur_cnt = " + cur_cnt);
            logger.debug("rawLocalDataList.size() = " + rawLocalDataList.size());

            int i = 0;

            // 初始化 pre_electricEnergy 与 pre_time
            while( isFirstTime ) {
                // electricEnergy初始化
                if( rawLocalDataList.get(0).getElectric_energy() == null
                        || rawLocalDataList.get(0).getElectric_energy() == .0 ) {
                    i++;
                    continue;
                }
                pre_electricEnergy = rawLocalDataList.get(i).getElectric_energy();

                // pre_time初始化
                try {
                    pre_time = DateUtils.getCalendar_By_String_MySql_DateTime(rawLocalDataList.get(i).getInsert_time());
                } catch (Exception e) {
                    logger.error("Error Transfroming From String To Calendar: "
                            + rawLocalDataList.get(i).getInsert_time());
                    i++;
                    continue;
                }

                // i 与 isFirstTime 初始化
                i++;
                isFirstTime = false;

                logger.debug("pre_electricEnergy = "+pre_electricEnergy);
                logger.debug("pre_time"
                        + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(pre_time.getTime()));
            }


            //
            for( ; i < rawLocalDataList.size() ; i++ ) {
                rawLocalData = rawLocalDataList.get(i);

                // 获取Calendar对象
                try {
                    cur_time = DateUtils.getCalendar_By_String_MySql_DateTime(rawLocalData.getInsert_time());
                } catch (Exception e) {
                    logger.error("Error Transfroming From String To Calendar: "
                            + rawLocalDataList.get(i).getInsert_time());
                    continue;
                }

                if( cur_time.equals(pre_time) ) continue;

                // 时间有差距
                pre_time.add(Calendar.MINUTE, 1);
                if( !pre_time.equals(cur_time) ) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    logger.debug("Fill In Blanks From "+ sdf.format(pre_time.getTime())
                            + " to " + sdf.format(cur_time.getTime()));
                    fillInBlanks(pre_time, cur_time, pre_electricEnergy, user_id, meter_number);
                }

                // 普通插入
                usualInsert(rawLocalData, pre_electricEnergy);

                // 设置pre_ElectricEnergy
                pre_electricEnergy = (rawLocalData.getElectric_energy() == null ||
                                    rawLocalData.getElectric_energy() == .0 ) ?
                                    pre_electricEnergy : rawLocalData.getElectric_energy();
                pre_time = cur_time;
            }

            cur_cnt += rawLocalDataList.size();
        }



    }


    /**
     *
     * 当RawLocalData的两条相邻数据之间有空隙时，填充数据
     * 填充时间包括startTime，不包括endTime
     *
     * @param startTime
     * @param endTime
     * @param electricEnergy
     * @param user_id
     * @param meter_number
     */
    private void fillInBlanks(Calendar startTime, Calendar endTime,
                              Double electricEnergy, int user_id, String meter_number) {
        LocalData localData = new LocalData();
        for( ; startTime.before(endTime) ; startTime.add(Calendar.MINUTE, 1) ) {
            localData.setValuesByCalendarAndOthers(startTime, electricEnergy, user_id, meter_number, (short)2);
            localDataDao.insert(localData);
        }
    }

    /**
     *
     * 普通添加
     * 通过rawLocalData创建localData对象，并设置status为1
     *
     * @param rawLocalData
     */
    private void usualInsert(RawLocalData rawLocalData, double pre_electricEnergy) {
        try {
            LocalData localData = new LocalData();
            localData.setValuesFromRawLocalData(rawLocalData);
            localData.setStatus((short) 1);
            if( rawLocalData.getElectric_energy() < 1e-5 ) {
                localData.setElectricEnergy(pre_electricEnergy);
            }
            localData.setEnergy( localData.getElectricEnergy() - pre_electricEnergy );
            localDataDao.insert(localData);
        } catch (Exception e) {
            logger.error("Error Insert LocalData: " + e.getMessage());
        }
    }
}
