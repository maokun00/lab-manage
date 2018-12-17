package com.lab.manage.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lab.manage.domain.Response;
import com.lab.manage.domain.SysMenu;
import com.lab.manage.domain.SysUser;
import com.lab.manage.domain.TreeMenu;
import com.lab.manage.form.SysMenuForm;
import com.lab.manage.mapper.SysMenuMapper;
import com.lab.manage.mapper.SysRoleMapper;
import com.lab.manage.mapper.SysUserMapper;
import com.lab.manage.pojo.SysMenuPojo;
import com.lab.manage.pojo.SysUserPojo;
import com.lab.manage.result.IndexMenu;
import com.lab.manage.result.SysMenuResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Chengcheng on 2018/12/12.
 */
@RestController
@RequestMapping("/service/sys/menu")
public class SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * @Author Chengcheng
     * @Description : 获取授权列表
     * @Date 2018/12/17 上午9:39
     */
    @RequestMapping("/selectList")
    public List<String> selectList(){
        List<String> permsList = new ArrayList<>();
        List<SysMenuPojo> pojos = sysMenuMapper.selectList(null);
        permsList = new ArrayList<>(pojos.size());
        for(SysMenu menu : pojos){
            permsList.add(menu.getPerms());
        }
        return permsList;
    }

    /**
     * @Author Chengcheng
     * @Description : 获取菜单树
     * @Date 2018/12/17 上午9:39
     */
    @RequestMapping("/findTree")
    public List<TreeMenu> findTree(){
        return sysMenuMapper.findTree();
    }

    /**
     * @Author Chengcheng
     * @Description : index首页菜单树
     * @Date 2018/12/17 下午1:29
     */
    @RequestMapping("/indexMenu")
    public List<IndexMenu> indexMenu(@RequestParam("userId") Integer userId){
        List<Integer> roleId = sysRoleMapper.findByUserId(userId);
        if(roleId.isEmpty()) return new ArrayList<>();
        List<IndexMenu> indexMenus = check(0,roleId);
        return indexMenus;
    }

    private List<IndexMenu> check(Integer parentId,List<Integer> roleId){
        List<SysMenuResult> menuResults = sysMenuMapper.findByParentIdAndRoleId(parentId,roleId);
        List<IndexMenu> indexMenus = new ArrayList<>(menuResults.size());
        if(!menuResults.isEmpty()){
            for(SysMenuResult result : menuResults){
                IndexMenu indexMenu = new IndexMenu();
                indexMenu.setName(result.getName());
                indexMenu.setUrl(result.getUrl());
                indexMenu.setNode(check(result.getId(),roleId));
                indexMenus.add(indexMenu);
            }
        }
        return indexMenus;
    }

    /**
     * @Author Chengcheng
     * @Description : 根据用户id查询授权
     * @Date 2018/12/17 上午9:39
     */
    @RequestMapping("/queryAllPerms")
    public List<String> queryAllPerms(@RequestParam("userId") Integer userId){
        if(userId != null){
            List<Integer> roleIds = sysRoleMapper.findByUserId(userId);
            List<String> list = sysMenuMapper.queryAllPerms(roleIds);
            return list;
        }
        return null;
    }

    /**
     * @Author Chengcheng
     * @Description : 获取菜单列表
     * @Date 2018/12/17 上午9:40
     */
    @RequestMapping("/findList")
    public PageInfo<SysMenuResult> findList(SysMenuForm sysMenuForm){
        PageHelper.startPage(sysMenuForm.getOffset(),sysMenuForm.getLimit());
        List<SysMenuResult> sysMenuResults = sysMenuMapper.findList(sysMenuForm);
        PageInfo<SysMenuResult> pageInfo = new PageInfo<>(sysMenuResults);
        return pageInfo;
    }

    /**
     * @Author Chengcheng
     * @Description : 新增菜单
     * @Date 2018/12/17 上午9:40
     */
    @RequestMapping("/addMenu")
    public boolean addMenu(@RequestBody SysMenu sysMenu){
        if(sysMenu == null) return false;
        SysMenuPojo sysMenuPojo = new SysMenuPojo(sysMenu);
        sysMenuMapper.insert(sysMenuPojo);
        return true;
    }

    /**
     * @Author Chengcheng
     * @Description : 根据菜单id获取详情
     * @Date 2018/12/17 上午9:40
     */
    @RequestMapping("/findById")
    public SysMenuResult findById(@RequestParam("memuId") Integer memuId){
        SysMenuResult sysMenuResult = sysMenuMapper.findById(memuId);
        if(sysMenuResult != null){
            SysMenuResult parentMenu = sysMenuMapper.findById(sysMenuResult.getParentId());
            sysMenuResult.setParentName(parentMenu.getName());
        }
        return sysMenuResult;
    }

    /**
     * @Author Chengcheng
     * @Description : 修改菜单
     * @Date 2018/12/17 上午9:40
     */
    @RequestMapping("/editMenu")
    public Response editMenu(@RequestBody SysMenu sysMenu){
        if(sysMenu.getId() == null) return new Response.Builder(Response.ResponseCode.FAILURE.getNumber()).msg("缺少参数，请重新操作").build();
        sysMenuMapper.updateById(new SysMenuPojo(sysMenu));
        return new Response.Builder(Response.ResponseCode.SUCCESS.getNumber()).build();
    }

    /**
     * @Author Chengcheng
     * @Description : 删除菜单
     * @Date 2018/12/17 上午9:40
     */
    @RequestMapping("/remove")
    @Transactional
    public Response remove(@RequestParam("memuId") Integer menuId){
        List<SysMenuResult> results = sysMenuMapper.findByParentId(menuId);
        if(results.isEmpty()){
            sysMenuMapper.deleteById(menuId);
            sysMenuMapper.deleteRoleMenu(menuId);
            sysMenuMapper.deleteCompanyMenu(menuId);
            return new Response.Builder(Response.ResponseCode.SUCCESS.getNumber()).build();
        }
        return new Response.Builder(Response.ResponseCode.FAILURE.getNumber()).msg("请先删除子菜单").build();
    }
}
