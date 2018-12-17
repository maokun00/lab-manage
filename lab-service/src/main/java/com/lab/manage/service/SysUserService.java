package com.lab.manage.service;

import com.lab.manage.domain.SysUser;
import com.lab.manage.mapper.SysUserMapper;
import com.lab.manage.pojo.SysUserPojo;
import com.lab.manage.util.ShiroUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Chengcheng on 2018/12/12.
 */
@RestController
@RequestMapping("/service/sys/user")
public class SysUserService {

    private final Logger logger = LoggerFactory.getLogger(SysUserService.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * @Author Chengcheng
     * @Description : 根据用户名获取用户
     * @Date 2018/12/17 上午9:39
     */
    @RequestMapping("/findByUsername")
    public SysUser findByUsername(@RequestParam("username") String username){
        if(StringUtils.isBlank(username)) {
            logger.warn("用户名为空");
            return null;
        }
        SysUser sysUser = sysUserMapper.findByUsername(username);
        return sysUser;
    }

    /**
     * @Author Chengcheng
     * @Description : 注册
     * @Date 2018/12/17 上午9:39
     */
    @RequestMapping("/register")
    public boolean register( @RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("nickname") String nickname){
        SysUserPojo sysUser = new SysUserPojo();
        sysUser.setUsername(username);
        sysUser.setPassword(password);
        sysUser.setNickname(nickname);
        SysUser user = sysUserMapper.findByUsername(sysUser.getUsername());
        if(user == null){
            String salt = RandomStringUtils.randomAlphanumeric(20);
            sysUser.setSalt(salt);
            sysUser.setStatus(1);
            sysUser.setPassword(ShiroUtils.sha256(sysUser.getPassword(), sysUser.getSalt()));
            sysUser.setCreateTime(new Date());
            sysUserMapper.insert(sysUser);
            return true;
        }
        return false;
    }

}
