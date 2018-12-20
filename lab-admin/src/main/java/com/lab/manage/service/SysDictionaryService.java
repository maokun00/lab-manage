package com.lab.manage.service;

import com.lab.manage.domain.SysDictionary;
import com.lab.manage.form.SysDictionaryForm;
import com.lab.manage.result.SysDictionaryResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Chengcheng on 2018/12/20.
 */
@FeignClient(value = "lab-service")
public interface SysDictionaryService {

    @RequestMapping("/service/sys/dictionary/findList")
    Object findList(SysDictionaryForm form);

    @RequestMapping("/service/sys/dictionary/addDictionary")
    void addDictionary(SysDictionary sysDictionary);

    @RequestMapping("/service/sys/dictionary/editDictionary")
    void editDictionary(SysDictionary sysDictionary);

    @RequestMapping("/service/sys/dictionary/findById")
    SysDictionaryResult findById(@RequestParam("id") Integer id);
}
