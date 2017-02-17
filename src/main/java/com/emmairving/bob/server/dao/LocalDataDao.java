package com.emmairving.bob.server.dao;


import com.emmairving.bob.server.model.LocalData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * Created by irving on 17/2/16.
 */
@Mapper
public interface LocalDataDao extends BaseDao<Integer, LocalData> {
    @Insert(
        "INSERT INTO t_local_data(meter_number, user_id, year, month, day, hour, minute, " +
                "current, voltage, electricEnergy, activePower, " +
                "reactivePower, apparentPower, powerFactor, status )" +
                "VALUES(#{meter_number}, #{user_id}, #{year}, #{month}, #{day}, #{hour}, #{minute}, " +
                "#{current}, #{voltage}, #{electricEnergy}, #{activePower}, " +
                "#{reactivePower}, #{apparentPower}, #{powerFactor}, #{status})"
    )
    @Options(
            useGeneratedKeys = true,
            keyProperty = "id"
    )
    public void insert(LocalData localData);
}
