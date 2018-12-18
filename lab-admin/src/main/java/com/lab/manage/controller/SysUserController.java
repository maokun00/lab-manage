package com.lab.manage.controller;

import com.lab.manage.config.MyLog;
import com.lab.manage.domain.Company;
import com.lab.manage.domain.Response;
import com.lab.manage.domain.SysRole;
import com.lab.manage.domain.SysUser;
import com.lab.manage.form.SysUserForm;
import com.lab.manage.result.CompanyResult;
import com.lab.manage.result.SysRoleResult;
import com.lab.manage.result.SysUserResult;
import com.lab.manage.service.CompanyService;
import com.lab.manage.service.SysRoleService;
import com.lab.manage.service.SysUserService;
import com.lab.manage.shiro.ShiroUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Chengcheng on 2018/12/17.
 */
@RequestMapping("/sys/user")
@Controller
public class SysUserController extends AbstractController{

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping
    public String index(){
        return "system/user/user.html";
    }

    @RequestMapping("/user_add")
    @RequiresPermissions("sys:user:add")
    public String add(){
        return "system/user/user_add.html";
    }

    @RequestMapping("/user_edit/{userId}")
    @RequiresPermissions("sys:user:edit")
    public String edit(@PathVariable("userId") Integer userId, Model model){
        SysUserResult result = sysUserService.findById(userId);
        model.addAttribute("user",result);
        return "system/user/user_edit.html";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Response list(SysUserForm sysUserForm){
        SysUser userEntity = ShiroUtils.getUserEntity();
        Integer companyId = userEntity.getCompanyId();
        if(companyId == null) return this.response(Response.ResponseCode.SUCCESS);
        sysUserForm.setCompanyId(companyId);
        Object list = sysUserService.findList(sysUserForm);
        return this.response(Response.ResponseCode.SUCCESS).data(list);
    }

    @RequestMapping("/role/{userId}")
    public String roleUser(@PathVariable("userId") Integer userId, Model model){
        SysUserResult result = sysUserService.findById(userId);
        List<SysRole> roleList = new ArrayList<>();
        List<Integer> userRoles = new ArrayList<>();
        if(result != null){
            Integer companyId = result.getCompanyId();
            if(companyId != null){
                CompanyResult companyResult = companyService.findById(companyId);
                userRoles = sysUserService.findRoleByUserId(userId);
                roleList = sysRoleService.findByCompanyId(companyResult.getId());
            }
        }
        model.addAttribute("roles",roleList);
        model.addAttribute("userId",userId);
        model.addAttribute("userRoles",userRoles);
        return "system/user/user_role.html";
    }

    @MyLog(requestUrl = "设置角色")
    @RequestMapping("/role/submit/{userId}")
    @RequiresPermissions("sys:user:role")
    @ResponseBody
    public Response roleSubmit(@RequestBody String[] roleIds, @PathVariable("userId") Integer userId){
        SysUserResult user = sysUserService.findById(userId);
        if(user == null) return this.response(Response.ResponseCode.FAILURE).message("未获取到用户信息！");
        if(roleIds.length == 0) return this.response(Response.ResponseCode.SUCCESS);
        try {
            sysUserService.roleSubmit(userId,roleIds);
        }catch (Exception e){
            logger.error("服务异常 {}",e);
            return this.response(Response.ResponseCode.FAILURE).message("服务异常");
        }
        return this.response(Response.ResponseCode.SUCCESS);
    }

    @MyLog(requestUrl = "提交用户信息")
    @RequestMapping("/submit")
    @RequiresPermissions("sys:user:submit")
    @ResponseBody
    public Response submit(SysUser sysUser){
        SysUser username = sysUserService.findByUsername(sysUser.getUsername());
        SysUser userEntity = ShiroUtils.getUserEntity();
        CompanyResult company = companyService.findById(userEntity.getCompanyId());
        if(company == null) return this.response(Response.ResponseCode.FAILURE).message("未检测到登录账号所属公司");
        if(sysUser.getId() == null){
            if(StringUtils.isBlank(sysUser.getUsername())) return this.response(Response.ResponseCode.FAILURE).message("用户名为空");
            if(StringUtils.isBlank(sysUser.getPassword())) return this.response(Response.ResponseCode.FAILURE).message("密码为空");
            if(StringUtils.isBlank(sysUser.getNickname())) return this.response(Response.ResponseCode.FAILURE).message("平台昵称为空");
            if(username != null) return this.response(Response.ResponseCode.FAILURE).message("该账号已存在");
            sysUser.setCreateBy(userEntity.getNickname());
            if(company.getAdmin() != 1){
                sysUser.setCompanyId(company.getId());
            }
            sysUserService.register(sysUser);
        }else{
            sysUser.setUpdateBy(userEntity.getNickname());
            sysUser.setUpdateTime(new Date());
            sysUserService.updateUser(sysUser);
        }
        return this.response(Response.ResponseCode.SUCCESS);
    }

    @MyLog(requestUrl = "用户设置公司")
    @RequestMapping("/checkCompany/{userId}")
    @RequiresPermissions("sys:user:checkCompany")
    @ResponseBody
    public Response checkCompany(@PathVariable("userId") Integer userId){
        SysUser userEntity = ShiroUtils.getUserEntity();
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setUpdateBy(userEntity.getNickname());
        sysUser.setUpdateTime(new Date());
        Integer companyId = userEntity.getCompanyId();
        CompanyResult company = companyService.findById(companyId);
        if(company == null) return this.response(Response.ResponseCode.FAILURE).message("未检测到登录账号所属公司");
        sysUser.setCompanyId(companyId);
        sysUserService.updateUser(sysUser);
        return this.response(Response.ResponseCode.SUCCESS);
    }

    @MyLog(requestUrl = "禁用用户")
    @RequestMapping("/disable/{userId}")
    @RequiresPermissions("sys:user:disable")
    @ResponseBody
    public Response disable(@PathVariable("userId") Integer userId){
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setStatus(2);
        merge(sysUser);
        return this.response(Response.ResponseCode.SUCCESS);
    }

    @MyLog(requestUrl = "启用用户")
    @RequestMapping("/enable/{userId}")
    @RequiresPermissions("sys:user:enable")
    @ResponseBody
    public Response enable(@PathVariable("userId") Integer userId){
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setStatus(1);
        merge(sysUser);
        return this.response(Response.ResponseCode.SUCCESS);
    }

    @MyLog(requestUrl = "重置密码")
    @RequestMapping("/reset/{userId}")
    @RequiresPermissions("sys:user:reset")
    @ResponseBody
    public Response reset(@PathVariable("userId") Integer userId){
        SysUser sysUser = new SysUser();
        String salt = RandomStringUtils.randomAlphanumeric(20);
        String password = "12345678";
        sysUser.setPassword(ShiroUtils.sha256(password, salt));
        sysUser.setSalt(salt);
        sysUser.setId(userId);
        merge(sysUser);
        return this.response(Response.ResponseCode.SUCCESS);
    }


    private void merge(SysUser sysUser){
        SysUser userEntity = ShiroUtils.getUserEntity();
        sysUser.setUpdateBy(userEntity.getNickname());
        sysUser.setUpdateTime(new Date());
        sysUserService.updateUser(sysUser);
    }

}
