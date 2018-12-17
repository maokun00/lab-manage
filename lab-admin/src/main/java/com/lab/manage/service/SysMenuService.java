package com.lab.manage.service;

import com.lab.manage.domain.Response;
import com.lab.manage.domain.SysMenu;
import com.lab.manage.domain.SysUser;
import com.lab.manage.domain.TreeMenu;
import com.lab.manage.form.SysMenuForm;
import com.lab.manage.result.IndexMenu;
import com.lab.manage.result.SysMenuResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Chengcheng on 2018/12/12.
 */
@FeignClient(value = "lab-service")
public interface SysMenuService {

    @RequestMapping("/service/sys/menu/selectList")
    List<String> selectList();

    @RequestMapping("/service/sys/menu/queryAllPerms")
    List<String> queryAllPerms(@RequestParam("userId") Integer userId);

    @RequestMapping("/service/sys/menu/findTree")
    List<TreeMenu> findTree();

    @RequestMapping("/service/sys/menu/findList")
    Object findList(@RequestParam("offset") Integer offset,@RequestParam("limit") Integer limit);

    @RequestMapping("/service/sys/menu/addMenu")
    boolean addMenu(SysMenu sysMenu);

    @RequestMapping("/service/sys/menu/findById")
    SysMenuResult findById(@RequestParam("memuId") Integer memuId);

    @RequestMapping("/service/sys/menu/editMenu")
    Object editMenu(SysMenu sysMenu);

    @RequestMapping("/service/sys/menu/remove")
    Object remove(@RequestParam("memuId") Integer menuId);

    @RequestMapping("/service/sys/menu/indexMenu")
    List<IndexMenu> indexMenu(@RequestParam("userId") Integer userId);
}
