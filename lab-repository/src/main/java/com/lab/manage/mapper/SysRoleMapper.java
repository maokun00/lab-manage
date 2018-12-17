package com.lab.manage.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lab.manage.domain.SysRole;
import com.lab.manage.form.SysRoleForm;
import com.lab.manage.pojo.SysRolePojo;
import com.lab.manage.result.SysRoleResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Chengcheng on 2018/12/13.
 */
public interface SysRoleMapper extends BaseMapper<SysRolePojo> {

    List<Integer> findByUserId(Integer userId);

    List<Integer> findTreeById(Integer roleId);

    List<SysRoleResult> findList(SysRoleForm sysRoleForm);

    List<SysRole> findNoCompanyRoleList();

    void removeByRoleId(Integer role);

    void treeSubmit(@Param("roleId") Integer role, @Param("treeIds") String[] treeIds);

    SysRoleResult findById(Integer roleId);

    List<SysRole> findByCompanyId(Integer companyId);
}
