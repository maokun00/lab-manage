package com.lab.manage.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lab.manage.form.CompanyForm;
import com.lab.manage.pojo.CompanyPojo;
import com.lab.manage.result.CompanyResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Chengcheng on 2018/12/14.
 */
public interface CompanyMapper extends BaseMapper<CompanyPojo> {

    List<CompanyResult> findList(CompanyForm companyForm);

    void removeMenuByCompanyId(Integer companyId);

    void pushMenu(@Param("companyId") Integer companyId, @Param("treeIds") String[] treeIds);

    CompanyResult findById(Integer companyId);
}
