package com.lab.manage.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Chengcheng on 2018/12/12.
 */
@FeignClient(value = "lab-service")
public interface IndexService {

    @RequestMapping("/test")
    String test();

}
