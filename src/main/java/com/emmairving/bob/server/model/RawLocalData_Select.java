package com.emmairving.bob.server.model;

/**
 * Created by irving on 17/2/8.
 */
public class RawLocalData_Select {
    private Integer id;
    private String user_id;
    private String meter_number;
    private Double voltage;
    private Double current;
    private Double active_power;
    private Double reactive_power;
    private Double apparent_power;
    private Double power_factor;
    private Double electric_energy;
    private String insert_time;

    // 最早插入时间
    private String min_insert_time;
    // 最晚插入时间
    private String max_insert_time;

    // 结果排序方式
    private String sort;

    // 分页信息
    //数据库查询时的起始记号
    private Integer pageStart;
    //每页记录数量
    private Integer pageSize;
    //逻辑页码，从1开始编号
    private Integer pageNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    public Double getActive_power() {
        return active_power;
    }

    public void setActive_power(Double active_power) {
        this.active_power = active_power;
    }

    public Double getReactive_power() {
        return reactive_power;
    }

    public void setReactive_power(Double reactive_power) {
        this.reactive_power = reactive_power;
    }

    public Double getApparent_power() {
        return apparent_power;
    }

    public void setApparent_power(Double apparent_power) {
        this.apparent_power = apparent_power;
    }

    public Double getPower_factor() {
        return power_factor;
    }

    public void setPower_factor(Double power_factor) {
        this.power_factor = power_factor;
    }

    public Double getElectric_energy() {
        return electric_energy;
    }

    public void setElectric_energy(Double electric_energy) {
        this.electric_energy = electric_energy;
    }

    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }

    public String getMin_insert_time() {
        return min_insert_time;
    }

    public void setMin_insert_time(String min_insert_time) {
        this.min_insert_time = min_insert_time;
    }

    public String getMax_insert_time() {
        return max_insert_time;
    }

    public void setMax_insert_time(String max_insert_time) {
        this.max_insert_time = max_insert_time;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        if( pageNumber != null ) {
            pageStart = (pageNumber - 1)*pageSize;
        }
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
        if( pageSize != null ) {
            pageStart = (pageNumber - 1)*pageSize;
        }
    }

    public String getMeter_number() {
        return meter_number;
    }

    public void setMeter_number(String meter_number) {
        this.meter_number = meter_number;
    }
}
