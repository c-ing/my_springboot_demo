package com.example.my_springboot_demo.java反射;

import java.lang.reflect.Method;

import static sun.misc.Version.print;

/**
 * @Author: cdc
 * @Date: 2020/3/12 14:42
 */
public class Test {

    public static void target(int i) {
        new Exception("#" + i).printStackTrace();
    }

    public static void main(String[] args) throws Exception{
        // 写全路径：报名加类名
        Class<?> testClass = Class.forName("com.example.my_springboot_demo.java反射.Test");
        // 加入方法名和参数类型获取目标方法的实例
        Method method = testClass.getMethod("target", int.class);
        // 插入参数，调用方法
        method.invoke(null,0);

        //查看对象内部信息
        //print(ClassLayout.parseInstance(obj).toPrintable());

    }
}
