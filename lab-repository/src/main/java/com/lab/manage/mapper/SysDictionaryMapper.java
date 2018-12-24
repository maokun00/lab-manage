package com.lab.manage.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lab.manage.domain.SysDictionary;
import com.lab.manage.form.SysDictionaryForm;
import com.lab.manage.pojo.SysDictionaryPojo;
import com.lab.manage.result.SysDictionaryResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Chengcheng on 2018/12/20.
 */
public interface SysDictionaryMapper extends BaseMapper<SysDictionaryPojo>{

    List<SysDictionaryResult> findList(SysDictionaryForm form);

    SysDictionaryResult findById(Integer id);

    List<SysDictionary> findByType(String type);

    List<SysDictionary> findTypes(@Param("types") String[] stringArray);

    SysDictionary findByTypeAndValue(@Param("type") String type, @Param("value") String value);
}
