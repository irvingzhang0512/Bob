package com.emmairving.bob.api.dao;

import com.emmairving.bob.api.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * Created by irving on 17/2/7.
 */
@Mapper
public interface UserDao extends BaseDao<User, Integer> {

    @Override
    @Insert(
        "INSERT INTO t_user(name, meter_id, password, joinDate) " +
        "VALUES(#{name}, #{meter_id}, #{password}, now() )"
    )
    @Options(
        useGeneratedKeys = true,
        keyProperty = "id"
    )
    void insert(User user);
}
