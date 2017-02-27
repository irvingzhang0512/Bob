package com.emmairving.bob.server.model;

import com.emmairving.bob.server.utils.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.util.resources.ar.CalendarData_ar;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by irving on 17/2/16.
 */
public class LocalData {
    private static final Logger logger = LogManager.getLogger(LocalData.class);

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

    public boolean setValuesFromRawLocalData(RawLocalData rawLocalData) {
        Calendar calendar = null;
        try {
            calendar = DateUtils.getCalendar_By_String_MySql_DateTime(rawLocalData.getInsert_time());
        } catch (Exception e) {
            logger.error("error transfer from String to Calendar: "+rawLocalData.getInsert_time());
            return false;
        }

        this.meter_number = rawLocalData.getMeter_number();
//        this.user_id = rawLocalData.getUser_id();
        this.user_id = 1;
        this.year = (short)calendar.get(Calendar.YEAR);
        this.month = (short)(calendar.get(Calendar.MONTH)+1);
        this.day = (short)calendar.get(Calendar.DAY_OF_MONTH);
        this.hour = (short)calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = (short)calendar.get(Calendar.MINUTE);
        this.current = rawLocalData.getCurrent() == null ? .0 : rawLocalData.getCurrent();
        this.voltage = rawLocalData.getVoltage() == null ? .0 : rawLocalData.getVoltage();
        this.electricEnergy = rawLocalData.getElectric_energy() == null ? .0 : rawLocalData.getElectric_energy();
        this.activePower = rawLocalData.getActive_power() == null ? .0 : rawLocalData.getActive_power();
        this.reactivePower = rawLocalData.getReactive_power() == null ? .0 : rawLocalData.getReactive_power();
        this.apparentPower = rawLocalData.getApparent_power() == null ? .0 : rawLocalData.getApparent_power();
        this.powerFactor = rawLocalData.getPower_factor() == null ? .0 : rawLocalData.getPower_factor();

        return true;
    }

    public void setValuesByCalendarAndOthers(Calendar calendar, Double preElectricEnergy,
                     Integer user_id, String meter_number, Short status) {
//        this.user_id = user_id;
        this.user_id = 1;
        this.meter_number = meter_number;

        this.current = .0;
        this.voltage = .0;
        this.activePower = .0;
        this.reactivePower = .0;
        this.apparentPower = .0;
        this.powerFactor = .0;
        this.energy = .0;

        this.electricEnergy = preElectricEnergy;

        this.year = (short)calendar.get(Calendar.YEAR);
        this.month = (short)calendar.get(Calendar.MONTH);
        this.day = (short)calendar.get(Calendar.DAY_OF_MONTH);
        this.hour = (short)calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = (short)calendar.get(Calendar.MINUTE);

        this.status = status;
    }

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

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }
}
