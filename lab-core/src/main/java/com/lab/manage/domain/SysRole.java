package com.lab.manage.domain;

/**
 * Created by Chengcheng on 2018/12/13.
 */
public class SysRole extends BaseDomain {

    private Integer id;

    private String name;

    private Integer companyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
