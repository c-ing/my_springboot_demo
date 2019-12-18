package com.example.my_springboot_demo.no_if_else.V2;

import com.example.my_springboot_demo.no_if_else.UserType;
import org.springframework.stereotype.Component;

/**
 * @Author: cdc
 * @Date: 2019/12/18 10:40
 * @Description:白金会员策略
 */

@Component
public class PlatinumStrategyV2 implements StrategyV2 {

    @Override
    public double compute(long money) {
        System.out.println("白金会员 优惠50元，再打7折");
        return (money - 50) * 0.7;
    }

    @Override
    public int getType() {
        return UserType.PLATINUM_VIP.getCode();
    }
}
