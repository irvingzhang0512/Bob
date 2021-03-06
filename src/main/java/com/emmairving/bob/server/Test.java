package com.emmairving.bob.server;

import com.emmairving.bob.Bootstrap;
import com.emmairving.bob.api.dao.UserDetailDao;
import com.emmairving.bob.api.model.UserDetail;
import com.emmairving.bob.api.model.UserDetail_Select;
import com.emmairving.bob.server.dao.RawLocalDataDao;
import com.emmairving.bob.server.model.LocalData;
import com.emmairving.bob.server.model.LocalDataEnergy_Select;
import com.emmairving.bob.server.model.LocalData_Select;
import com.emmairving.bob.server.model.RawLocalData_Select;
import com.emmairving.bob.server.service.LocalDataService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by irving on 17/2/8.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Bootstrap.class)
public class Test {
    @Autowired
    private LocalDataService localDataService;
    @Autowired
    private RawLocalDataDao rawLocalDataDao;
    @Autowired
    private UserDetailDao userDetailDao;

    @org.junit.Test
    public void test() {
//        LocalDataEnergy_Select localDataEnergy_select = new LocalDataEnergy_Select();
//        localDataEnergy_select.setUser_id(1);
//        localDataEnergy_select.setYear(2017);
//        System.out.println(localDataService.getEnergy(localDataEnergy_select));

//        LocalData_Select localData_select = new LocalData_Select();
//        localData_select.setUser_id(1);
//        System.out.println(localDataService.getCount(localData_select));

//
        // 33764
        localDataService.rawLocalDataHandler(1, "000001094917");
//
//        LocalData_Select localData_select = new LocalData_Select();
//        localData_select.setUser_id(1);
//        localData_select.setYear((short)2017);
//        localData_select.setMonth((short)2);
//        localData_select.setDay((short)28);
//        List<LocalData> list = localDataService.getList(localData_select);
//
//        System.out.println(list.size());
//        for( int i = 0 ; i < list.size() ; i++ ) {
//            System.out.println(list.get(i));
//        }

        // 46983

        // 32040
//        localDataService.rawLocalDataHandler(46982, 1, "000001094917");



////        RawLocalData_Select rawLocalData_select = new RawLocalData_Select();
//          RawLocalData_Select rawLocalData_select = new RawLocalData_Select();
////        // 获取一共需要处理的RawLocalData数量
////        rawLocalData_select.setMeter_number("000001094917");
//          rawLocalData_select.setMeter_number("000001094917");
////        rawLocalData_select.setPageStart(0);
//          rawLocalData_select.setPageStart(10000);
////        rawLocalData_select.setPageSize(1000000);
//          rawLocalData_select.setPageSize(1000000);
////        int cnt = rawLocalDataDao.getCount(rawLocalData_select);
//          int cnt = rawLocalDataDao.getCount(rawLocalData_select);
//        System.out.println("CNT = " + cnt);
    }
}
