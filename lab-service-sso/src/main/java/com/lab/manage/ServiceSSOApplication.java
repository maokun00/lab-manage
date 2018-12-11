package com.lab.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by Chengcheng on 2018/10/22.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceSSOApplication {

    public static void main(String[] args){
        SpringApplication.run(ServiceSSOApplication.class,args);
    }

}
