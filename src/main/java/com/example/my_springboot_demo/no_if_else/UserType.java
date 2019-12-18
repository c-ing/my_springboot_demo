package com.example.my_springboot_demo.no_if_else;

/**
 * @Author: cdc
 * @Date: 2019/12/18 10:46
 */
public enum  UserType {
    ORDINARY_VIP("普通会员",1),
    SILVER_VIP("白银会员",2),
    GOLD_VIP("黄金会员",3),
    PLATINUM_VIP("白金会员",4)
    ;

    private String value;
    private Integer code;

    UserType(String value, Integer code) {
        this.value = value;
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
