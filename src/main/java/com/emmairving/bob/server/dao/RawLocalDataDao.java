package com.emmairving.bob.server.dao;

import com.emmairving.bob.server.model.RawLocalData;
import com.emmairving.bob.server.model.RawLocalData_Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * Created by irving on 17/2/3.
 */
@Mapper
public interface RawLocalDataDao{

    @Insert(
        "INSERT INTO t_raw_local(id, user_id, meter_number, insert_time, voltage, current, active_power, " +
        "reactive_power, apparent_power, power_factor, electric_energy)" +
        "VALUES(#{id}, #{user_id}, #{meter_number}, now(), #{voltage}, #{current}, #{active_power}, " +
        "#{reactive_power}, #{apparent_power}, #{power_factor}, #{electric_energy})"
    )
    @Options(
        useGeneratedKeys = true,
        keyProperty = "id"
    )
    void insert(RawLocalData rawLocalData);

    List<RawLocalData> getList(RawLocalData_Select rawLocalData_select);

    int getCount(RawLocalData_Select rawLocalData_select);
}
