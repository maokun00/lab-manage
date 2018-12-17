package com.lab.manage.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lab.manage.domain.SysRole;
import com.lab.manage.domain.TreeMenu;
import com.lab.manage.form.SysRoleForm;
import com.lab.manage.mapper.CompanyMapper;
import com.lab.manage.mapper.SysMenuMapper;
import com.lab.manage.mapper.SysRoleMapper;
import com.lab.manage.pojo.SysRolePojo;
import com.lab.manage.result.CompanyResult;
import com.lab.manage.result.SysRoleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    @Autowired
    private CompanyMapper companyMapper;

    /**
     * @Author Chengcheng
     * @Description : 角色列表
     * @Date 2018/12/17 上午9:33
     */
    @RequestMapping("/findList")
    public Object findList(@RequestBody SysRoleForm sysRoleForm){
        PageHelper.startPage(sysRoleForm.getOffset(),sysRoleForm.getLimit());
        List<SysRoleResult> results = sysRoleMapper.findList(sysRoleForm);
        for(SysRoleResult result : results){
            Integer companyId = result.getCompanyId();
            if(companyId != null){
                CompanyResult company = companyMapper.findById(companyId);
                result.setCompanyName(company.getName());
            }
        }
        return new PageInfo<>(results);
    }

    /**
     * @Author Chengcheng
     * @Description : 新增角色
     * @Date 2018/12/17 上午9:33
     */
    @RequestMapping("/addRole")
    public void addRole(@RequestBody SysRole sysRole){
        sysRoleMapper.insert(new SysRolePojo(sysRole));
    }

    /**
     * @Author Chengcheng
     * @Description : 修改角色
     * @Date 2018/12/17 上午9:53
     */
    @RequestMapping("/editRole")
    public void editRole(@RequestBody SysRole sysRole){
        sysRoleMapper.updateById(new SysRolePojo(sysRole));
    }

    /**
     * @Author Chengcheng
     * @Description : 根据角色id查询详情
     * @Date 2018/12/17 上午10:56
     */
    @RequestMapping("/findById")
    public SysRoleResult findById(@RequestParam("roleId") Integer roleId){
        return sysRoleMapper.findById(roleId);
    }

    /**
     * @Author Chengcheng
     * @Description : 获取未分配公司的角色
     * @Date 2018/12/17 上午9:59
     */
    @RequestMapping("/findNoCompanyRoleList")
    public List<SysRole> findNoCompanyRoleList(){
        return sysRoleMapper.findNoCompanyRoleList();
    }

    /**
     * @Author Chengcheng
     * @Description : 给角色分配菜单
     * @Date 2018/12/17 上午10:09
     */
    @RequestMapping("/treeSubmit")
    @Transactional
    public void treeSubmit(@RequestParam("roleId") Integer role, @RequestParam("treeIds") String[] treeIds){
        sysRoleMapper.removeByRoleId(role);
        if(treeIds.length > 0) sysRoleMapper.treeSubmit(role,treeIds);
    }

    /**
     * @Author Chengcheng
     * @Description : 根据角色获取菜单树
     * @Date 2018/12/17 上午9:33
     */
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

    /**
     * @Author Chengcheng
     * @Description : 根据公司id获取菜单树
     * @Date 2018/12/17 上午9:34
     */
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

    /**
     * @Author Chengcheng
     * @Description : 根据角色id和公司id获取菜单树
     * @Date 2018/12/17 上午9:34
     */
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
