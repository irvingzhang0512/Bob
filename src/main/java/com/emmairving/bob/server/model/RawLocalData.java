package com.emmairving.bob.server.model;

/**
 * Created by irving on 17/2/3.
 */
public class RawLocalData {
    private Integer id;
    private Integer user_id;
    private String meter_number;
    private String insert_time;
    private Double voltage;
    private Double current;
    private Double active_power;
    private Double reactive_power;
    private Double apparent_power;
    private Double power_factor;
    private Double electric_energy;


    /**
     *
     * 通过编号设置值
     * 先通过canSetObjectByIndex来进行判断，再调用该函数
     *
     * @param object
     * @param index
     * @return
     */
    public void setObjectByIndex(Double object, int index) {
        switch (index) {
            case 0:
//                if( voltage != null ) return false;
                voltage = object;
                break;
            case 1:
//                if(current != null ) return false;
                current = object;
                break;
            case 2:
//                if(active_power != null ) return false;
                active_power = object;
                break;
            case 3:
//                if(reactive_power != null ) return false;
                reactive_power = object;
                break;
            case 4:
//                if(apparent_power != null ) return false;
                apparent_power = object;
                break;
            case 5:
//                if(power_factor != null ) return false;
                power_factor = object;
                break;
            case 6:
//                if(electric_energy != null ) return false;
                electric_energy = object;
                break;
//            default:
//                return false;
        }
//        return true;
    }

    /**
     *
     * 判断是否可以设置值
     *
     * @param index
     * @return
     */
    public boolean canSetObjectByIndex(int index) {
        switch (index) {
            case 0:
                if( voltage != null ) return false;
                break;
            case 1:
                if(current != null ) return false;
                break;
            case 2:
                if(active_power != null ) return false;
                break;
            case 3:
                if(reactive_power != null ) return false;
                break;
            case 4:
                if(apparent_power != null ) return false;
                break;
            case 5:
                if(power_factor != null ) return false;
                break;
            case 6:
                if(electric_energy != null ) return false;
                break;
            default:
                return false;
        }

        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getMeter_number() {
        return meter_number;
    }

    public void setMeter_number(String meter_number) {
        this.meter_number = meter_number;
    }

    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
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
}
