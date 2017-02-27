package com.emmairving.bob.web.model;

import java.util.List;

/**
 * Created by irving on 17/2/27.
 */
public class HightChartsResult {
    private List<Double> data;
    private String name;

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
