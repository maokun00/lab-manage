package com.lab.manage.controller;

import com.lab.manage.config.MyLog;
import com.lab.manage.domain.EntrustInfo;
import com.lab.manage.domain.Response;
import com.lab.manage.domain.SysDictionary;
import com.lab.manage.domain.SysUser;
import com.lab.manage.enums.Dictionary;
import com.lab.manage.form.EntrustForm;
import com.lab.manage.result.EntrustResult;
import com.lab.manage.service.EntrustService;
import com.lab.manage.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Chengcheng on 2018/12/20.
 */
@RequestMapping("/entrust")
@Controller
public class EntrustController extends AbstractController{

    @Autowired
    private EntrustService entrustService;

    @RequestMapping
    public String index(){return "entrust/info/entrust_info.html";}

    @RequestMapping("/entrust_add")
    public String add(Model model){
        checkModel(model);
        return "entrust/info/entrust_info_add.html";
    }

    @RequestMapping("/entrust_edit/{id}")
    public String add(@PathVariable("id") Long id, Model model){
        checkModel(model);
        EntrustResult entrustResult = entrustService.findResultById(id);
        model.addAttribute("result",entrustResult);
        return "entrust/info/entrust_info_edit.html";
    }

    @RequestMapping("/entrust_sample/{id}")
    public String sample(@PathVariable("id") Long id, Model model){
        EntrustInfo info = entrustService.findById(id);
        List<SysDictionary> type = entrustService.findType(Dictionary.SAMPLE_TYPE.getType());
        for(SysDictionary dictionary : type){
            if(dictionary.getId().equals(info.getSampleType())){
                return "entrust/info/" + dictionary.getValue();
            }
        }
        return "";
    }

    @MyLog(requestUrl = "添加委托信息")
    @RequestMapping("/add")
    @ResponseBody
    public Response addEntrust(EntrustResult entrustResult){
        SysUser userEntity = ShiroUtils.getUserEntity();
        Integer companyId = userEntity.getCompanyId();
        if(companyId == null) this.response(Response.ResponseCode.FAILURE).message("提交失败");
        entrustResult.setSysCompanyId(companyId);
        entrustResult.setCreateBy(userEntity.getNickname());
        entrustResult.setCreateTime(new Date());
        entrustResult.setStatus(1);
        entrustService.addEntrust(entrustResult);
        return this.response(Response.ResponseCode.SUCCESS);
    }

    @MyLog(requestUrl = "修改委托信息")
    @RequestMapping("/edit")
    @ResponseBody
    public Response editEntrust(EntrustResult entrustResult){
        SysUser userEntity = ShiroUtils.getUserEntity();
        Integer companyId = userEntity.getCompanyId();
        if(companyId == null) this.response(Response.ResponseCode.FAILURE).message("提交失败");
        entrustResult.setSysCompanyId(companyId);
        entrustResult.setUpdateBy(userEntity.getNickname());
        entrustResult.setUpdateTime(new Date());
        entrustService.editEntrust(entrustResult);
        return this.response(Response.ResponseCode.SUCCESS);
    }

    @RequestMapping("/list")
    @ResponseBody
    public Response list(EntrustForm entrustForm){
        Object list = entrustService.findList(entrustForm);
        return this.response(Response.ResponseCode.SUCCESS).data(list);
    }


    private void checkModel(Model model){
        Dictionary[] dictionaries = Dictionary.values();
        String[] stringArray = new String[dictionaries.length];
        for(int i = 0; i < dictionaries.length; i++){
            stringArray[i] = dictionaries[i].getType();
        }
        List<SysDictionary> types = entrustService.findTypes(stringArray);
        List<SysDictionary> sampleType = new ArrayList<>(),
                            serviceType = new ArrayList<>(),
                            aptitudeType = new ArrayList<>(),
                            residualType = new ArrayList<>(),
                            detectionType = new ArrayList<>(),
                            reportType = new ArrayList<>(),
                            modeType = new ArrayList<>(),
                            languageType = new ArrayList<>();

        types.forEach(type -> {
            switch (Dictionary.byType(type.getType())){
                case SERVICE_TYPE:
                    serviceType.add(type);
                    break;
                case SAMPLE_TYPE:
                    sampleType.add(type);
                    break;
                case APTITUDE_TYPE:
                    aptitudeType.add(type);
                    break;
                case RESIDUAL_TYPE:
                    residualType.add(type);
                    break;
                case DETECTION_TYPE:
                    detectionType.add(type);
                    break;
                case REPORT_TYPE:
                    reportType.add(type);
                    break;
                case MODE_TYPE:
                    modeType.add(type);
                    break;
                case LANGUAGE_TYPE:
                    languageType.add(type);
                    break;
            }
        });
        model.addAttribute("reportType",reportType);
        model.addAttribute("modeType",modeType);
        model.addAttribute("languageType",languageType);
        model.addAttribute("serviceType",serviceType);
        model.addAttribute("sampleType",sampleType);
        model.addAttribute("aptitudeType",aptitudeType);
        model.addAttribute("residualType",residualType);
        model.addAttribute("detectionType",detectionType);
    }
}
