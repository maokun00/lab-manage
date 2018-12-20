package com.lab.manage.domain;

/**
 * Created by Chengcheng on 2018/12/20.
 * 检测项目
 */
public class EntrustDetection extends BaseDomain {

    private Long id;

    private String name;

    private Long entrustInfoId;

    private Integer basisId;

    private String basisName;

    private Integer locale;

    private String content;

    private Integer basisType;

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

    public Long getEntrustInfoId() {
        return entrustInfoId;
    }

    public void setEntrustInfoId(Long entrustInfoId) {
        this.entrustInfoId = entrustInfoId;
    }

    public Integer getBasisId() {
        return basisId;
    }

    public void setBasisId(Integer basisId) {
        this.basisId = basisId;
    }

    public String getBasisName() {
        return basisName;
    }

    public void setBasisName(String basisName) {
        this.basisName = basisName;
    }

    public Integer getLocale() {
        return locale;
    }

    public void setLocale(Integer locale) {
        this.locale = locale;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBasisType() {
        return basisType;
    }

    public void setBasisType(Integer basisType) {
        this.basisType = basisType;
    }
}
