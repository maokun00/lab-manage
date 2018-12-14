package com.lab.manage.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lab.manage.domain.SysRole;
import com.lab.manage.domain.TreeMenu;
import com.lab.manage.form.SysRoleForm;
import com.lab.manage.mapper.SysMenuMapper;
import com.lab.manage.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Chengcheng on 2018/12/14.
 */
@RestController
@RequestMapping("/service/sys/role")
public class SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @RequestMapping("/findList")
    public Object findList(@RequestBody SysRoleForm sysRoleForm){
        PageHelper.startPage(sysRoleForm.getOffset(),sysRoleForm.getLimit());
        List<SysRole> sysRoles = sysRoleMapper.findList(sysRoleForm);
        return new PageInfo<>(sysRoles);
    }

    @RequestMapping("/findTree")
    public List<TreeMenu> findTree(@RequestParam("roleId") Integer roleId){
        List<TreeMenu> menuList = sysMenuMapper.findTree();
        List<Integer> menuId = sysRoleMapper.findTreeById(roleId);
        for(TreeMenu treeMenu : menuList){
            for(Integer id : menuId){
                if(treeMenu.getId().equals(id)){
                    treeMenu.setOpen(true);
                    treeMenu.setChecked(true);
                }
            }
        }
        return menuList;
    }

    @RequestMapping("/findTreeByCompanyId")
    public List<TreeMenu> findTreeByCompanyId(@RequestParam("companyId") Integer companyId){
        List<TreeMenu> menuList = sysMenuMapper.findTreeByCompanyId(companyId);
        List<TreeMenu> menus = sysMenuMapper.findTree();
        for(TreeMenu treeMenu : menuList){
            for(TreeMenu menu : menus){
                if(treeMenu.getId().equals(menu.getId())){
                    menu.setOpen(true);
                    menu.setChecked(true);
                }
            }
        }
        return menus;
    }

    @RequestMapping("/findTreeByRoleIdAndCompanyId")
    public List<TreeMenu> findTreeByRoleIdAndCompanyId(@RequestParam("roleId") Integer roleId, @RequestParam("companyId") Integer companyId){
        List<TreeMenu> menuList = sysMenuMapper.findTreeByCompanyId(companyId);
        List<Integer> menuId = sysRoleMapper.findTreeById(roleId);
        for(TreeMenu treeMenu : menuList){
            for(Integer id : menuId){
                if(treeMenu.getId().equals(id)){
                    treeMenu.setOpen(true);
                    treeMenu.setChecked(true);
                }
            }
        }
        return menuList;
    }

}
