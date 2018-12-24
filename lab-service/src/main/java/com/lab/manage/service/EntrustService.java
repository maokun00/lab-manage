package com.lab.manage.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lab.manage.domain.EntrustInfo;
import com.lab.manage.domain.EntrustReport;
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

    /**
     * @Author Chengcheng
     * @Description : 根据id获取委托基本信息
     * @Date 2018/12/21 下午4:46  
     */
    @RequestMapping("/findById")
    public EntrustInfo findById(@RequestParam("id") Long id){
        return entrustInfoMapper.selectById(id);
    }

    /**
     * @Author Chengcheng
     * @Description : 根据关键字获取字典数据
     * @Date 2018/12/21 下午4:46  
     */
    @RequestMapping("/findType")
    public List<SysDictionary> findType(@RequestParam("type") String type){
        return sysDictionaryMapper.findByType(type);
    }

    /**
     * @Author Chengcheng
     * @Description : 根据关键字列表获取字典数据集合
     * @Date 2018/12/21 下午4:46
     */
    @RequestMapping("/findTypes")
    public List<SysDictionary> findTypes(@RequestParam("types") String[] stringArray){
        return sysDictionaryMapper.findTypes(stringArray);
    }

    /**
     * @Author Chengcheng
     * @Description : 获取委托信息列表
     * @Date 2018/12/21 下午4:46
     */
    @RequestMapping("/findList")
    public Object findList(@RequestBody EntrustForm entrustForm){
        PageHelper.startPage(entrustForm.getOffset(),entrustForm.getLimit());
        List<EntrustResult> results = entrustInfoMapper.findList(entrustForm);
        results.forEach(result -> {
            MemberPojo memberPojo = memberMapper.selectById(result.getMemberId());
            CompanyPojo companyPojo = companyMapper.selectById(memberPojo.getSysCompanyId());
            SysDictionaryPojo detection = sysDictionaryMapper.selectById(result.getDetectionType());
            SysDictionaryPojo service = sysDictionaryMapper.selectById(result.getServiceType());
            SysDictionaryPojo sampleType = sysDictionaryMapper.selectById(result.getSampleType());
            result.setDetectionTypeStr(detection.getLabel());
            result.setServiceTypeStr(service.getLabel());
            result.setSampleTypeStr(sampleType.getLabel());
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

    /**
     * @Author Chengcheng
     * @Description : 获取委托信息全部数据
     * @Date 2018/12/21 下午4:46
     */
    @RequestMapping("/findResultById")
    public EntrustResult findResultById(@RequestParam("id") Long id){
        EntrustResult result = entrustInfoMapper.findById(id);
        MemberPojo memberPojo = memberMapper.selectById(result.getMemberId());
        MemberPojo unionMemberPojo = memberMapper.selectById(result.getUnionMemberId());
        EntrustReport report = entrustReportMapper.findByInfoId(result.getId());
        CompanyPojo companyPojo = companyMapper.selectById(memberPojo.getSysCompanyId());
        try {
            Member member = EncryptionUtil.checkMember(memberPojo,companyPojo.getPrivateKey());
            Member unionMember = EncryptionUtil.checkMember(unionMemberPojo,companyPojo.getPrivateKey());
            result.setMember(member);
            result.setUnionMember(unionMember);
        }catch (Exception e){}
        result.setReport(report);
        return result;
    }

    /**
     * @Author Chengcheng
     * @Description : 新增委托信息
     * @Date 2018/12/21 下午4:46
     */
    @RequestMapping("/add")
    @Transactional
    public void addEntrust(@RequestBody EntrustResult entrustResult){
        //用户信息
        checkMember(entrustResult);
        //添加委托基本信息
        EntrustInfoPojo entrustInfoPojo = new EntrustInfoPojo(entrustResult);
        entrustInfoMapper.insert(entrustInfoPojo);
        entrustResult.setId(entrustInfoPojo.getId());
        //添加委托报告信息
        entrustReportMapper.insert(merge(entrustResult));

    }

    /**
     * @Author Chengcheng
     * @Description : 修改委托信息
     * @Date 2018/12/21 下午4:46
     */
    @RequestMapping("/edit")
    @Transactional
    public void editEntrust(@RequestBody EntrustResult entrustResult){
        //用户信息
        checkMember(entrustResult);
        entrustInfoMapper.updateById(new EntrustInfoPojo(entrustResult));
        entrustReportMapper.updateById(merge(entrustResult));
    }

    private void checkMember(EntrustResult entrustResult){
        //添加或修改 受检单位信息
        Member unionDataMember = merge(entrustResult,true);
        Member memberMerge = memberMerge(unionDataMember, entrustResult.getSysCompanyId());
        entrustResult.setUnionMemberId(memberMerge.getId());
        //添加或修改 委托单位信息
        Member dataMember = merge(entrustResult,false);
        Member memberMerge1 = memberMerge(dataMember, entrustResult.getSysCompanyId());
        entrustResult.setMemberId(memberMerge1.getId());
    }

    private EntrustReportPojo merge(EntrustResult entrustResult){
        EntrustReportPojo entrustReport = new EntrustReportPojo();
        entrustReport.setEntrustInfoId(entrustResult.getId());
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
            dataMember.setId(entrustResult.getUnionMemberId());
            dataMember.setCompanyName(entrustResult.getCompanyName());
            dataMember.setName(entrustResult.getUnionMemberName());
            dataMember.setMobile(entrustResult.getUnionMemberMobile());
            dataMember.setLocation(entrustResult.getUnionMemberLocation());
            dataMember.setCityId(entrustResult.getUnionCityId());
        }else{
            dataMember.setId(entrustResult.getMemberId());
            dataMember.setCompanyName(entrustResult.getCompanyName());
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

    private Member memberMerge(Member dataMember, Integer sysCompanyId){
        CompanyPojo companyPojo = companyMapper.selectById(sysCompanyId);
        try {
            dataMember = EncryptionUtil.encryptionMember(dataMember,companyPojo.getPublicKey());
        } catch (Exception e){}
        dataMember.setSysCompanyId(sysCompanyId);
        MemberPojo memberPojo = new MemberPojo(dataMember);
        if(memberPojo.getId() != null){
            memberMapper.updateById(memberPojo);
        }else{
            memberMapper.insert(memberPojo);
        }
        return memberPojo;
    }

}
