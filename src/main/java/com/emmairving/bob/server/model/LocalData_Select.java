package com.emmairving.bob.server.model;

/**
 * Created by irving on 17/2/28.
 */
public class LocalData_Select {
    private Integer id;
    private String meter_number;
    private Integer user_id;
    private Short year;
    private Short month;
    private Short day;
    private Short hour;
    private Short minute;
    private Double current;
    private Double voltage;
    private Double electricEnergy;
    private Double energy;
    private Double activePower;
    private Double reactivePower;
    private Double apparentPower;
    private Double powerFactor;
    private Short status;

    private Integer pageStart;
    private Integer pageSize;
    private Integer pageNumber;

    private String sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeter_number() {
        return meter_number;
    }

    public void setMeter_number(String meter_number) {
        this.meter_number = meter_number;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public Short getMonth() {
        return month;
    }

    public void setMonth(Short month) {
        this.month = month;
    }

    public Short getDay() {
        return day;
    }

    public void setDay(Short day) {
        this.day = day;
    }

    public Short getHour() {
        return hour;
    }

    public void setHour(Short hour) {
        this.hour = hour;
    }

    public Short getMinute() {
        return minute;
    }

    public void setMinute(Short minute) {
        this.minute = minute;
    }

    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    public Double getElectricEnergy() {
        return electricEnergy;
    }

    public void setElectricEnergy(Double electricEnergy) {
        this.electricEnergy = electricEnergy;
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public Double getActivePower() {
        return activePower;
    }

    public void setActivePower(Double activePower) {
        this.activePower = activePower;
    }

    public Double getReactivePower() {
        return reactivePower;
    }

    public void setReactivePower(Double reactivePower) {
        this.reactivePower = reactivePower;
    }

    public Double getApparentPower() {
        return apparentPower;
    }

    public void setApparentPower(Double apparentPower) {
        this.apparentPower = apparentPower;
    }

    public Double getPowerFactor() {
        return powerFactor;
    }

    public void setPowerFactor(Double powerFactor) {
        this.powerFactor = powerFactor;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
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
            pageStart = (pageNumber-1)*pageSize;
        }
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
        if( pageSize != null ) {
            pageStart = (pageNumber-1)*pageSize;
        }
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }


}
