package com.lab.manage.controller;

import com.lab.manage.config.MyLog;
import com.lab.manage.domain.Response;
import com.lab.manage.domain.SysDictionary;
import com.lab.manage.domain.SysUser;
import com.lab.manage.form.SysDictionaryForm;
import com.lab.manage.result.SysDictionaryResult;
import com.lab.manage.service.SysDictionaryService;
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

/**
 * Created by Chengcheng on 2018/12/20.
 */
@RequestMapping("/sys/dictionary")
@Controller
public class SysDictionaryController extends AbstractController{

    @Autowired
    private SysDictionaryService sysDictionaryService;

    @RequestMapping
    public String index(){
        return "system/dictionary/dictionary.html";
    }

    @RequestMapping("/dictionary_add")
    public String add(){
        return "system/dictionary/dictionary_add.html";
    }

    @RequestMapping("/dictionary_edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        SysDictionaryResult result = sysDictionaryService.findById(id);
        model.addAttribute("result",result);
        return "system/dictionary/dictionary_edit.html";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Response list(SysDictionaryForm form){
        Object list = sysDictionaryService.findList(form);
        return this.response(Response.ResponseCode.SUCCESS).data(list);
    }

    @MyLog(requestUrl = "添加字典")
    @RequestMapping("/add")
    @RequiresPermissions("sys:dictionary:add")
    @ResponseBody
    public Response addDictionary(SysDictionary sysDictionary){
        if(StringUtils.isBlank(sysDictionary.getName())) return this.response(Response.ResponseCode.FAILURE).message("请填写字典名称");
        if(StringUtils.isBlank(sysDictionary.getType())) return this.response(Response.ResponseCode.FAILURE).message("请填写类型编号");
        SysUser userEntity = ShiroUtils.getUserEntity();
        sysDictionary.setCreateBy(userEntity.getNickname());
        sysDictionary.setCreateTime(new Date());
        sysDictionaryService.addDictionary(sysDictionary);
        return this.response(Response.ResponseCode.SUCCESS);
    }

    @MyLog(requestUrl = "修改字典")
    @RequestMapping("/edit")
    @RequiresPermissions("sys:dictionary:edit")
    @ResponseBody
    public Response editDictionary(SysDictionary sysDictionary){
        if(StringUtils.isBlank(sysDictionary.getName())) return this.response(Response.ResponseCode.FAILURE).message("请填写字典名称");
        if(StringUtils.isBlank(sysDictionary.getType())) return this.response(Response.ResponseCode.FAILURE).message("请填写类型编号");
        SysUser userEntity = ShiroUtils.getUserEntity();
        sysDictionary.setUpdateBy(userEntity.getNickname());
        sysDictionary.setUpdateTime(new Date());
        sysDictionaryService.editDictionary(sysDictionary);
        return this.response(Response.ResponseCode.SUCCESS);
    }

}
