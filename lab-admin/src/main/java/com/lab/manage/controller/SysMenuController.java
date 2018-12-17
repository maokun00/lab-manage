package com.lab.manage.controller;

import com.lab.manage.config.MyLog;
import com.lab.manage.domain.Response;
import com.lab.manage.domain.SysMenu;
import com.lab.manage.domain.TreeMenu;
import com.lab.manage.form.SysMenuForm;
import com.lab.manage.result.SysMenuResult;
import com.lab.manage.service.SysMenuService;
import com.lab.manage.shiro.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by Chengcheng on 2018/12/13.
 */
@RequestMapping("/sys/menu")
@Controller
public class SysMenuController extends AbstractController{

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping
    public String index(){
        return "system/menu/menu.html";
    }

    @RequestMapping("/menu_add")
    public String add(){
        return "system/menu/menu_add.html";
    }

    @RequestMapping("/menu_edit/{menuId}")
    public String edit(@PathVariable("menuId") Integer memuId, Model model){
        SysMenuResult sysMenuResult = sysMenuService.findById(memuId);
        model.addAttribute("menu",sysMenuResult);
        return "system/menu/menu_edit.html";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Response list(SysMenuForm sysMenuForm){
        Object list = sysMenuService.findList(sysMenuForm.getOffset(), sysMenuForm.getLimit());
        return this.response(Response.ResponseCode.SUCCESS).data(list);
    }

    @RequestMapping("/tree")
    @ResponseBody
    public Response tree(){
        List<TreeMenu> menuList = sysMenuService.findTree();
        return this.response(Response.ResponseCode.SUCCESS).data(menuList);
    }

    @MyLog(requestUrl = "添加菜单")
    @RequestMapping("/add")
    @RequiresPermissions("sys:menu:add")
    @ResponseBody
    public Response addMenu(SysMenu sysMenu){
        if(checkParam(sysMenu) != null) return checkParam(sysMenu);
        sysMenu.setCreateBy(ShiroUtils.getUserEntity().getNickname());
        sysMenu.setCreateTime(new Date());
        boolean b = sysMenuService.addMenu(sysMenu);
        return this.response(Response.ResponseCode.SUCCESS);
    }

    @MyLog(requestUrl = "修改菜单")
    @RequestMapping("/edit")
    @RequiresPermissions("sys:menu:edit")
    @ResponseBody
    public Object editMenu(SysMenu sysMenu){
        if(checkParam(sysMenu) != null) return checkParam(sysMenu);
        sysMenu.setUpdateBy(ShiroUtils.getUserEntity().getNickname());
        sysMenu.setUpdateTime(new Date());
        return sysMenuService.editMenu(sysMenu);
    }

    @MyLog(requestUrl = "删除菜单")
    @RequestMapping("/remove/{menuId}")
    @RequiresPermissions("sys:menu:remove")
    @ResponseBody
    public Object removeMenu(@PathVariable("menuId") Integer menuId){
        return sysMenuService.remove(menuId);
    }

    private Response checkParam(SysMenu sysMenu){
        if(StringUtils.isBlank(sysMenu.getName())) return this.response(Response.ResponseCode.FAILURE).message("缺少菜单名称");
        if(sysMenu.getParentId() == null) return this.response(Response.ResponseCode.FAILURE).message("请选择父级菜单");
        return null;
    }
}
