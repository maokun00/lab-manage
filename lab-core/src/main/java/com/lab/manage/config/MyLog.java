package com.lab.manage.config;

import java.lang.annotation.*;

/**
 * Created by Chengcheng on 2018/12/11.
 * 日志统计
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyLog {

    String requestUrl();
}
