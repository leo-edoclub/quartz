package com.css.cloud.quartz.zhjc.jsq.entity;

import java.util.Date;

public class TDeftAreaOrder {
    private String id;

    private String daoType;

    private String daoOrgCode;

    private String daoAreaCode;

    private String daoName;

    private Double daoScore;

    private Date daoBeginTime;

    private Date daoEndTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDaoType() {
        return daoType;
    }

    public void setDaoType(String daoType) {
        this.daoType = daoType == null ? null : daoType.trim();
    }

    public String getDaoOrgCode() {
        return daoOrgCode;
    }

    public void setDaoOrgCode(String daoOrgCode) {
        this.daoOrgCode = daoOrgCode == null ? null : daoOrgCode.trim();
    }

    public String getDaoAreaCode() {
        return daoAreaCode;
    }

    public void setDaoAreaCode(String daoAreaCode) {
        this.daoAreaCode = daoAreaCode == null ? null : daoAreaCode.trim();
    }

    public String getDaoName() {
        return daoName;
    }

    public void setDaoName(String daoName) {
        this.daoName = daoName == null ? null : daoName.trim();
    }

    public Double getDaoScore() {
        return daoScore;
    }

    public void setDaoScore(Double daoScore) {
        this.daoScore = daoScore;
    }

    public Date getDaoBeginTime() {
        return daoBeginTime;
    }

    public void setDaoBeginTime(Date daoBeginTime) {
        this.daoBeginTime = daoBeginTime;
    }

    public Date getDaoEndTime() {
        return daoEndTime;
    }

    public void setDaoEndTime(Date daoEndTime) {
        this.daoEndTime = daoEndTime;
    }
}