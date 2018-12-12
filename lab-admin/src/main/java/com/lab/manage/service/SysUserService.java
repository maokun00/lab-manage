package com.lab.manage.service;

import com.lab.manage.domain.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Chengcheng on 2018/12/12.
 */
@FeignClient(value = "lab-service")
public interface SysUserService {

    @RequestMapping("/service/sys/user/findByUsername")
    SysUser findByUsername(@RequestParam("username") String username);

    @RequestMapping("/service/sys/user/register")
    boolean register(@RequestParam("username") String username,
                     @RequestParam("password") String password,
                     @RequestParam("nickname") String nickname);
}
