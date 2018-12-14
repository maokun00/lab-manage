package com.lab.manage.service;

import com.lab.manage.domain.Company;
import com.lab.manage.form.CompanyForm;
import com.lab.manage.result.CompanyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Chengcheng on 2018/12/14.
 */
@FeignClient(value = "lab-service")
public interface CompanyService {

    @RequestMapping("/service/sys/company/findList")
    Object findList(CompanyForm companyForm);

    @RequestMapping("/service/sys/company/findById")
    CompanyResult findById(@RequestParam("companyId") Integer companyId);

    @RequestMapping("/service/sys/company/treeSubmit")
    void treeSubmit(@RequestParam("companyId") Integer companyId, @RequestParam("treeIds") String[] treeIds);

    @RequestMapping("/service/sys/company/addCompany")
    void addCompany(Company company);

    @RequestMapping("/service/sys/company/editCompany")
    void editCompany(Company company);
}
