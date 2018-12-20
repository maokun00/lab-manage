package com.lab.manage.domain;

/**
 * Created by Chengcheng on 2018/12/20.
 */
public class EntrustReport extends BaseDomain {

    private Long id;

    private String name;

    private String mobile;

    private Integer languageType;

    private Integer reportType;

    private String content;

    private Integer count;

    private Integer modeType;

    private String reportingUnit;

    private String location;

    private Long entrustInfoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getLanguageType() {
        return languageType;
    }

    public void setLanguageType(Integer languageType) {
        this.languageType = languageType;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getModeType() {
        return modeType;
    }

    public void setModeType(Integer modeType) {
        this.modeType = modeType;
    }

    public String getReportingUnit() {
        return reportingUnit;
    }

    public void setReportingUnit(String reportingUnit) {
        this.reportingUnit = reportingUnit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getEntrustInfoId() {
        return entrustInfoId;
    }

    public void setEntrustInfoId(Long entrustInfoId) {
        this.entrustInfoId = entrustInfoId;
    }
}
