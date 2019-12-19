package com.css.cloud.quartz.zhjc.jsq.entity;

import java.util.Date;

public class TBaseStatistics {
    private String id;

    private String dataType;

    private Long updateCount;

    private Long totalCont;

    private Date updatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public Long getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(Long updateCount) {
        this.updateCount = updateCount;
    }

    public Long getTotalCont() {
        return totalCont;
    }

    public void setTotalCont(Long totalCont) {
        this.totalCont = totalCont;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}