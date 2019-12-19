package com.css.cloud.quartz.zhjc.jsq.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TCaseInfoStatistics {
    private String id;

    private BigDecimal caseinfoTotal;

    private BigDecimal caseinfoAccept;

    private BigDecimal caseinfoFinal;

    private BigDecimal caseinfoNetsave;

    private BigDecimal caseinfoNetaccept;

    private BigDecimal caseinfoNetfinal;

    private BigDecimal caseinfoFrontsave;

    private BigDecimal caseinfoFront;

    private BigDecimal caseinfoFrontfinal;

    private BigDecimal caseinfoPost;

    private Date updatetime;

    private BigDecimal caseinfoOnline;

    private BigDecimal caseinfoOnce;

    private BigDecimal caseinfoAll;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public BigDecimal getCaseinfoTotal() {
        return caseinfoTotal;
    }

    public void setCaseinfoTotal(BigDecimal caseinfoTotal) {
        this.caseinfoTotal = caseinfoTotal;
    }

    public BigDecimal getCaseinfoAccept() {
        return caseinfoAccept;
    }

    public void setCaseinfoAccept(BigDecimal caseinfoAccept) {
        this.caseinfoAccept = caseinfoAccept;
    }

    public BigDecimal getCaseinfoFinal() {
        return caseinfoFinal;
    }

    public void setCaseinfoFinal(BigDecimal caseinfoFinal) {
        this.caseinfoFinal = caseinfoFinal;
    }

    public BigDecimal getCaseinfoNetsave() {
        return caseinfoNetsave;
    }

    public void setCaseinfoNetsave(BigDecimal caseinfoNetsave) {
        this.caseinfoNetsave = caseinfoNetsave;
    }

    public BigDecimal getCaseinfoNetaccept() {
        return caseinfoNetaccept;
    }

    public void setCaseinfoNetaccept(BigDecimal caseinfoNetaccept) {
        this.caseinfoNetaccept = caseinfoNetaccept;
    }

    public BigDecimal getCaseinfoNetfinal() {
        return caseinfoNetfinal;
    }

    public void setCaseinfoNetfinal(BigDecimal caseinfoNetfinal) {
        this.caseinfoNetfinal = caseinfoNetfinal;
    }

    public BigDecimal getCaseinfoFrontsave() {
        return caseinfoFrontsave;
    }

    public void setCaseinfoFrontsave(BigDecimal caseinfoFrontsave) {
        this.caseinfoFrontsave = caseinfoFrontsave;
    }

    public BigDecimal getCaseinfoFront() {
        return caseinfoFront;
    }

    public void setCaseinfoFront(BigDecimal caseinfoFront) {
        this.caseinfoFront = caseinfoFront;
    }

    public BigDecimal getCaseinfoFrontfinal() {
        return caseinfoFrontfinal;
    }

    public void setCaseinfoFrontfinal(BigDecimal caseinfoFrontfinal) {
        this.caseinfoFrontfinal = caseinfoFrontfinal;
    }

    public BigDecimal getCaseinfoPost() {
        return caseinfoPost;
    }

    public void setCaseinfoPost(BigDecimal caseinfoPost) {
        this.caseinfoPost = caseinfoPost;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public BigDecimal getCaseinfoOnline() {
        return caseinfoOnline;
    }

    public void setCaseinfoOnline(BigDecimal caseinfoOnline) {
        this.caseinfoOnline = caseinfoOnline;
    }

    public BigDecimal getCaseinfoOnce() {
        return caseinfoOnce;
    }

    public void setCaseinfoOnce(BigDecimal caseinfoOnce) {
        this.caseinfoOnce = caseinfoOnce;
    }

    public BigDecimal getCaseinfoAll() {
        return caseinfoAll;
    }

    public void setCaseinfoAll(BigDecimal caseinfoAll) {
        this.caseinfoAll = caseinfoAll;
    }
}