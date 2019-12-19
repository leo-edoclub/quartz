package com.css.cloud.quartz.zhjc.jsq.entity;

import java.util.Date;

public class TDeftOverDelay {
    private String id;

    private String dodOrgCode;

    private String dodName;

    private String dodType;

    private Double dodPercent;

    private Date dodDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDodOrgCode() {
        return dodOrgCode;
    }

    public void setDodOrgCode(String dodOrgCode) {
        this.dodOrgCode = dodOrgCode == null ? null : dodOrgCode.trim();
    }

    public String getDodName() {
        return dodName;
    }

    public void setDodName(String dodName) {
        this.dodName = dodName == null ? null : dodName.trim();
    }

    public String getDodType() {
        return dodType;
    }

    public void setDodType(String dodType) {
        this.dodType = dodType == null ? null : dodType.trim();
    }

    public Double getDodPercent() {
        return dodPercent;
    }

    public void setDodPercent(Double dodPercent) {
        this.dodPercent = dodPercent;
    }

    public Date getDodDate() {
        return dodDate;
    }

    public void setDodDate(Date dodDate) {
        this.dodDate = dodDate;
    }
}