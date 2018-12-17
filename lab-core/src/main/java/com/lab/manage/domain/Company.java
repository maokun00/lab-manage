package com.lab.manage.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Chengcheng on 2018/12/12.
 */
public class Company extends BaseDomain {

    private Integer id;

    private String name;

    private String publicKey;

    private String privateKey;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private String person;

    private String personMobile;

    private Integer admin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getPersonMobile() {
        return personMobile;
    }

    public void setPersonMobile(String personMobile) {
        this.personMobile = personMobile;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }
}
