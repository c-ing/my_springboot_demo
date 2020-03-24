package com.example.my_springboot_demo.重构.充血枚举类型;

/**
 * @Auther: cdc
 * @Date: 2020/3/17 13:51
 * @Description: 定义一个包含通知实现机制的充血的枚举类型
 */
public enum NOTIFY_TYPE {
    email("邮件",NotifyMechanismInterface.byEmail());

    String memo;
    NotifyMechanismInterface notifyMechanismInterface;

    //2.私有构造函数，用于初始化枚举值
    private NOTIFY_TYPE(String memo, NotifyMechanismInterface notifyMechanismInterface) {
        this.memo = memo;
        this.notifyMechanismInterface = notifyMechanismInterface;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public NotifyMechanismInterface getNotifyMechanismInterface() {
        return notifyMechanismInterface;
    }

    public void setNotifyMechanismInterface(NotifyMechanismInterface notifyMechanismInterface) {
        this.notifyMechanismInterface = notifyMechanismInterface;
    }

    public static void main(String[] args) {
        NOTIFY_TYPE.email.getNotifyMechanismInterface().doNotify("邮件策略充血");
        NOTIFY_TYPE.valueOf("email").getNotifyMechanismInterface().doNotify("dd");
        System.out.println("email");
    }
}