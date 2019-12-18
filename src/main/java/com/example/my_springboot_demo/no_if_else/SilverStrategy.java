package com.example.my_springboot_demo.no_if_else;

/**
 * @Author: cdc
 * @Date: 2019/12/18 10:52
 */
public class SilverStrategy implements Strategy {

    @Override
    public double compute(long money) {
        System.out.println("白银会员 优惠50元");
        return money - 50;
    }
}
