package com.css.cloud.quartz.zhjc.jsq.entity;

import java.io.Serializable;
import java.util.Date;

public class TDeftScoreStatistics implements Serializable{
    private String id;

    private String dssProjectType;

    private Double dssProjectScore;

    private String dssAreaDept;

    private Date dssBeginTime;

    private Date dssEndTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDssProjectType() {
        return dssProjectType;
    }

    public void setDssProjectType(String dssProjectType) {
        this.dssProjectType = dssProjectType == null ? null : dssProjectType.trim();
    }

    public Double getDssProjectScore() {
        return dssProjectScore;
    }

    public void setDssProjectScore(Double dssProjectScore) {
        this.dssProjectScore = dssProjectScore;
    }

    public String getDssAreaDept() {
        return dssAreaDept;
    }

    public void setDssAreaDept(String dssAreaDept) {
        this.dssAreaDept = dssAreaDept == null ? null : dssAreaDept.trim();
    }

    public Date getDssBeginTime() {
        return dssBeginTime;
    }

    public void setDssBeginTime(Date dssBeginTime) {
        this.dssBeginTime = dssBeginTime;
    }

    public Date getDssEndTime() {
        return dssEndTime;
    }

    public void setDssEndTime(Date dssEndTime) {
        this.dssEndTime = dssEndTime;
    }
}