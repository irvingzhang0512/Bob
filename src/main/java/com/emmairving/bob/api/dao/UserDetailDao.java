package com.emmairving.bob.api.dao;

import com.emmairving.bob.api.model.UserDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by irving on 17/2/7.
 */
@Mapper
public interface UserDetailDao extends BaseDao<UserDetail, Integer> {

    @Override
    @Insert(
        "INSERT INTO t_user_detail(id, last_login_ip, last_login_time) " +
        "VALUES(#{id}, #{last_login_ip}, #{last_login_time} )"
    )
    void insert(UserDetail userDetail);
}
