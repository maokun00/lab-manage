package com.lab.manage.controller;

import com.lab.manage.config.MyLog;
import com.lab.manage.domain.*;
import com.lab.manage.form.CompanyForm;
import com.lab.manage.service.CompanyService;
import com.lab.manage.service.SysRoleService;
import com.lab.manage.service.SysUserService;
import com.lab.manage.shiro.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.Cache;

import java.util.Date;
import java.util.List;

/**
 * Created by Chengcheng on 2018/12/14.
 */
@RequestMapping("/sys/company")
@Controller
public class CompanyController extends AbstractController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping
    public String index(Model model){
        List<SysRole> roles = sysRoleService.findNoCompanyRoleList();
        model.addAttribute("roles",roles);
        List<SysUser> users = sysUserService.findNoCompanyUser();
        model.addAttribute("users",users);
        return "system/company/company.html";
    }

    @RequestMapping("/company_add")
    public String add(){
        return "system/company/company_add.html";
    }

    @RequestMapping("/company_edit/{companyId}")
    public String edit(@PathVariable("companyId") Integer companyId,Model model){
        Company company = companyService.findById(companyId);
        model.addAttribute("company",company);
        return "system/company/company_edit.html";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Response list(CompanyForm companyForm){
        Object list = companyService.findList(companyForm);
        return this.response(Response.ResponseCode.SUCCESS).data(list);
    }

    @RequestMapping("/tree/{companyId}")
    @ResponseBody
    public Response findTree(@PathVariable("companyId") Integer companyId){
        Company company = companyService.findById(companyId);
        if(company != null){
            List<TreeMenu> tree = sysRoleService.findTreeByCompanyId(companyId);
            return this.response(Response.ResponseCode.SUCCESS).data(tree);
        }
        return this.response(Response.ResponseCode.SUCCESS);
    }

    @MyLog(requestUrl = "公司设置菜单")
    @RequestMapping("/tree/submit/{companyId}")
    @RequiresPermissions("sys:company:menu")
    @ResponseBody
    public Response treeSubmit(@RequestBody String[] treeIds,@PathVariable("companyId") Integer companyId){
        Company company = companyService.findById(companyId);
        if(company == null) return this.response(Response.ResponseCode.FAILURE).message("请重新选择公司！");
        try{
            companyService.treeSubmit(companyId,treeIds);
        }catch (Exception e){
            logger.error("服务异常 {}",e);
            return this.response(Response.ResponseCode.FAILURE).message("服务异常");
        }
        return this.response(Response.ResponseCode.SUCCESS);
    }

    @MyLog(requestUrl = "公司设置角色")
    @RequestMapping("/role/submit/{companyId}")
    @RequiresPermissions("sys:company:role")
    @ResponseBody
    public Response roleSubmit(@RequestBody String[] roleIds,@PathVariable("companyId") Integer companyId){
        Company company = companyService.findById(companyId);
        if(company == null) return this.response(Response.ResponseCode.FAILURE).message("请重新选择公司！");
        if(roleIds.length == 0) return this.response(Response.ResponseCode.SUCCESS);
        try {
            companyService.roleSubmit(companyId,roleIds);
        }catch (Exception e){
            logger.error("服务异常 {}",e);
            return this.response(Response.ResponseCode.FAILURE).message("服务异常");
        }
        return this.response(Response.ResponseCode.SUCCESS);
    }

    @MyLog(requestUrl = "公司设置用户")
    @RequestMapping("/user/submit/{companyId}")
    @RequiresPermissions("sys:company:user")
    @ResponseBody
    public Response userSubmit(@RequestBody String[] userIds,@PathVariable("companyId") Integer companyId){
        Company company = companyService.findById(companyId);
        if(company == null) return this.response(Response.ResponseCode.FAILURE).message("请重新选择公司！");
        if(userIds.length == 0) return this.response(Response.ResponseCode.SUCCESS);
        try {
            companyService.userSubmit(companyId,userIds);
        }catch (Exception e){
            logger.error("服务异常 {}",e);
            return this.response(Response.ResponseCode.FAILURE).message("服务异常");
        }
        return this.response(Response.ResponseCode.SUCCESS);
    }

    @MyLog(requestUrl = "新增公司")
    @RequestMapping("/add")
    @RequiresPermissions("sys:company:add")
    @ResponseBody
    public Response addCompany(Company company){
        if(StringUtils.isBlank(company.getName())) return this.response(Response.ResponseCode.FAILURE).message("请填写公司名称");
        if(StringUtils.isBlank(company.getPerson())) return this.response(Response.ResponseCode.FAILURE).message("请填写公司负责人");
        if(company.getEndTime() == null) return this.response(Response.ResponseCode.FAILURE).message("请设置过期时间");
        try {
            SysUser user = ShiroUtils.getUserEntity();
            company.setCreateBy(user.getNickname());
            company.setCreateTime(new Date());
            companyService.addCompany(company);
        }catch (Exception e){
            logger.error("服务异常 {}",e);
            return this.response(Response.ResponseCode.FAILURE).message("服务异常");
        }
        return this.response(Response.ResponseCode.SUCCESS);
    }

    @MyLog(requestUrl = "修改公司")
    @RequestMapping("/edit")
    @RequiresPermissions("sys:company:edit")
    @ResponseBody
    public Response editCompany(Company company){
        if(StringUtils.isBlank(company.getName())) return this.response(Response.ResponseCode.FAILURE).message("请填写公司名称");
        if(StringUtils.isBlank(company.getPerson())) return this.response(Response.ResponseCode.FAILURE).message("请填写公司负责人");
        if(company.getEndTime() == null) return this.response(Response.ResponseCode.FAILURE).message("请设置过期时间");
        try {
            SysUser user = ShiroUtils.getUserEntity();
            company.setUpdateBy(user.getNickname());
            company.setUpdateTime(new Date());
            companyService.editCompany(company);
        }catch (Exception e){
            logger.error("服务异常 {}",e);
            return this.response(Response.ResponseCode.FAILURE).message("服务异常");
        }
        return this.response(Response.ResponseCode.SUCCESS);
    }

}
