package com.lab.manage.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Created by Chengcheng on 2018/12/12.
 */
public class ShiroUtils {

    /**  加密算法 */
    public final static String hashAlgorithmName = "SHA-256";
    /**  循环次数 */
    public final static int hashIterations = 16;

    public static String sha256(String password, String salt) {
        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
    }
}
