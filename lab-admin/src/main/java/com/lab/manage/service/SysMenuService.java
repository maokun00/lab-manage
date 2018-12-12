package com.lab.manage.service;

import com.lab.manage.domain.SysMenu;
import org.springframework.cloud.openfeign.FeignClient;
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
}
