package com.emmairving.bob.web.service;

import com.emmairving.bob.api.dao.UserDao;
import com.emmairving.bob.api.model.User;
import com.emmairving.bob.api.model.User_Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by irving on 17/2/18.
 */
@Service
public class UserWebService {
    @Autowired
    private UserDao userDao;

    /**
     *
     * 根据用户名获取用户数据
     * 如果当前用户名不存在，则返回null
     *
     * @param username
     * @return
     */
    public User getUserByUserName(String username) {
        User_Select user_select = new User_Select();
        user_select.setName(username);
        List<User> list = userDao.getList(user_select);
        if( list == null || list.size() == 0 ) return null;
        return list.get(0);
    }
}
