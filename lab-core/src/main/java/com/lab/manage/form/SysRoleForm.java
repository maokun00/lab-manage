package com.lab.manage.form;

/**
 * Created by Chengcheng on 2018/12/14.
 */
public class SysRoleForm extends BaseForm {

    private String name;

    private Integer companyId;

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
