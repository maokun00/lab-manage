package com.lab.manage.result;

import com.lab.manage.domain.SysMenu;

/**
 * Created by Chengcheng on 2018/12/13.
 */
public class SysMenuResult extends SysMenu{

    private String parentName;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
