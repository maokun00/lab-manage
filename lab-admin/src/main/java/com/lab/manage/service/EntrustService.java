package com.lab.manage.service;

import com.lab.manage.domain.SysDictionary;
import com.lab.manage.enums.Dictionary;
import com.lab.manage.form.EntrustForm;
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
}
