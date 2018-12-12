package com.lab.manage.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by admin on 2017/12/11.
 * 生成内部订单号工具类
 */
public class TradeNum {
    /**
     * @return 订单号
     */
    public static String getTradeNo(Long userId){
        DateFormat df = new SimpleDateFormat("yyMMddHHmmssSSS");
        String now = df.format(new Date());
        StringBuffer  tradeNum = new StringBuffer();
        tradeNum.append(userId==null?"":userId).append(now);
        int randNum = getRandom(100,1000);
        tradeNum.append(randNum);
        return tradeNum.toString();
    }

    public static int getRandom(int begin,int end){
        Random randNum = new Random();
        return randNum.nextInt(end) + begin;
    }
}
