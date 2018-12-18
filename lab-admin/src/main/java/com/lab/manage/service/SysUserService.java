package com.lab.manage.service;

import com.lab.manage.domain.SysRole;
import com.lab.manage.domain.SysUser;
import com.lab.manage.form.SysUserForm;
import com.lab.manage.result.SysRoleResult;
import com.lab.manage.result.SysUserResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Chengcheng on 2018/12/12.
 */
@FeignClient(value = "lab-service")
public interface SysUserService {

    @RequestMapping("/service/sys/user/findByUsername")
    SysUser findByUsername(@RequestParam("username") String username);

    @RequestMapping("/service/sys/user/register")
    boolean register(SysUser sysUser);

    @RequestMapping("/service/sys/user/findById")
    SysUserResult findById(@RequestParam("userId") Integer userId);

    @RequestMapping("/service/sys/user/findList")
    Object findList(SysUserForm sysUserForm);

    @RequestMapping("/service/sys/user/updateUser")
    void updateUser(SysUser sysUser);

    @RequestMapping("/service/sys/user/findNoCompanyUser")
    List<SysUser> findNoCompanyUser();

    @RequestMapping("/service/sys/user/roleSubmit")
    void roleSubmit(@RequestParam("userId") Integer userId, @RequestParam("roleIds") String[] roleIds);

    @RequestMapping("/service/sys/user/findRoleByUserId")
    List<Integer> findRoleByUserId(@RequestParam("userId") Integer userId);
}
