package com.lab.manage.service;

import com.lab.manage.domain.EntrustInfo;
import com.lab.manage.domain.SysDictionary;
import com.lab.manage.enums.Dictionary;
import com.lab.manage.form.EntrustForm;
import com.lab.manage.result.EntrustResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Chengcheng on 2018/12/20.
 */
@FeignClient(value = "lab-service")
public interface EntrustService {

    @RequestMapping("/service/entrust/findList")
    Object findList(EntrustForm entrustForm);

    @RequestMapping("/service/entrust/findType")
    List<SysDictionary> findType(@RequestParam("type") String type);

    @RequestMapping("/service/entrust/findTypes")
    List<SysDictionary> findTypes(@RequestParam("types") String[] stringArray);

    @RequestMapping("/service/entrust/add")
    void addEntrust(EntrustResult entrustResult);

    @RequestMapping("/service/entrust/edit")
    void editEntrust(EntrustResult entrustResult);

    @RequestMapping("/service/entrust/findResultById")
    EntrustResult findResultById(@RequestParam("id") Long id);

    @RequestMapping("/service/entrust/findById")
    EntrustInfo findById(@RequestParam("id") Long id);
}
