package com.emmairving.bob.server;

import com.emmairving.bob.Bootstrap;
import com.emmairving.bob.server.dao.RawLocalDataDao;
import com.emmairving.bob.server.model.RawLocalData;
import com.emmairving.bob.server.model.RawLocalData_Select;
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
    private RawLocalDataDao rawLocalDataDao;


    @org.junit.Test
    public void test() {
        RawLocalData_Select rawLocalData_select = new RawLocalData_Select();

        rawLocalData_select.setElectric_energy(52.6);

        List<RawLocalData> list = rawLocalDataDao.getList(rawLocalData_select);
        System.out.println(list.size());
    }
}
