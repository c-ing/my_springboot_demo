package com.example.my_springboot_demo.no_if_else.V2;


import com.example.my_springboot_demo.no_if_else.UserType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: cdc
 * @Date: 2019/12/18 10:39
 */


@Component
public class GoldStrategyV2 implements StrategyV2 {

    @Override
    public double compute(long money) {
        System.out.println("黄金会员 8折");
        return  money * 8.5;
    }

    @Override
    public int getType() {
        return UserType.GOLD_VIP.getCode();
    }
}
