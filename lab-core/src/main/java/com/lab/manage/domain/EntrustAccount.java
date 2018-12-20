package com.lab.manage.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Chengcheng on 2018/12/20.
 * 委托台账
 */
public class EntrustAccount extends BaseDomain {

    private Long id;

    private BigDecimal moeny;

    private BigDecimal moneyBack;

    private Integer remitType;

    private Date remitTime;

    private String remitName;

    private String content;

    private Long entrustInfoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMoeny() {
        return moeny;
    }

    public void setMoeny(BigDecimal moeny) {
        this.moeny = moeny;
    }

    public BigDecimal getMoneyBack() {
        return moneyBack;
    }

    public void setMoneyBack(BigDecimal moneyBack) {
        this.moneyBack = moneyBack;
    }

    public Integer getRemitType() {
        return remitType;
    }

    public void setRemitType(Integer remitType) {
        this.remitType = remitType;
    }

    public Date getRemitTime() {
        return remitTime;
    }

    public void setRemitTime(Date remitTime) {
        this.remitTime = remitTime;
    }

    public String getRemitName() {
        return remitName;
    }

    public void setRemitName(String remitName) {
        this.remitName = remitName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getEntrustInfoId() {
        return entrustInfoId;
    }

    public void setEntrustInfoId(Long entrustInfoId) {
        this.entrustInfoId = entrustInfoId;
    }
}
