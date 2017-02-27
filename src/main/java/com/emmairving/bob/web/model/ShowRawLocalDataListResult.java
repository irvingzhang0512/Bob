package com.emmairving.bob.web.model;

import com.emmairving.bob.server.model.RawLocalData;

import java.util.List;

/**
 * Created by irving on 17/2/27.
 */
public class ShowRawLocalDataListResult {
    private List<RawLocalData> data;
    private Integer recordsTotal;
    private Integer recordsFiltered;

    public List<RawLocalData> getData() {
        return data;
    }

    public void setData(List<RawLocalData> data) {
        this.data = data;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }
}
