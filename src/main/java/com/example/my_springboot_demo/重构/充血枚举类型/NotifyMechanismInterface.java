package com.example.my_springboot_demo.重构.充血枚举类型;

/**
 * @Auther: cdc
 * @Date: 2020/3/17 13:54
 * @Description:
 */

//3 定义通知机制的接口或抽象父类
public interface NotifyMechanismInterface {
    public boolean doNotify(String msg);

    // 3.1 返回一个定义了邮件通知机制的策略实现：一个匿名内部类实例
    public static NotifyMechanismInterface byEmail() {
        return new NotifyMechanismInterface() {
            @Override
            public boolean doNotify(String msg) {
                System.out.println("-----------"+ msg);
                return false;
            }
        };
    }
}
