package com.lab.manage.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.lab.manage.domain.SysUser;

/**
 * Created by Chengcheng on 2018/12/12.
 */
@TableName("sys_user")
public class SysUserPojo extends SysUser {

    public SysUserPojo() {
    }

    public SysUserPojo(SysUser sysUser) {
        this.setCompanyId(sysUser.getCompanyId());
        this.setCreateBy(sysUser.getNickname());
        this.setCreateTime(sysUser.getCreateTime());
        this.setId(sysUser.getId());
        this.setMobile(sysUser.getMobile());
        this.setNickname(sysUser.getNickname());
        this.setSalt(sysUser.getSalt());
        this.setStatus(sysUser.getStatus());
        this.setUpdateBy(sysUser.getUpdateBy());
        this.setUpdateTime(sysUser.getUpdateTime());
        this.setUsername(sysUser.getUsername());
        this.setPassword(sysUser.getPassword());
    }
}
