package com.css.cloud.quartz.zhjc.jsq.entity;

import java.math.BigDecimal;
import java.util.Date;

public class THandleOfEveryday {
    private String id;

    private BigDecimal doeHandleNum;

    private BigDecimal doeOverNum;

    private Double doeAvgHandle;

    private Double doeAvgOver;

    private Date doeDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public BigDecimal getDoeHandleNum() {
        return doeHandleNum;
    }

    public void setDoeHandleNum(BigDecimal doeHandleNum) {
        this.doeHandleNum = doeHandleNum;
    }

    public BigDecimal getDoeOverNum() {
        return doeOverNum;
    }

    public void setDoeOverNum(BigDecimal doeOverNum) {
        this.doeOverNum = doeOverNum;
    }

    public Double getDoeAvgHandle() {
        return doeAvgHandle;
    }

    public void setDoeAvgHandle(Double doeAvgHandle) {
        this.doeAvgHandle = doeAvgHandle;
    }

    public Double getDoeAvgOver() {
        return doeAvgOver;
    }

    public void setDoeAvgOver(Double doeAvgOver) {
        this.doeAvgOver = doeAvgOver;
    }

    public Date getDoeDate() {
        return doeDate;
    }

    public void setDoeDate(Date doeDate) {
        this.doeDate = doeDate;
    }
}