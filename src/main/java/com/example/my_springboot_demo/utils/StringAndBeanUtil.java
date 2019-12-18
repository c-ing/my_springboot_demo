package com.example.my_springboot_demo.utils;

import com.alibaba.fastjson.JSON;

/**
 * @Author: cdc
 * @Date: 2019/10/14 15:10
 */
public class StringAndBeanUtil {

    public static <T> String beanToString(T value) {
        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class) {
            return value + "";
        }else if(clazz == String.class) {
            return (String)value;
        }else if(clazz == long.class || clazz == Long.class) {
            return value + "";
        }else {
            return JSON.toJSONString(value);

        }

    }

}
