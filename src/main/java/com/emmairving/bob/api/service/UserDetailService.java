package com.emmairving.bob.api.service;

import com.emmairving.bob.api.dao.UserDetailDao;
import com.emmairving.bob.api.model.UserDetail;
import com.emmairving.bob.api.model.UserDetail_Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by irving on 17/2/27.
 */
@Service
public class UserDetailService {
    @Autowired
    private UserDetailDao userDetailDao;

    public void update(UserDetail userDetail) {
        userDetailDao.update(userDetail);
    }

    public void setUserPageStart(int user_id, int pageStart) {
        UserDetail userDetail = new UserDetail();
        userDetail.setId(user_id);
        userDetail.setRaw_local_data_start_page(pageStart);
        update(userDetail);
    }

    public int getUserPageStart(int user_id) {
        UserDetail_Select userDetail_select = new UserDetail_Select();
        userDetail_select.setId(user_id);
        List<UserDetail> list = userDetailDao.getList(userDetail_select);
        return list.get(0).getRaw_local_data_start_page();
    }
}
