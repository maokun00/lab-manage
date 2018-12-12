package com.lab.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by Chengcheng on 2018/12/12.
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.lab.manage.mapper.**"})
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

}
