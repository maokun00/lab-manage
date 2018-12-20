package com.lab.manage.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lab.manage.domain.SysDictionary;
import com.lab.manage.form.SysDictionaryForm;
import com.lab.manage.pojo.SysDictionaryPojo;
import com.lab.manage.result.SysDictionaryResult;

import java.util.List;

/**
 * Created by Chengcheng on 2018/12/20.
 */
public interface SysDictionaryMapper extends BaseMapper<SysDictionaryPojo>{

    List<SysDictionaryResult> findList(SysDictionaryForm form);

    SysDictionaryResult findById(Integer id);

    List<SysDictionary> findByType(String type);
}
