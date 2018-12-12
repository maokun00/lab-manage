package com.lab.manage.service;

import com.lab.manage.mapper.SysUserMapper;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Chengcheng on 2018/12/12.
 */
@RestController
public class IndexService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @RequestMapping("/test")
    public String test(){
        return "ok";
    }

}
