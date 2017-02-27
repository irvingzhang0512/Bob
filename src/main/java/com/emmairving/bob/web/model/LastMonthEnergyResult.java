package com.emmairving.bob.web.model;

import java.util.List;

/**
 * Created by irving on 17/2/28.
 */
public class LastMonthEnergyResult {
    private XAxis xAxis;
    private List<HightChartsResult> list;

    public XAxis getxAxis() {
        return xAxis;
    }

    public void setxAxis(XAxis xAxis) {
        this.xAxis = xAxis;
    }

    public List<HightChartsResult> getList() {
        return list;
    }

    public void setList(List<HightChartsResult> list) {
        this.list = list;
    }
}
