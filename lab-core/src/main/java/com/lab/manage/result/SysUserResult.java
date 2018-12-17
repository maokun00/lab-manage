package com.lab.manage.result;

import com.lab.manage.domain.SysUser;

/**
 * Created by Chengcheng on 2018/12/17.
 */
public class SysUserResult extends SysUser {

    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStatusStr(){
        return getStatus() == null? "无状态":getStatus() == 1?"启用":"禁用";
    }
}
