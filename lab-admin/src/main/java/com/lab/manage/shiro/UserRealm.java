package com.lab.manage.shiro;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Chengcheng on 2018/6/27.
 */
@Component
public class UserRealm extends AuthorizingRealm {

//    @Autowired
//    private SysUserMapper sysUserMapper;
//    @Autowired
//    private SysMenuMapper sysMenuMapper;


    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        SysUser user = (SysUser)principals.getPrimaryPrincipal();
//        Integer userId = user.getUserId();
//
//        List<String> permsList = null;
//
//        //系统管理员，拥有最高权限
//        if(user.getUsername().equals("admin") ){
//            List<SysMenu> menuList = sysMenuMapper.selectList(null);
//            permsList = new ArrayList<>(menuList.size());
//            for(SysMenu menu : menuList){
//                permsList.add(menu.getPerms());
//            }
//        }else{
//            permsList = sysUserMapper.queryAllPerms(userId);
//        }

        //用户权限列表
//        Set<String> permsSet = new HashSet<>();
//        for(String perms : permsList){
//            if(StringUtils.isBlank(perms)){
//                continue;
//            }
//            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
//        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;

        //查询用户信息
//        SysUser user = new SysUser();
//        user.setUsername(token.getUsername());
//        user = sysUserMapper.selectOne(user);

//        //账号不存在
//        if(user == null) {
//            throw new UnknownAccountException("账号或密码不正确");
//        }
//        Integer status = 1;
//        //账号锁定
//        if(status == 0){
//            throw new LockedAccountException("账号已被锁定,请联系管理员");
//        }
//
//        List<Integer> list = sysUserMapper.hasProject(user.getUserId());
//        if(!list.isEmpty()){
//            user.setHasDevelop(true);
//        }

//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        return null;
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
        shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }

}
