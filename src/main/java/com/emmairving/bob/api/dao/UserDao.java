package com.emmairving.bob.api.dao;

import com.emmairving.bob.api.model.User;
import com.emmairving.bob.api.model.UserDetail_Select;
import com.emmairving.bob.api.model.User_Select;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by irving on 17/2/7.
 */
@Mapper
public interface UserDao {

    @Insert(
        "INSERT INTO t_user(name, meter_id, password, joinDate) " +
        "VALUES(#{name}, #{meter_id}, #{password}, now() )"
    )
    @Options(
        useGeneratedKeys = true,
        keyProperty = "id"
    )
    void insert(User user);

    @Delete("DELETE FROM t_user WHERE id = #{id}")
    void deleteById(Integer id);

    @Select(
        "SELECT id, name, password, meter_number, joinDate " +
        "FROM t_user WHERE id = #{id}"
    )
    User getById(Integer id);

    List<User> getList(User_Select user_select);

    int getCount(User_Select user_select);
}
