package com.emmairving.bob.api.model;

/**
 * Created by irving on 17/2/17.
 */
public class UserDetail_Select {
    private Integer id;
    private String last_login_ip;
    private String last_login_time;
    private Integer raw_local_data_start_page;

    private Integer pageStart;
    private Integer pageSize;
    private Integer pageNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLast_login_ip() {
        return last_login_ip;
    }

    public void setLast_login_ip(String last_login_ip) {
        this.last_login_ip = last_login_ip;
    }

    public String getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }

    public Integer getRaw_local_data_start_page() {
        return raw_local_data_start_page;
    }

    public void setRaw_local_data_start_page(Integer raw_local_data_start_page) {
        this.raw_local_data_start_page = raw_local_data_start_page;
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
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
}
