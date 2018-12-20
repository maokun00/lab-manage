package com.lab.manage.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Chengcheng on 2018/12/20.
 * 委托基本信息
 */
public class EntrustInfo extends BaseDomain{

    private Long id;

    private Long memberId;

    private Long unionMemberId;

    /**
     * 委托名称
     */
    private String name;

    /**
     * 委托编号
     */
    private String num;

    /**
     * 检测类型
     */
    private Integer detectionType;

    /**
     * 委托来源
     */
    private String source;

    /**
     * 检验目的
     */
    private String detectionPurpose;

    /**
     * 资质管理类型
     */
    private Integer aptitudeType;

    /**
     * 服务类型
     */
    private Integer serviceType;

    /**
     * 执行期限（天）
     */
    private Integer dayCount;

    /**
     * 预计开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 预计出报告时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date entTime;

    /**
     * 数据出具日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataTime;

    /**
     * 签订日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date signTime;

    /**
     * 到样日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date toSampleTime;

    /**
     * 委托金额
     */
    private BigDecimal money;

    /**
     * 余样处置类型
     */
    private Integer residualType;

    /**
     * 留样时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date residualTime;

    /**
     * 最晚付款时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date moenyTime;

    /**
     * 备注
     */
    private String content;

    /**
     * 委托状态
     */
    private Integer status;

    /**
     * 采样类型
     */
    private Integer sampleType;

    /**
     * 是否分包
     */
    private Integer toSubcontract;

    /**
     * 是否判定
     */
    private Integer determine;

    /**
     * 使用非标方法
     */
    private Integer standardMethod;

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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Integer getDetectionType() {
        return detectionType;
    }

    public void setDetectionType(Integer detectionType) {
        this.detectionType = detectionType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDetectionPurpose() {
        return detectionPurpose;
    }

    public void setDetectionPurpose(String detectionPurpose) {
        this.detectionPurpose = detectionPurpose;
    }

    public Integer getAptitudeType() {
        return aptitudeType;
    }

    public void setAptitudeType(Integer aptitudeType) {
        this.aptitudeType = aptitudeType;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getDayCount() {
        return dayCount;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEntTime() {
        return entTime;
    }

    public void setEntTime(Date entTime) {
        this.entTime = entTime;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Date getToSampleTime() {
        return toSampleTime;
    }

    public void setToSampleTime(Date toSampleTime) {
        this.toSampleTime = toSampleTime;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getResidualType() {
        return residualType;
    }

    public void setResidualType(Integer residualType) {
        this.residualType = residualType;
    }

    public Date getResidualTime() {
        return residualTime;
    }

    public void setResidualTime(Date residualTime) {
        this.residualTime = residualTime;
    }

    public Date getMoenyTime() {
        return moenyTime;
    }

    public void setMoenyTime(Date moenyTime) {
        this.moenyTime = moenyTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getToSubcontract() {
        return toSubcontract;
    }

    public void setToSubcontract(Integer toSubcontract) {
        this.toSubcontract = toSubcontract;
    }

    public Integer getDetermine() {
        return determine;
    }

    public void setDetermine(Integer determine) {
        this.determine = determine;
    }

    public Integer getStandardMethod() {
        return standardMethod;
    }

    public void setStandardMethod(Integer standardMethod) {
        this.standardMethod = standardMethod;
    }

    public Integer getSampleType() {
        return sampleType;
    }

    public void setSampleType(Integer sampleType) {
        this.sampleType = sampleType;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getUnionMemberId() {
        return unionMemberId;
    }

    public void setUnionMemberId(Long unionMemberId) {
        this.unionMemberId = unionMemberId;
    }

}
