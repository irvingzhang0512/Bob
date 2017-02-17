package com.emmairving.bob.api.dao;

import com.emmairving.bob.api.model.UserDetail;
import com.emmairving.bob.api.model.UserDetail_Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by irving on 17/2/7.
 */
@Mapper
public interface UserDetailDao {

    @Insert(
        "INSERT INTO t_user_detail(id, last_login_ip, last_login_time, startPage) " +
        "VALUES(#{id}, #{last_login_ip}, #{last_login_time}, #{startPage} )"
    )
    void insert(UserDetail userDetail);

    List<UserDetail> getList(UserDetail_Select userDetail_select);

    int getCount(UserDetail_Select userDetail_select);

    void update(UserDetail userDetail);
}
