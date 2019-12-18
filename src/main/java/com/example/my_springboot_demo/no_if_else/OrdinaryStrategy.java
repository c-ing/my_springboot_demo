package com.example.my_springboot_demo.no_if_else;

/**
 * @Author: cdc
 * @Date: 2019/12/18 10:37
 * @Description: 普通会员策略
 */
public class OrdinaryStrategy implements Strategy {

    @Override
    public double compute(long money) {
        System.out.println("普通会员不打折");
        return  money;
    }
}
