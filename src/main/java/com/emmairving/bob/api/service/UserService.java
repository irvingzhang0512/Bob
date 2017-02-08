package com.emmairving.bob.api.service;

import com.emmairving.bob.api.dao.UserDao;
import com.emmairving.bob.api.dao.UserDetailDao;
import com.emmairving.bob.api.model.User;
import com.emmairving.bob.api.model.UserDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by irving on 17/2/7.
 */
@Service
public class UserService {
    private static Logger logger = LogManager.getLogger(UserService.class.getName());

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserDetailDao userDetailDao;


    /**
     *
     * 新建用户
     * 在t_user与t_user_detail中新建用户
     *
     */
    public void insert(User user) {
        userDao.insert(user);
        UserDetail userDetail = new UserDetail();
        userDetail.setId(user.getId());
        userDetailDao.insert(userDetail);
    }
}
