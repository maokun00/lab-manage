package com.lab.manage.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by Chengcheng on 2018/4/30.
 */
@Component
public final class SpringUtil implements ApplicationContextAware {

    private static final Logger LOG = LoggerFactory.getLogger(SpringUtil.class);

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
        LOG.debug("---------------------------------------------------------------------");

        LOG.debug("---------------com.stylefeng.guns.core.util.SpringUtil------------------------------------------------------");

        LOG.debug("========ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象,applicationContext="+SpringUtil.applicationContext+"========");

        LOG.debug("---------------------------------------------------------------------");
    }

    /**
     * @Author Chengcheng
     * @Description :  获取applicationContext
     * @Date 2018/4/28 上午8:34
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @Author Chengcheng
     * @Description : 通过name获取 Bean.
     * @Date 2018/4/28 上午8:34
     */
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }


    /**
     * @Author Chengcheng
     * @Description : 通过class获取Bean.
     * @Date 2018/4/28 上午8:34
     */
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }


    /**
     * @Author Chengcheng
     * @Description :
     * @Date 2018/4/28 上午8:35
     */
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}
