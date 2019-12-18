package com.example.my_springboot_demo.no_if_else.V2;

import com.example.my_springboot_demo.no_if_else.UserType;
import org.springframework.stereotype.Component;

/**
 * @Author: cdc
 * @Date: 2019/12/18 10:52
 */

@Component
public class SilverStrategyV2 implements StrategyV2 {

    @Override
    public double compute(long money) {

        System.out.println("白银会员 优惠50元");
        return money - 50;
    }

    // type 返回
    @Override
    public int getType() {
        return UserType.SILVER_VIP.getCode();
    }
}
