package com.lab.manage.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lab.manage.domain.SysUser;
import com.lab.manage.form.SysUserForm;
import com.lab.manage.pojo.SysUserPojo;
import com.lab.manage.result.SysUserResult;

import java.util.List;

/**
 * Created by Chengcheng on 2018/12/12.
 */
public interface SysUserMapper extends BaseMapper<SysUserPojo>{

    SysUser findByUsername(String username);

    SysUserResult findById(Integer userId);

    List<SysUserResult> findList(SysUserForm sysUserForm);

    List<SysUser> findNoCompanyUser();

}
