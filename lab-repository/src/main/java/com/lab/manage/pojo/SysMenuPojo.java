package com.lab.manage.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.lab.manage.domain.SysMenu;

/**
 * Created by Chengcheng on 2018/12/12.
 */
@TableName("sys_menu")
public class SysMenuPojo extends SysMenu {

    public SysMenuPojo() {
    }

    public SysMenuPojo(SysMenu sysMenu) {
        setName(sysMenu.getName());
        setId(sysMenu.getId());
        setOrderNum(sysMenu.getOrderNum());
        setParentId(sysMenu.getParentId());
        setPerms(sysMenu.getPerms());
        setCreateBy(sysMenu.getCreateBy());
        setCreateTime(sysMenu.getCreateTime());
        setUpdateBy(sysMenu.getUpdateBy());
        setUpdateTime(sysMenu.getUpdateTime());
        setType(sysMenu.getType());
        setUrl(sysMenu.getUrl());
    }

}
