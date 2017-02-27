package com.emmairving.bob.web.model;

import com.emmairving.bob.server.model.LocalData;
import com.emmairving.bob.server.model.RawLocalData;

import java.util.List;

/**
 * Created by irving on 17/2/27.
 */
public class ShowLocalDataListResult {
    private List<LocalData> data;
    private Integer recordsTotal;
    private Integer recordsFiltered;

    public List<LocalData> getData() {
        return data;
    }

    public void setData(List<LocalData> data) {
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
