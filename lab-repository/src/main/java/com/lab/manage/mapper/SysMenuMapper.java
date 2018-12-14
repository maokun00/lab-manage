package com.lab.manage.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lab.manage.domain.SysMenu;
import com.lab.manage.domain.TreeMenu;
import com.lab.manage.form.SysMenuForm;
import com.lab.manage.pojo.SysMenuPojo;
import com.lab.manage.result.SysMenuResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Chengcheng on 2018/12/12.
 */
public interface SysMenuMapper extends BaseMapper<SysMenuPojo> {

    List<String> queryAllPerms(@Param("list") List<Integer> roleId);

    List<TreeMenu> findTree();

    List<SysMenuResult> findList(SysMenuForm sysMenuForm);

    SysMenuResult findById(Integer memuId);

    List<SysMenuResult> findByParentId(Integer menuId);

    List<TreeMenu> findTreeByCompanyId(Integer companyId);
}
