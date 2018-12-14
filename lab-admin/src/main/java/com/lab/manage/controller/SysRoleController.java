package com.lab.manage.controller;

import com.lab.manage.domain.Response;
import com.lab.manage.domain.SysRole;
import com.lab.manage.domain.SysUser;
import com.lab.manage.domain.TreeMenu;
import com.lab.manage.form.SysRoleForm;
import com.lab.manage.service.SysMenuService;
import com.lab.manage.service.SysRoleService;
import com.lab.manage.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private SysMenuService sysMenuService;

    @RequestMapping
    public String index(){
        return "system/role/role.html";
    }

    @RequestMapping("/role_add")
    public String add(){
        return "system/role/role_add.html";
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
        if(companyId == null){
            tree = sysRoleService.findTree(roleId);
        }else{
            tree = sysRoleService.findTreeByRoleIdAndCompanyId(roleId,companyId);
        }
        return this.response(Response.ResponseCode.SUCCESS).data(tree);
    }

    @RequestMapping("/add")
    public Response addRole(SysRole sysRole){
        SysUser sysUser = ShiroUtils.getUserEntity();
        Integer companyId = sysUser.getCompanyId();
        if(companyId == null){

        }
        return this.response(Response.ResponseCode.SUCCESS);
    }

}
