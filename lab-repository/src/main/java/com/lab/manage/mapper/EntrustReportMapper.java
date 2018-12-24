package com.lab.manage.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lab.manage.domain.EntrustReport;
import com.lab.manage.pojo.EntrustReportPojo;

/**
 * Created by Chengcheng on 2018/12/20.
 */
public interface EntrustReportMapper extends BaseMapper<EntrustReportPojo> {

    EntrustReport findByInfoId(Long id);
}
