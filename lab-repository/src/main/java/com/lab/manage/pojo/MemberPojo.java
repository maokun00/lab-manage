package com.lab.manage.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.lab.manage.domain.Member;

/**
 * Created by Chengcheng on 2018/12/20.
 */
@TableName("member")
public class MemberPojo extends Member {

    public MemberPojo() {
    }

    public MemberPojo(Member member) {
        this.setLocation(member.getLocation());
        this.setCityId(member.getCityId());
        this.setFax(member.getFax());
        this.setEmail(member.getEmail());
        this.setId(member.getId());
        this.setCompanyName(member.getCompanyName());
        this.setSource(member.getSource());
        this.setSysCompanyId(member.getSysCompanyId());
        this.setMemberId(member.getMemberId());
        this.setMobile(member.getMobile());
        this.setName(member.getName());
        this.setCreateBy(member.getCreateBy());
        this.setCreateTime(member.getCreateTime());
        this.setUpdateBy(member.getUpdateBy());
        this.setUpdateTime(member.getUpdateTime());
    }
}
