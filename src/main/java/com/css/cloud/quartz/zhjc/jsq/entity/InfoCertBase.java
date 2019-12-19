package com.css.cloud.quartz.zhjc.jsq.entity;

import java.math.BigDecimal;
import java.util.Date;

public class InfoCertBase {
    private String id;

    private String certNo;

    private String certNameId;

    private String certName;

    private String issueOrg;

    private String ownerName;

    private String ownerCertNo;

    private Date issueDate;

    private Date startDate;

    private Date endDate;

    private String dir;

    private BigDecimal isAvaliable;

    private BigDecimal version;

    private String paperNo;

    private String source;

    private String trustLevel;

    private BigDecimal ownerType;

    private String digitalInfo;

    private Date createTime;

    private String creator;

    private Date editTime;

    private String editor;

    private BigDecimal paperStatus;

    private BigDecimal aboutToExpire;

    private String issueOrgCode;

    private String issueOrgAreaCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo == null ? null : certNo.trim();
    }

    public String getCertNameId() {
        return certNameId;
    }

    public void setCertNameId(String certNameId) {
        this.certNameId = certNameId == null ? null : certNameId.trim();
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName == null ? null : certName.trim();
    }

    public String getIssueOrg() {
        return issueOrg;
    }

    public void setIssueOrg(String issueOrg) {
        this.issueOrg = issueOrg == null ? null : issueOrg.trim();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    public String getOwnerCertNo() {
        return ownerCertNo;
    }

    public void setOwnerCertNo(String ownerCertNo) {
        this.ownerCertNo = ownerCertNo == null ? null : ownerCertNo.trim();
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir == null ? null : dir.trim();
    }

    public BigDecimal getIsAvaliable() {
        return isAvaliable;
    }

    public void setIsAvaliable(BigDecimal isAvaliable) {
        this.isAvaliable = isAvaliable;
    }

    public BigDecimal getVersion() {
        return version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

    public String getPaperNo() {
        return paperNo;
    }

    public void setPaperNo(String paperNo) {
        this.paperNo = paperNo == null ? null : paperNo.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getTrustLevel() {
        return trustLevel;
    }

    public void setTrustLevel(String trustLevel) {
        this.trustLevel = trustLevel == null ? null : trustLevel.trim();
    }

    public BigDecimal getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(BigDecimal ownerType) {
        this.ownerType = ownerType;
    }

    public String getDigitalInfo() {
        return digitalInfo;
    }

    public void setDigitalInfo(String digitalInfo) {
        this.digitalInfo = digitalInfo == null ? null : digitalInfo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor == null ? null : editor.trim();
    }

    public BigDecimal getPaperStatus() {
        return paperStatus;
    }

    public void setPaperStatus(BigDecimal paperStatus) {
        this.paperStatus = paperStatus;
    }

    public BigDecimal getAboutToExpire() {
        return aboutToExpire;
    }

    public void setAboutToExpire(BigDecimal aboutToExpire) {
        this.aboutToExpire = aboutToExpire;
    }

    public String getIssueOrgCode() {
        return issueOrgCode;
    }

    public void setIssueOrgCode(String issueOrgCode) {
        this.issueOrgCode = issueOrgCode == null ? null : issueOrgCode.trim();
    }

    public String getIssueOrgAreaCode() {
        return issueOrgAreaCode;
    }

    public void setIssueOrgAreaCode(String issueOrgAreaCode) {
        this.issueOrgAreaCode = issueOrgAreaCode == null ? null : issueOrgAreaCode.trim();
    }
}