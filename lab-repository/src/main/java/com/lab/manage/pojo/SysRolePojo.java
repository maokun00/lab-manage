package com.lab.manage.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.lab.manage.domain.SysRole;

/**
 * Created by Chengcheng on 2018/12/13.
 */
@TableName("sys_role")
public class SysRolePojo extends SysRole {

    public SysRolePojo() {
    }

    public SysRolePojo(SysRole sysRole) {
        this.setCompanyId(sysRole.getCompanyId());
        this.setCreateBy(sysRole.getCreateBy());
        this.setCreateTime(sysRole.getCreateTime());
        this.setId(sysRole.getId());
        this.setName(sysRole.getName());
        this.setUpdateBy(sysRole.getUpdateBy());
        this.setUpdateTime(sysRole.getUpdateTime());
    }
}
