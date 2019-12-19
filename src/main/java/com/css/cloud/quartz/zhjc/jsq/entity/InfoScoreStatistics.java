package com.css.cloud.quartz.zhjc.jsq.entity;

import java.math.BigDecimal;
import java.util.Date;

public class InfoScoreStatistics {
    private String id;

    private String areaCode;

    private String orgCode;

    private String projectType;

    private BigDecimal noClaim;

    private BigDecimal guideProcess;

    private Double guideFull;

    private Double guideExact;

    private BigDecimal guideConcord;

    private BigDecimal personRegister;

    private BigDecimal onlineReport;

    private BigDecimal formJoin;

    private Double receiveJoin;

    private BigDecimal onlineRate;

    private BigDecimal lineUpDownNum;

    private BigDecimal lineUpMonthNum;

    private BigDecimal postNumber;

    private BigDecimal ssoInterface;

    private BigDecimal fullCertList;

    private BigDecimal certCleanUp;

    private BigDecimal sumbitData;

    private BigDecimal certJoin;

    private BigDecimal pointClaim;

    private BigDecimal pointSolve;

    private BigDecimal newPoint;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType == null ? null : projectType.trim();
    }

    public BigDecimal getNoClaim() {
        return noClaim;
    }

    public void setNoClaim(BigDecimal noClaim) {
        this.noClaim = noClaim;
    }

    public BigDecimal getGuideProcess() {
        return guideProcess;
    }

    public void setGuideProcess(BigDecimal guideProcess) {
        this.guideProcess = guideProcess;
    }

    public Double getGuideFull() {
        return guideFull;
    }

    public void setGuideFull(Double guideFull) {
        this.guideFull = guideFull;
    }

    public Double getGuideExact() {
        return guideExact;
    }

    public void setGuideExact(Double guideExact) {
        this.guideExact = guideExact;
    }

    public BigDecimal getGuideConcord() {
        return guideConcord;
    }

    public void setGuideConcord(BigDecimal guideConcord) {
        this.guideConcord = guideConcord;
    }

    public BigDecimal getPersonRegister() {
        return personRegister;
    }

    public void setPersonRegister(BigDecimal personRegister) {
        this.personRegister = personRegister;
    }

    public BigDecimal getOnlineReport() {
        return onlineReport;
    }

    public void setOnlineReport(BigDecimal onlineReport) {
        this.onlineReport = onlineReport;
    }

    public BigDecimal getFormJoin() {
        return formJoin;
    }

    public void setFormJoin(BigDecimal formJoin) {
        this.formJoin = formJoin;
    }

    public Double getReceiveJoin() {
        return receiveJoin;
    }

    public void setReceiveJoin(Double receiveJoin) {
        this.receiveJoin = receiveJoin;
    }

    public BigDecimal getOnlineRate() {
        return onlineRate;
    }

    public void setOnlineRate(BigDecimal onlineRate) {
        this.onlineRate = onlineRate;
    }

    public BigDecimal getLineUpDownNum() {
        return lineUpDownNum;
    }

    public void setLineUpDownNum(BigDecimal lineUpDownNum) {
        this.lineUpDownNum = lineUpDownNum;
    }

    public BigDecimal getLineUpMonthNum() {
        return lineUpMonthNum;
    }

    public void setLineUpMonthNum(BigDecimal lineUpMonthNum) {
        this.lineUpMonthNum = lineUpMonthNum;
    }

    public BigDecimal getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(BigDecimal postNumber) {
        this.postNumber = postNumber;
    }

    public BigDecimal getSsoInterface() {
        return ssoInterface;
    }

    public void setSsoInterface(BigDecimal ssoInterface) {
        this.ssoInterface = ssoInterface;
    }

    public BigDecimal getFullCertList() {
        return fullCertList;
    }

    public void setFullCertList(BigDecimal fullCertList) {
        this.fullCertList = fullCertList;
    }

    public BigDecimal getCertCleanUp() {
        return certCleanUp;
    }

    public void setCertCleanUp(BigDecimal certCleanUp) {
        this.certCleanUp = certCleanUp;
    }

    public BigDecimal getSumbitData() {
        return sumbitData;
    }

    public void setSumbitData(BigDecimal sumbitData) {
        this.sumbitData = sumbitData;
    }

    public BigDecimal getCertJoin() {
        return certJoin;
    }

    public void setCertJoin(BigDecimal certJoin) {
        this.certJoin = certJoin;
    }

    public BigDecimal getPointClaim() {
        return pointClaim;
    }

    public void setPointClaim(BigDecimal pointClaim) {
        this.pointClaim = pointClaim;
    }

    public BigDecimal getPointSolve() {
        return pointSolve;
    }

    public void setPointSolve(BigDecimal pointSolve) {
        this.pointSolve = pointSolve;
    }

    public BigDecimal getNewPoint() {
        return newPoint;
    }

    public void setNewPoint(BigDecimal newPoint) {
        this.newPoint = newPoint;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}