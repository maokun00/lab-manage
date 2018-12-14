package com.lab.manage.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.lab.manage.domain.Company;

/**
 * Created by Chengcheng on 2018/12/14.
 */
@TableName("company")
public class CompanyPojo extends Company {

    public CompanyPojo() {
    }

    public CompanyPojo(Company company) {
        setId(company.getId());
        setName(company.getName());
        setPrivateKey(company.getPrivateKey());
        setPublicKey(company.getPublicKey());
        setCreateBy(company.getCreateBy());
        setUpdateBy(company.getUpdateBy());
        setCreateTime(company.getCreateTime());
        setUpdateTime(company.getUpdateTime());
        setPerson(company.getPerson());
        setPersonMobile(company.getPersonMobile());
        setEndTime(company.getEndTime());
    }
}
