package com.css.cloud.quartz.zhjc.jsq.entity;

import java.math.BigDecimal;
import java.util.Date;

public class InfoArchives {
    private String id;

    private String archivesId;

    private String archivesNo;

    private String archivesName;

    private String archivesTrem;

    private Date filingDate;

    private String caseNo;

    private String caseName;

    private String caseinfoNo;

    private String archivesTitle;

    private String archivesScope;

    private BigDecimal archivesPages;

    private String archivesRemarks;

    private Date createtime;

    private String creator;

    private Date updatetime;

    private String updater;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getArchivesId() {
        return archivesId;
    }

    public void setArchivesId(String archivesId) {
        this.archivesId = archivesId == null ? null : archivesId.trim();
    }

    public String getArchivesNo() {
        return archivesNo;
    }

    public void setArchivesNo(String archivesNo) {
        this.archivesNo = archivesNo == null ? null : archivesNo.trim();
    }

    public String getArchivesName() {
        return archivesName;
    }

    public void setArchivesName(String archivesName) {
        this.archivesName = archivesName == null ? null : archivesName.trim();
    }

    public String getArchivesTrem() {
        return archivesTrem;
    }

    public void setArchivesTrem(String archivesTrem) {
        this.archivesTrem = archivesTrem == null ? null : archivesTrem.trim();
    }

    public Date getFilingDate() {
        return filingDate;
    }

    public void setFilingDate(Date filingDate) {
        this.filingDate = filingDate;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo == null ? null : caseNo.trim();
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName == null ? null : caseName.trim();
    }

    public String getCaseinfoNo() {
        return caseinfoNo;
    }

    public void setCaseinfoNo(String caseinfoNo) {
        this.caseinfoNo = caseinfoNo == null ? null : caseinfoNo.trim();
    }

    public String getArchivesTitle() {
        return archivesTitle;
    }

    public void setArchivesTitle(String archivesTitle) {
        this.archivesTitle = archivesTitle == null ? null : archivesTitle.trim();
    }

    public String getArchivesScope() {
        return archivesScope;
    }

    public void setArchivesScope(String archivesScope) {
        this.archivesScope = archivesScope == null ? null : archivesScope.trim();
    }

    public BigDecimal getArchivesPages() {
        return archivesPages;
    }

    public void setArchivesPages(BigDecimal archivesPages) {
        this.archivesPages = archivesPages;
    }

    public String getArchivesRemarks() {
        return archivesRemarks;
    }

    public void setArchivesRemarks(String archivesRemarks) {
        this.archivesRemarks = archivesRemarks == null ? null : archivesRemarks.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }
}