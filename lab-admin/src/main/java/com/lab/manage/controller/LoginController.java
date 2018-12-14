package com.lab.manage.controller;

import com.lab.manage.domain.Response;
import com.lab.manage.domain.SysUser;
import com.lab.manage.form.SysUserForm;
import com.lab.manage.service.SysUserService;
import com.lab.manage.shiro.ShiroUtils;
import com.lab.manage.util.CreateImageCode;
import com.lab.manage.util.MD5Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private SysUserService sysUserService;

    @Value("${domain.path}")
    private String domainPath;

    private final static String ERP_KAPTCHA_CODE = "lab_";

    @RequestMapping("/login")
    public String toLogin(){
        return "login.html";
    }

    @RequestMapping("/index")
    public String index(Model model){
        SysUser userEntity = ShiroUtils.getUserEntity();
        model.addAttribute("userName",userEntity.getNickname());
        return "index.html";
    }

    @RequestMapping("/register")
    public String register(){
        return "register.html";
    }

    @RequestMapping("/login/submit")
    @ResponseBody
    public Response loginSubmit(HttpServletRequest request, SysUserForm sysUser, Model model,HttpServletResponse response){
        try{
            Object attribute = null;
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie :cookies){
                if(ERP_KAPTCHA_CODE.equals(cookie.getName())){
                    attribute = cookie.getValue();
                    cookie.setMaxAge(0);
                    cookie.setValue(null);
                    response.addCookie(cookie);
                }
            }
            try{
                String code = (String) attribute;
                if(StringUtils.isBlank(code) || !code.equalsIgnoreCase(MD5Utils.MD5(sysUser.getKaptcha()))){
                    return new Response.Builder(Response.ResponseCode.FAILURE.getNumber()).msg("验证码错误").build();
                }
            }catch (Exception e){
                return new Response.Builder(Response.ResponseCode.FAILURE.getNumber()).msg("请重新操作，如果重复出现该问题请联系管理员!!").build();
            }
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUsername(), sysUser.getPassword());
            subject.login(token);
        }catch (IncorrectCredentialsException e) {
            return new Response.Builder(Response.ResponseCode.FAILURE.getNumber()).msg("账号或密码不正确!").build();
        }catch (LockedAccountException e) {
            return new Response.Builder(Response.ResponseCode.FAILURE.getNumber()).msg("账号已被锁定,请联系管理员!").build();
        }catch (AuthenticationException e) {
            model.addAttribute("header","登录失败");
            model.addAttribute("context","账户验证失败!");
            return new Response.Builder(Response.ResponseCode.FAILURE.getNumber()).msg("账户验证失败!").build();
        }
        return new Response.Builder(Response.ResponseCode.SUCCESS.getNumber()).data("/index").build();
    }

    @RequestMapping("/register/submit")
    @ResponseBody
    public Response register(HttpServletRequest request, SysUserForm sysUser, Model model, HttpServletResponse response){
        if(sysUser == null
                || StringUtils.isBlank(sysUser.getUsername())
                || StringUtils.isBlank(sysUser.getPassword())
                || StringUtils.isBlank(sysUser.getConfirmPassword())
                || StringUtils.isBlank(sysUser.getKaptcha())){
            return new Response.Builder(Response.ResponseCode.FAILURE.getNumber()).msg("缺少必要参数!").build();
        }
        Object attribute = null;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie :cookies){
            if(ERP_KAPTCHA_CODE.equals(cookie.getName())) {
                attribute = cookie.getValue();
                cookie.setMaxAge(0);
                cookie.setValue(null);
                response.addCookie(cookie);
            }
        }
        try {
            String code = (String) attribute;
            if(!code.equals(MD5Utils.MD5(sysUser.getKaptcha()))){
                return new Response.Builder(Response.ResponseCode.FAILURE.getNumber()).msg("验证码错误!").build();
            }
            if(!sysUser.getPassword().equals(sysUser.getConfirmPassword())){
                return new Response.Builder(Response.ResponseCode.FAILURE.getNumber()).msg("密码输入不一致!").build();
            }
            SysUser user = sysUserService.findByUsername(sysUser.getUsername());
            if(user != null){
                return new Response.Builder(Response.ResponseCode.FAILURE.getNumber()).msg("该用户名已经存在!").build();
            }
            boolean register = sysUserService.register(sysUser.getUsername(), sysUser.getPassword(), sysUser.getNickname());
            if(register){
                return new Response.Builder(Response.ResponseCode.SUCCESS.getNumber()).data("/login").build();
            }
            return new Response.Builder(Response.ResponseCode.FAILURE.getNumber()).msg("用户已存在!").build();
        }catch (Exception e){
            logger.error("注册异常 :",e);
            return new Response.Builder(Response.ResponseCode.FAILURE.getNumber()).msg("请重新操作，如果重复出现该问题请联系管理员!").build();
        }
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

    @RequestMapping("/logout")
    public String logout(){
        ShiroUtils.logout();
        return "redirect:/login";
    }

    @RequestMapping("/errorpage")
    public String errorPage(HttpServletRequest request,Model model){
        try {
            String header = (String) request.getAttribute("header");
            String context = (String) request.getAttribute("context");
            model.addAttribute("header",header);
            model.addAttribute("context",context);
            return "404.html";
        }catch (Exception e){
            model.addAttribute("header","500");
            model.addAttribute("context","系统异常，请重新登录!");
            logger.error("错误 {}",e);
            return "404.html";
        }

    }

}
