package com.lab.manage;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by Chengcheng on 2018/9/8.
 */

@SpringBootApplication //springBoot注解,spring在springBoot基础之上来构建项目
@EnableEurekaServer //开启eureka服务
public class EurekaApplication {

    //启动服务
    public static void main(String[] args){
        new SpringApplicationBuilder(EurekaApplication.class).run(args);
    }

}
