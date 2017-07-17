package com.junqiang.www.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liujian on 17/7/17.
 */
public class SprintContext {
    private  static ClassPathXmlApplicationContext context = null;

    public static void init(){
        context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.start();
    }

    public static void init(String xmlName){
        context = new ClassPathXmlApplicationContext(xmlName);
        context.start();

    }
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<?> cls){
        if(context == null) init();
        return (T) context.getBean(cls);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name){
        if(context == null) init();
        return (T) context.getBean(name);
    }
}
