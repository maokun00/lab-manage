package com.lab.manage.controller;

import com.lab.manage.domain.Response;
import com.lab.manage.domain.SysDictionary;
import com.lab.manage.enums.Dictionary;
import com.lab.manage.form.EntrustForm;
import com.lab.manage.service.EntrustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        List<SysDictionary> sampleType = entrustService.findType(Dictionary.SERVICE_TYPE.getType());
        List<SysDictionary> serviceType = entrustService.findType(Dictionary.SERVICE_TYPE.getType());
        List<SysDictionary> aptitudeType = entrustService.findType(Dictionary.APTITUDE_TYPE.getType());
        List<SysDictionary> residualType = entrustService.findType(Dictionary.RESIDUAL_TYPE.getType());
        List<SysDictionary> detectionType = entrustService.findType(Dictionary.DETECTION_TYPE.getType());
        model.addAttribute("serviceType",serviceType);
        model.addAttribute("sampleType",sampleType);
        model.addAttribute("aptitudeType",aptitudeType);
        model.addAttribute("residualType",residualType);
        model.addAttribute("detectionType",detectionType);
        return "entrust/info/entrust_info_add.html";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Response list(EntrustForm entrustForm){
        Object list = entrustService.findList(entrustForm);
        return this.response(Response.ResponseCode.SUCCESS).data(list);
    }



}
