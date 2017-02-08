package com.emmairving.bob.api.test;

import com.emmairving.bob.Bootstrap;
import com.emmairving.bob.api.dao.UserDao;
import com.emmairving.bob.api.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
        user.setName("emma");
        user.setPassword("111111");
        userDao.insert(user);
        System.out.println(user.toString());
    }
}
