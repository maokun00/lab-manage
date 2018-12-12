package com.lab.manage.domain;

/**
 * Created by Chengcheng on 2018/12/12.
 */
public class CompanyDept extends BaseDomain {

    private Integer id;

    private Integer companyId;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
