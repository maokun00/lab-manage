package com.lab.manage.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lab.manage.domain.Member;
import com.lab.manage.pojo.MemberPojo;

/**
 * Created by Chengcheng on 2018/12/20.
 */
public interface MemberMapper extends BaseMapper<MemberPojo> {

    Member findByCompanyName(String companyName);

}
