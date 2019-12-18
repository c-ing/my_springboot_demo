package com.example.my_springboot_demo.no_if_else;

/**
 * @Author: cdc
 * @Date: 2019/12/18 10:40
 * @Description:白金会员策略
 */
public class PlatinumStrategy implements Strategy {

    @Override
    public double compute(long money) {
        System.out.println("白金会员 优惠50元，再打7折");
        return (money - 50) * 0.7;
    }
}
