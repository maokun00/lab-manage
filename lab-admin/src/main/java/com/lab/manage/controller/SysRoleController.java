package com.lab.manage.controller;

import com.lab.manage.config.MyLog;
import com.lab.manage.domain.*;
import com.lab.manage.form.SysRoleForm;
import com.lab.manage.result.CompanyResult;
import com.lab.manage.result.SysRoleResult;
import com.lab.manage.service.CompanyService;
import com.lab.manage.service.SysMenuService;
import com.lab.manage.service.SysRoleService;
import com.lab.manage.shiro.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by Chengcheng on 2018/12/14.
 */
@RequestMapping("/sys/role")
@Controller
public class SysRoleController extends AbstractController{

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping
    public String index(){
        return "system/role/role.html";
    }

    @RequestMapping("/role_add")
    public String add(){
        return "system/role/role_add.html";
    }

    @RequestMapping("/role_edit/{roleId}")
    public String edit(@PathVariable("roleId") Integer roleId, Model model){
        SysRoleResult result = sysRoleService.findById(roleId);
        model.addAttribute("role",result);
        return "system/role/role_edit.html";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Response list(SysRoleForm sysRoleForm){
        SysUser userEntity = ShiroUtils.getUserEntity();
        sysRoleForm.setCompanyId(userEntity.getCompanyId());
        Object list = sysRoleService.findList(sysRoleForm);
        return this.response(Response.ResponseCode.SUCCESS).data(list);
    }

    @RequestMapping("/tree/{roleId}")
    @ResponseBody
    public Response findTree(@PathVariable("roleId") Integer roleId){
        SysUser sysUser = ShiroUtils.getUserEntity();
        Integer companyId = sysUser.getCompanyId();
        List<TreeMenu> tree;
        if(companyId == null && sysUser.getUsername().equals("admin")){
            tree = sysRoleService.findTree(roleId);
        }else{
            tree = sysRoleService.findTreeByRoleIdAndCompanyId(roleId,companyId);
        }
        return this.response(Response.ResponseCode.SUCCESS).data(tree);
    }

    @MyLog(requestUrl = "权限分配菜单")
    @RequestMapping("/tree/submit/{role}")
    @RequiresPermissions("sys:role:menu")
    @ResponseBody
    public Response treeSubmit(@RequestBody String[] treeIds, @PathVariable("role") Integer role){
        SysUser sysUser = ShiroUtils.getUserEntity();
        String username = sysUser.getUsername();
        if(username.equals("admin") || sysUser.getCompanyId() != null){
            sysRoleService.treeSubmit(role,treeIds);
        }
        return this.response(Response.ResponseCode.SUCCESS);
    }

    @MyLog(requestUrl = "新增角色")
    @RequestMapping("/add")
    @RequiresPermissions("sys:role:add")
    @ResponseBody
    public Response addRole(SysRole sysRole){
        try {
            SysUser sysUser = ShiroUtils.getUserEntity();
            Integer companyId = sysUser.getCompanyId();
            if(companyId != null){
                CompanyResult companyResult = companyService.findById(companyId);
                if(companyResult != null && companyResult.getAdmin() == 1){}else{
                    sysRole.setCompanyId(companyId);
                }
            }
            sysRole.setCreateBy(sysUser.getNickname());
            sysRole.setCreateTime(new Date());
            sysRoleService.addRole(sysRole);
        } catch (Exception e){
            logger.error("服务异常 {}",e);
            return this.response(Response.ResponseCode.FAILURE).message("服务异常");
        }

        return this.response(Response.ResponseCode.SUCCESS);
    }

    @MyLog(requestUrl = "修改角色")
    @RequestMapping("/edit")
    @RequiresPermissions("sys:role:edit")
    @ResponseBody
    public Response editRole(SysRole sysRole){
        try {
            SysUser sysUser = ShiroUtils.getUserEntity();
            sysRole.setUpdateBy(sysUser.getNickname());
            sysRole.setUpdateTime(new Date());
            sysRoleService.editRole(sysRole);
        } catch (Exception e){
            logger.error("服务异常 {}",e);
            return this.response(Response.ResponseCode.FAILURE).message("服务异常");
        }
        return this.response(Response.ResponseCode.SUCCESS);
    }


}
