package com.emmairving.bob.server.dao;

import com.emmairving.bob.server.model.RawLocalData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by irving on 17/2/3.
 */
@Mapper
public interface RawLocalDataDao extends BaseDao<RawLocalData, Integer> {

    @Override
    @Insert(
        "INSERT INTO t_raw_local(id, user_id, meter_number, insert_time, voltage, current, active_power, " +
        "reactive_power, apparent_power, power_factor, electric_energy)" +
        "VALUES(#{id}, #{user_id}, #{meter_number}, now(), #{voltage}, #{current}, #{active_power}, " +
        "#{reactive_power}, #{apparent_power}, #{power_factor}, #{electric_energy})"
    )
    void insert(RawLocalData rawLocalData);
}
