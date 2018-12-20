package com.lab.manage.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.lab.manage.domain.SysDictionary;

/**
 * Created by Chengcheng on 2018/12/20.
 */
@TableName("sys_dictionary")
public class SysDictionaryPojo extends SysDictionary {

    public SysDictionaryPojo() {
    }

    public SysDictionaryPojo(SysDictionary sysDictionary) {
        this.setId(sysDictionary.getId());
        this.setName(sysDictionary.getName());
        this.setOrder(sysDictionary.getOrder());
        this.setType(sysDictionary.getType());
        this.setValue(sysDictionary.getValue());
        this.setCreateBy(sysDictionary.getCreateBy());
        this.setCreateTime(sysDictionary.getCreateTime());
        this.setUpdateBy(sysDictionary.getUpdateBy());
        this.setUpdateTime(sysDictionary.getUpdateTime());
        this.setLabel(sysDictionary.getLabel());
    }
}
