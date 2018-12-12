package com.lab.manage.controller;

import com.lab.manage.service.IndexService;
import com.lab.manage.util.CreateImageCode;
import com.lab.manage.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Chengcheng on 2018/12/12.
 */
@Controller
public class LoginController extends AbstractController{

    @Autowired
    private IndexService indexService;

    @Value("${domain.path}")
    private String domainPath;

    private final static String ERP_KAPTCHA_CODE = "lab_";

    @RequestMapping("/login")
    public String toLogin(){
        return "login.html";
    }

    @RequestMapping("/test")
    public String test(){
        String test = indexService.test();
        logger.error(test);
        return test;
    }

    @RequestMapping("/kaptcha")
    public void kaptcha(HttpServletResponse response) throws IOException {
        CreateImageCode vCode = new CreateImageCode(100,30,5,10);
        String code = vCode.getCode();
        Cookie cookie = new Cookie(ERP_KAPTCHA_CODE, MD5Utils.MD5(code));
        cookie.setDomain(domainPath);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        vCode.write(response.getOutputStream());
    }

}
