package com.lab.manage.controller;

import com.lab.manage.domain.Response;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Chengcheng on 2018/12/12.
 */
public abstract class AbstractController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected Response response(Response.ResponseCode code){
        return response(null,null,code);
    }

    protected Response response(String message,Response.ResponseCode code){
        return response(null,message,code);
    }

    protected Response response(Object data,Response.ResponseCode code){
        return new Response.Builder(code.getNumber()).data(data).build();
    }

    protected Response response(Object data,String message,Response.ResponseCode code){
        return new Response.Builder(code.getNumber()).data(data).msg(message).build();
    }
}
