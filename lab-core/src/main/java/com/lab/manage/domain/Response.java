package com.lab.manage.domain;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by Chengcheng on 2018/12/12.
 */
public class Response implements Serializable{

    private String message;

    private Integer status;

    private Object data;

    private Response(Builder builder){
        this.message = builder.message;
        this.status = builder.status;
        this.data = builder.data;
    }

    public Response message(String message){
        this.message = message;
        return this;
    }

    public Response status(int status){
        this.status = status;
        return this;
    }

    public Response data(Object data){
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public static class Builder {
        private String message;

        private Integer status;

        private Object data;

        public Builder() {

        }

        public Builder(int status) {
            this.status = status;
        }


        public Builder msg(String message) {
            this.message = message;
            return this;
        }

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder data(Object data){
            this.data=data;
            return this;
        }

        public Response build() {
            return new Response(this);
        }
    }

    public enum ResponseCode{


        SUCCESS(10000,"请求成功"),

        FAILURE(20000,"请求失败"),

        DATA(20003,"数据统计中"),

        UPGRADE (20002,"站点升级中"),

        NEED_LOGIN(20001,"重新登录"),

        SMSCODE_LOGIN(200001,"短信登陆"),

        LOGIN_LOCK(200002,"账号锁定"),

        PASS_ERROR(50000,"审核失败"),

        SIGN_ERROR(40000,"签名失败");


        private Integer number;
        private String message;

        ResponseCode(int number, String message){
            this.message = message;
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public String getMessage() {
            return message;
        }

        public static String message(Integer number){
            if (number != null){
                ResponseCode[] values = ResponseCode.values();
                for (ResponseCode code : values){
                    if(code.getNumber() == number){
                        return code.getMessage();
                    }
                }
            }
            return null;
        }

        public static Integer number(String message){
            if (message != null){
                ResponseCode[] values = ResponseCode.values();
                for (ResponseCode code : values){
                    if(code.getMessage().equals(message)){
                        return code.getNumber();
                    }
                }
            }
            return null;
        }
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
