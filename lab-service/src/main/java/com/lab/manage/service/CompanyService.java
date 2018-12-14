package com.lab.manage.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lab.manage.domain.Company;
import com.lab.manage.form.CompanyForm;
import com.lab.manage.mapper.CompanyMapper;
import com.lab.manage.pojo.CompanyPojo;
import com.lab.manage.result.CompanyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Chengcheng on 2018/12/14.
 */
@RequestMapping("/service/sys/company")
@RestController
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @RequestMapping("/findList")
    public Object findList(@RequestBody CompanyForm companyForm){
        PageHelper.startPage(companyForm.getOffset(),companyForm.getLimit());
        List<CompanyResult> resultList = companyMapper.findList(companyForm);
        return new PageInfo<>(resultList);
    }

    @RequestMapping("/findById")
    public CompanyResult findById(@RequestParam("companyId") Integer companyId){
        return companyMapper.findById(companyId);
    }

    @RequestMapping("/treeSubmit")
    @Transactional
    public void treeSubmit(@RequestParam("companyId") Integer companyId, @RequestParam("treeIds") String[] treeIds){
        companyMapper.removeMenuByCompanyId(companyId);
        companyMapper.pushMenu(companyId,treeIds);
    }

    @RequestMapping("/addCompany")
    public void addCompany(@RequestBody Company company){
        companyMapper.insert(new CompanyPojo(company));
    }

    @RequestMapping("/editCompany")
    public void editCompany(@RequestBody Company company) {
        companyMapper.updateById(new CompanyPojo(company));
    }
}
