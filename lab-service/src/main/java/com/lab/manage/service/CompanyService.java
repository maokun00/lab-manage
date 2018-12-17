package com.lab.manage.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lab.manage.domain.Company;
import com.lab.manage.form.CompanyForm;
import com.lab.manage.mapper.CompanyMapper;
import com.lab.manage.mapper.SysRoleMapper;
import com.lab.manage.pojo.CompanyPojo;
import com.lab.manage.pojo.SysRolePojo;
import com.lab.manage.result.CompanyResult;
import com.lab.manage.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by Chengcheng on 2018/12/14.
 */
@RequestMapping("/service/sys/company")
@RestController
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * @Author Chengcheng
     * @Description : 获取公司列表
     * @Date 2018/12/17 上午9:37
     */
    @RequestMapping("/findList")
    public Object findList(@RequestBody CompanyForm companyForm){
        PageHelper.startPage(companyForm.getOffset(),companyForm.getLimit());
        List<CompanyResult> resultList = companyMapper.findList(companyForm);
        return new PageInfo<>(resultList);
    }

    /**
     * @Author Chengcheng
     * @Description : 根据id获取公司信息
     * @Date 2018/12/17 上午9:38
     */
    @RequestMapping("/findById")
    public CompanyResult findById(@RequestParam("companyId") Integer companyId){
        return companyMapper.findById(companyId);
    }

    /**
     * @Author Chengcheng
     * @Description : 根据公司设置菜单
     * @Date 2018/12/17 上午9:38  
     */
    @RequestMapping("/treeSubmit")
    @Transactional
    public void treeSubmit(@RequestParam("companyId") Integer companyId, @RequestParam("treeIds") String[] treeIds){
        companyMapper.removeMenuByCompanyId(companyId);
        if(treeIds.length > 0)companyMapper.pushMenu(companyId,treeIds);
    }

    /**
     * @Author Chengcheng
     * @Description : 给公司添加角色
     * @Date 2018/12/17 上午11:34
     */
    @RequestMapping("/roleSubmit")
    @Transactional
    public void roleSubmit(@RequestParam("companyId") Integer companyId, @RequestParam("roleIds") String[] roleIds){
        for(String id: roleIds){
            SysRolePojo sysRolePojo = sysRoleMapper.selectById(id);
            if(sysRolePojo != null && sysRolePojo.getCompanyId() == null){
                sysRolePojo.setCompanyId(companyId);
                sysRoleMapper.updateById(sysRolePojo);
            }
        }
    }

    /**
     * @Author Chengcheng
     * @Description : 新增公司
     * @Date 2018/12/17 上午9:38  
     */
    @RequestMapping("/addCompany")
    public void addCompany(@RequestBody Company company){
        Map<String, String> keyMap = EncryptionUtil.createKeys(512);
        String  publicKey = keyMap.get("publicKey");
        String  privateKey = keyMap.get("privateKey");
        company.setPublicKey(publicKey);
        company.setPrivateKey(privateKey);
        companyMapper.insert(new CompanyPojo(company));
    }

    /**
     * @Author Chengcheng
     * @Description : 修改公司
     * @Date 2018/12/17 上午9:38
     */
    @RequestMapping("/editCompany")
    public void editCompany(@RequestBody Company company) {
        companyMapper.updateById(new CompanyPojo(company));
    }
}
