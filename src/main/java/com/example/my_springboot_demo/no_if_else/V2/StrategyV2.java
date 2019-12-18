package com.example.my_springboot_demo.no_if_else.V2;

/**
 * @Author: cdc
 * @Date: 2019/12/18 11:05
 */
public interface StrategyV2 {

    double compute(long money);

    // 返回 type
    int getType();
}
