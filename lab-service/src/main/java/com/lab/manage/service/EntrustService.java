package com.lab.manage.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lab.manage.domain.Member;
import com.lab.manage.domain.SysDictionary;
import com.lab.manage.enums.Dictionary;
import com.lab.manage.form.EntrustForm;
import com.lab.manage.mapper.*;
import com.lab.manage.pojo.*;
import com.lab.manage.result.CompanyResult;
import com.lab.manage.result.EntrustResult;
import com.lab.manage.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * Created by Chengcheng on 2018/12/20.
 */
@RequestMapping("/service/entrust")
@RestController
public class EntrustService {

    @Autowired
    private EntrustInfoMapper entrustInfoMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;
    @Autowired
    private EntrustReportMapper entrustReportMapper;

    @RequestMapping("/findType")
    public List<SysDictionary> findType(@RequestParam("type") String type){
        return sysDictionaryMapper.findByType(type);
    }

    @RequestMapping("/findList")
    public Object findList(@RequestBody EntrustForm entrustForm){
        PageHelper.startPage(entrustForm.getOffset(),entrustForm.getLimit());
        List<EntrustResult> results = entrustInfoMapper.findList(entrustForm);
        results.forEach(result -> {
            MemberPojo memberPojo = memberMapper.selectById(result.getId());
            CompanyPojo companyPojo = companyMapper.selectById(memberPojo.getSysCompanyId());
            SysDictionaryPojo detection = sysDictionaryMapper.selectById(result.getDetectionType());
            SysDictionaryPojo service = sysDictionaryMapper.selectById(result.getServiceType());
            result.setDetectionTypeStr(detection.getLabel());
            result.setServiceTypeStr(service.getLabel());
            Member member = null;
            try {
                member = EncryptionUtil.checkMember(memberPojo, companyPojo.getPrivateKey());
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            result.setCompanyName(member.getCompanyName());
        });
        return new PageInfo(results);
    }

    @RequestMapping("/add")
    @Transactional
    public void addEntrust(@RequestBody EntrustResult entrustResult){
        //用户信息
        checkMember(entrustResult);
        //添加委托基本信息
        entrustInfoMapper.insert(new EntrustInfoPojo(entrustResult));
        //添加委托报告信息
        entrustReportMapper.insert(merge(entrustResult));

    }

    @RequestMapping("/edit")
    @Transactional
    public void editEntrust(@RequestBody EntrustResult entrustResult){
        //用户信息
        checkMember(entrustResult);
        entrustInfoMapper.updateById(new EntrustInfoPojo(entrustResult));
        entrustReportMapper.updateById(merge(entrustResult));
    }

    private void checkMember(EntrustResult entrustResult){
        Member unionMember = memberMapper.findByCompanyName(entrustResult.getUnionCompanyName());
        Member member = memberMapper.findByCompanyName(entrustResult.getCompanyName());
        //添加或修改 受检单位信息
        Member unionDataMember = merge(entrustResult,true);
        memberMerge(unionMember,unionDataMember,entrustResult.getSysCompanyId());
        //添加或修改 委托单位信息
        Member dataMember = merge(entrustResult,false);
        memberMerge(member,dataMember,entrustResult.getSysCompanyId());
    }

    private EntrustReportPojo merge(EntrustResult entrustResult){
        EntrustReportPojo entrustReport = new EntrustReportPojo();
        entrustReport.setEntrustInfoId(entrustReport.getId());
        entrustReport.setId(entrustResult.getReportId());
        entrustReport.setName(entrustResult.getReportName());
        entrustReport.setMobile(entrustResult.getReportMobile());
        entrustReport.setLanguageType(entrustResult.getLanguageType());
        entrustReport.setReportType(entrustResult.getReportType());
        entrustReport.setContent(entrustResult.getReportContent());
        entrustReport.setCount(entrustResult.getReportCount());
        entrustReport.setModeType(entrustResult.getModeType());
        entrustReport.setReportingUnit(entrustResult.getReportingUnit());
        entrustReport.setLocation(entrustResult.getReportLocation());
        return entrustReport;
    }

    private Member merge(EntrustResult entrustResult,boolean isUnion){
        Member dataMember = new Member();
        if(isUnion){
            dataMember.setName(entrustResult.getUnionMemberName());
            dataMember.setMobile(entrustResult.getUnionMemberMobile());
            dataMember.setLocation(entrustResult.getUnionMemberLocation());
            dataMember.setCityId(entrustResult.getUnionCityId());
        }else{
            dataMember.setName(entrustResult.getMemberName());
            dataMember.setMobile(entrustResult.getMemberMobile());
            dataMember.setLocation(entrustResult.getMemberLocation());
            dataMember.setSource(entrustResult.getMemberSource());
            dataMember.setFax(entrustResult.getFax());
            dataMember.setEmail(entrustResult.getEmail());
            dataMember.setCityId(entrustResult.getCityId());
        }
        return dataMember;
    }

    private void memberMerge(Member member,Member dataMember, Integer sysCompanyId){
        CompanyResult company = companyMapper.findById(member.getSysCompanyId());
        try {
            dataMember = EncryptionUtil.encryptionMember(dataMember,company.getPublicKey());
        } catch (Exception e){}
        if(company != null && company.getId().equals(sysCompanyId)){
            dataMember.setId(member.getId());
            memberMapper.updateById(new MemberPojo(dataMember));
        }else{
            dataMember.setSysCompanyId(sysCompanyId);
            memberMapper.insert(new MemberPojo(dataMember));
        }
    }

}
