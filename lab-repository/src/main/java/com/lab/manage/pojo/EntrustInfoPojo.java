package com.lab.manage.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.lab.manage.domain.EntrustInfo;

/**
 * Created by Chengcheng on 2018/12/20.
 */
@TableName("entrust_info")
public class EntrustInfoPojo extends EntrustInfo {

    public EntrustInfoPojo() {
    }

    public EntrustInfoPojo(EntrustInfo entrustInfo) {
        this.setId(entrustInfo.getId());
        this.setMemberId(entrustInfo.getMemberId());
        this.setUnionMemberId(entrustInfo.getUnionMemberId());
        this.setName(entrustInfo.getName());
        this.setNum(entrustInfo.getNum());
        this.setDetectionType(entrustInfo.getDetectionType());
        this.setSource(entrustInfo.getSource());
        this.setDetectionPurpose(entrustInfo.getDetectionPurpose());
        this.setAptitudeType(entrustInfo.getAptitudeType());
        this.setServiceType(entrustInfo.getServiceType());
        this.setDayCount(entrustInfo.getDayCount());
        this.setStartTime(entrustInfo.getStartTime());
        this.setEntTime(entrustInfo.getEntTime());
        this.setDataTime(entrustInfo.getDataTime());
        this.setSignTime(entrustInfo.getSignTime());
        this.setToSampleTime(entrustInfo.getToSampleTime());
        this.setMoney(entrustInfo.getMoney());
        this.setResidualType(entrustInfo.getResidualType());
        this.setResidualTime(entrustInfo.getResidualTime());
        this.setMoenyTime(entrustInfo.getMoenyTime());
        this.setContent(entrustInfo.getContent());
        this.setStatus(entrustInfo.getStatus());
        this.setSampleType(entrustInfo.getSampleType());
        this.setToSubcontract(entrustInfo.getToSubcontract());
        this.setDetermine(entrustInfo.getDetermine());
        this.setStandardMethod(entrustInfo.getStandardMethod());
        this.setCreateBy(entrustInfo.getCreateBy());
        this.setCreateTime(entrustInfo.getCreateTime());
        this.setUpdateBy(entrustInfo.getUpdateBy());
        this.setUpdateTime(entrustInfo.getUpdateTime());
    }

}
