package com.lab.manage.result;

import com.lab.manage.domain.*;
import com.lab.manage.util.DateUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Chengcheng on 2018/12/20.
 * 委托信息
 */
public class EntrustResult extends EntrustInfo{

    private String detectionTypeStr;
    private String serviceTypeStr;
    private String sampleTypeStr;
    private Integer sysCompanyId;

    /** ===========报告参数============ */
    private Long reportId;
    private String reportName;
    private String reportMobile;
    private Integer languageType;
    private Integer reportType;
    private String reportContent;
    private Integer reportCount;
    private Integer modeType;
    private String reportingUnit;
    private String reportLocation;
    /** ===========报告参数============ */




    /** ===========受检单位参数============ */
    private String unionCompanyName;
    private String unionMemberName;
    private String unionMemberMobile;
    private Integer unionCityId;
    private String unionMemberLocation;
    /** ===========受检单位参数============ */



    /** ===========委托单位参数============ */
    private String companyName;
    private String memberName;
    private String memberMobile;
    private String email;
    private String fax;
    private String memberSource;
    private Integer cityId;
    private String memberLocation;
    /** ===========委托单位参数============ */

    public String getStartTimeStr(){
        return DateUtil.formatDateTime(this.getStartTime());
    }

    public String getDataTimeStr(){
        return DateUtil.formatDateTime(this.getDataTime());
    }

    public String getToSampleTimeStr(){
        return DateUtil.formatDateTime(this.getToSampleTime());
    }

    public String getMoneyStr(){
        return getMoney() == null?"":getMoney().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    public String getResidualTimeStr(){
        return DateUtil.formatDateTime(this.getResidualTime());
    }

    public String getMoenyTimeStr(){
        return DateUtil.formatDateTime(this.getMoenyTime());
    }

    public String getSignTimeStr(){
        return DateUtil.formatDateTime(this.getSignTime());
    }

    public String getEntTimeStr(){
        return DateUtil.formatDateTime(this.getEntTime());
    }

    public String getCreateTimeStr(){
        return DateUtil.formatDateTime(this.getCreateTime());
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDetectionTypeStr() {
        return detectionTypeStr;
    }

    public void setDetectionTypeStr(String detectionTypeStr) {
        this.detectionTypeStr = detectionTypeStr;
    }

    public String getServiceTypeStr() {
        return serviceTypeStr;
    }

    public void setServiceTypeStr(String serviceTypeStr) {
        this.serviceTypeStr = serviceTypeStr;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportMobile() {
        return reportMobile;
    }

    public void setReportMobile(String reportMobile) {
        this.reportMobile = reportMobile;
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

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public Integer getReportCount() {
        return reportCount;
    }

    public void setReportCount(Integer reportCount) {
        this.reportCount = reportCount;
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

    public String getReportLocation() {
        return reportLocation;
    }

    public void setReportLocation(String reportLocation) {
        this.reportLocation = reportLocation;
    }

    public String getUnionCompanyName() {
        return unionCompanyName;
    }

    public void setUnionCompanyName(String unionCompanyName) {
        this.unionCompanyName = unionCompanyName;
    }

    public String getUnionMemberName() {
        return unionMemberName;
    }

    public void setUnionMemberName(String unionMemberName) {
        this.unionMemberName = unionMemberName;
    }

    public String getUnionMemberMobile() {
        return unionMemberMobile;
    }

    public void setUnionMemberMobile(String unionMemberMobile) {
        this.unionMemberMobile = unionMemberMobile;
    }

    public Integer getUnionCityId() {
        return unionCityId;
    }

    public void setUnionCityId(Integer unionCityId) {
        this.unionCityId = unionCityId;
    }

    public String getUnionMemberLocation() {
        return unionMemberLocation;
    }

    public void setUnionMemberLocation(String unionMemberLocation) {
        this.unionMemberLocation = unionMemberLocation;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMemberSource() {
        return memberSource;
    }

    public void setMemberSource(String memberSource) {
        this.memberSource = memberSource;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getMemberLocation() {
        return memberLocation;
    }

    public void setMemberLocation(String memberLocation) {
        this.memberLocation = memberLocation;
    }

    public Integer getSysCompanyId() {
        return sysCompanyId;
    }

    public void setSysCompanyId(Integer sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getSampleTypeStr() {
        return sampleTypeStr;
    }

    public void setSampleTypeStr(String sampleTypeStr) {
        this.sampleTypeStr = sampleTypeStr;
    }

    public void setMember(Member member) {
        this.companyName = member.getCompanyName();
        this.memberName = member.getName();
        this.memberMobile = member.getMobile();
        this.email = member.getEmail();
        this.fax = member.getFax();
        this.memberSource = member.getSource();
        this.cityId = member.getCityId();
        this.memberLocation = member.getLocation();
    }

    public void setUnionMember(Member unionMember) {
        this.unionCompanyName = unionMember.getCompanyName();
        this.unionMemberName = unionMember.getName();
        this.unionMemberMobile = unionMember.getMobile();
        this.unionCityId = unionMember.getCityId();
        this.unionMemberLocation = unionMember.getLocation();
    }

    public void setReport(EntrustReport report) {
        this.reportId = report.getId();
        this.reportName = report.getName();
        this.reportMobile = report.getMobile();
        this.languageType = report.getLanguageType();
        this.reportType = report.getReportType();
        this.reportContent = report.getContent();
        this.reportCount = report.getCount();
        this.modeType = report.getModeType();
        this.reportingUnit = report.getReportingUnit();
        this.reportLocation = report.getLocation();
    }
}
