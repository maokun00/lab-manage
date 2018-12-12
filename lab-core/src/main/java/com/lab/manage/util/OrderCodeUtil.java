package com.lab.manage.util;

import java.util.Random;
import java.util.UUID;

/**
 * Created by zhoub on 2018/5/12.
 */

public  class OrderCodeUtil {
    //系统订单号
    public static String getOrderCode(){
        StringBuffer code = new StringBuffer("");
        String PT = code.append(getCode()).toString();
        return PT;
    }

    public static String PFTOrderCode(){
        StringBuffer code = new StringBuffer("P");
        String PFT = code.append(getCode()).toString();
        return PFT;
    }

    public static String getCode(){
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        String str = String.valueOf(hashCodeV);
        int x = str.length();
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            int next = Math.abs(new Random().nextInt(x) - 2);
            String a = str.substring(next, next + 1);
            sf.append(a);
        }
        return sf.toString();
    }

    /**
     * 核销码
     * @return
     */
    public static String  getRandomCode(){
        Integer b = UUID.randomUUID().toString().hashCode();
        if(b<0){
           b=-b;
        }
        return String.format("%06d",b.toString());
    }

}
