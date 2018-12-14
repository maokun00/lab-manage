package com.lab.manage.result;

import com.lab.manage.domain.Company;
import com.lab.manage.util.DateUtil;

/**
 * Created by Chengcheng on 2018/12/14.
 */
public class CompanyResult extends Company {

    public String getEndTimeStr(){
        return DateUtil.format(getEndTime(),"yyyy-MM-dd HH:mm");
    }

}
