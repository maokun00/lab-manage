package com.lab.manage.service;

import com.lab.manage.domain.SysMenu;
import com.lab.manage.mapper.SysMenuMapper;
import com.lab.manage.pojo.SysMenuPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chengcheng on 2018/12/12.
 */
@RestController
@RequestMapping("/service/sys/menu")
public class SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

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

    @RequestMapping("/queryAllPerms")
    public List<String> queryAllPerms(@RequestParam("userId") Integer userId){

        return null;
    }

}
