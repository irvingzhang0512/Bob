package com.emmairving.bob.api.test;

import com.emmairving.bob.Bootstrap;
import com.emmairving.bob.api.dao.UserDao;
import com.emmairving.bob.api.model.User;
import com.emmairving.bob.api.model.UserDetail_Select;
import com.emmairving.bob.api.model.User_Select;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by irving on 17/2/7.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Bootstrap.class)
public class MyTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void test() {
        User user = new User();
        List<User> list = null;

        User_Select user_select = new User_Select();
        user_select.setId(1);

        user = userDao.getById(1);
        System.out.println(user.toString());
        list = userDao.getList(user_select);
        System.out.println("list.size() = "+list.size());
    }
}
