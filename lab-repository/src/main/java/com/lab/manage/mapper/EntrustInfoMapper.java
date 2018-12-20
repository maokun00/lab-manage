package com.lab.manage.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lab.manage.domain.EntrustInfo;
import com.lab.manage.form.EntrustForm;
import com.lab.manage.pojo.EntrustInfoPojo;
import com.lab.manage.result.EntrustResult;

import java.util.List;

/**
 * Created by Chengcheng on 2018/12/20.
 */
public interface EntrustInfoMapper extends BaseMapper<EntrustInfoPojo>{

    List<EntrustResult> findList(EntrustForm entrustForm);
}
