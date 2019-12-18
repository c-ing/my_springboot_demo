package com.example.my_springboot_demo.no_if_else.V2;

import com.example.my_springboot_demo.no_if_else.UserType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: cdc
 * @Date: 2019/12/18 10:37
 * @Description: 普通会员策略
 */

@Component
public class OrdinaryStrategyV2 implements StrategyV2 {

    @Override
    public double compute(long money) {
        System.out.println("普通会员不打折");
        return  money;
    }

    // 添加type返回
    @Override
    public int getType() {
        return UserType.ORDINARY_VIP.getCode();
    }
}
