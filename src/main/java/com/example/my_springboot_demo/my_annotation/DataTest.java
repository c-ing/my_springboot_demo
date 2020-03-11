package com.example.my_springboot_demo.my_annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author: cdc
 * @Date: 2019/12/20 10:32
 * @Description: 在创建一个DataTest注解,这里定义注解的目的就是如果使用了该注解在编译时打印出Hello World!。
 */

@Retention(RetentionPolicy.SOURCE)
public @interface DataTest {
}
