package com.example.my_springboot_demo.no_if_else;

/**
 * @Author: cdc
 * @Date: 2019/12/18 10:39
 */
public class GoldStrategy implements Strategy {

    @Override
    public double compute(long money) {
        System.out.println("黄金会员 8折");
        return  money * 8.5;
    }
}
