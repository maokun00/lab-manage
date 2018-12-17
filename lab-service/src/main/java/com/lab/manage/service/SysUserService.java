package com.lab.manage.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lab.manage.domain.SysUser;
import com.lab.manage.form.SysUserForm;
import com.lab.manage.mapper.CompanyMapper;
import com.lab.manage.mapper.SysUserMapper;
import com.lab.manage.pojo.CompanyPojo;
import com.lab.manage.pojo.SysUserPojo;
import com.lab.manage.result.CompanyResult;
import com.lab.manage.result.SysUserResult;
import com.lab.manage.util.ShiroUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by Chengcheng on 2018/12/12.
 */
@RestController
@RequestMapping("/service/sys/user")
public class SysUserService {

    private final Logger logger = LoggerFactory.getLogger(SysUserService.class);

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private CompanyMapper companyMapper;

    @RequestMapping("/findById")
    public SysUserResult findById(@RequestParam("userId") Integer userId){
        return sysUserMapper.findById(userId);
    }

    /**
     * @Author Chengcheng
     * @Description : 用户列表
     * @Date 2018/12/17 下午4:08
     */
    @RequestMapping("/findList")
    public Object findList(@RequestBody SysUserForm sysUserForm){
        PageHelper.startPage(sysUserForm.getOffset(),sysUserForm.getLimit());
        CompanyResult result = companyMapper.findById(sysUserForm.getCompanyId());
        List<SysUserResult> resultList;
        if(result != null && result.getAdmin() == 1){
            sysUserForm.setCompanyId(null);
            resultList = sysUserMapper.findList(sysUserForm);
            List<CompanyPojo> companyPojoList = companyMapper.selectList(null);
            companyPojoList.forEach(company -> {
                resultList.forEach(user -> {
                    if(company.getId().equals(user.getCompanyId())){
                        user.setCompanyName(company.getName());
                    }
                });
            });
        }else{
            resultList = sysUserMapper.findList(sysUserForm);
            resultList.forEach(user -> {
                user.setCompanyName(result.getName());
            });
        }

        return new PageInfo<>(resultList);
    }

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

    @RequestMapping("/findNoCompanyUser")
    public List<SysUser> findNoCompanyUser(){
        return sysUserMapper.findNoCompanyUser();
    }

    /**
     * @Author Chengcheng
     * @Description : 注册
     * @Date 2018/12/17 上午9:39
     */
    @RequestMapping("/register")
    public boolean register(@RequestBody SysUser sysUser){
        SysUserPojo pojo = new SysUserPojo();
        pojo.setUsername(sysUser.getUsername());
        pojo.setPassword(sysUser.getPassword());
        pojo.setNickname(sysUser.getNickname());
        pojo.setCreateBy(sysUser.getCreateBy());
        pojo.setCompanyId(sysUser.getCompanyId());
        SysUser user = sysUserMapper.findByUsername(sysUser.getUsername());
        if(user == null){
            String salt = RandomStringUtils.randomAlphanumeric(20);
            sysUser.setSalt(salt);
            sysUser.setStatus(1);
            sysUser.setPassword(ShiroUtils.sha256(sysUser.getPassword(), sysUser.getSalt()));
            sysUser.setCreateTime(new Date());
            sysUserMapper.insert(pojo);
            return true;
        }
        return false;
    }

    /**
     * @Author Chengcheng
     * @Description : 更新用户信息
     * @Date 2018/12/17 下午4:08
     */
    @RequestMapping("/updateUser")
    public void updateUser(@RequestBody SysUser sysUser){
        sysUserMapper.updateById(new SysUserPojo(sysUser));
    }

}
