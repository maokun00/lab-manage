package com.lab.manage.service;

import com.lab.manage.domain.TreeMenu;
import com.lab.manage.form.SysRoleForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Chengcheng on 2018/12/14.
 */
@FeignClient(value = "lab-service")
public interface SysRoleService {

    @RequestMapping("/service/sys/role/findList")
    Object findList(SysRoleForm sysRoleForm);

    @RequestMapping("/service/sys/role/findTree")
    List<TreeMenu> findTree(@RequestParam("roleId") Integer roleId);

    @RequestMapping("/service/sys/role/findTreeByCompanyId")
    List<TreeMenu> findTreeByCompanyId(@RequestParam("companyId") Integer companyId);

    @RequestMapping("/service/sys/role/findTreeByRoleIdAndCompanyId")
    List<TreeMenu> findTreeByRoleIdAndCompanyId(@RequestParam("roleId") Integer roleId, @RequestParam("companyId") Integer companyId);

}