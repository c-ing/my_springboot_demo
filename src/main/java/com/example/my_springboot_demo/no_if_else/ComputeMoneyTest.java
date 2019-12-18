package com.example.my_springboot_demo.no_if_else;

import com.example.my_springboot_demo.no_if_else.V2.StrategyFactory;
import com.example.my_springboot_demo.no_if_else.V2.StrategyFactoryV2;
import com.example.my_springboot_demo.no_if_else.V2.StrategyV2;

/**
 * @Author: cdc
 * @Date: 2019/12/18 10:42
 * @Description: 计费策略测试类
 */
public class ComputeMoneyTest {

    // 旧版本
    private static double getResultV1(long money, int type) {
        double result = money;
        if (money >= 1000) {
            if (type == UserType.SILVER_VIP.getCode()) {

                result = new SilverStrategy().compute(money);
            } else if (type == UserType.GOLD_VIP.getCode()) {

                result = new GoldStrategy().compute(money);
            } else if (type == UserType.PLATINUM_VIP.getCode()) {

                result = new PlatinumStrategy().compute(money);
            } else {
                result = new OrdinaryStrategy().compute(money);
            }
        }

        return result;
    }

    //这里把 money < 1000 的情况提前 return。
    // 更关注于满1000逻辑 ,也可以减少不必要的缩进。
    // 但 if-else 依然存在
    private static double getResultV2(long money, int type) {
        if (money < 1000) {
            return money;
        }
        Strategy strategy;
        if (type == UserType.SILVER_VIP.getCode()) {
            strategy = new SilverStrategy();
        } else if (type == UserType.GOLD_VIP.getCode()) {

            strategy = new GoldStrategy();
        } else if (type == UserType.PLATINUM_VIP.getCode()) {

            strategy = new PlatinumStrategy();
        } else {
            strategy = new OrdinaryStrategy();
        }
        return strategy.compute(money);
    }

    // 工厂 + 策略
    private static double getResultV3(long money, int type) {
        if (money < 1000) {
            return money;
        }
        StrategyV2 strategy = StrategyFactory.getInstance().get(type);

        if (strategy == null){
            throw new IllegalArgumentException("please input right type");
        }
        return strategy.compute(money);
    }


    public static void main(String[] args) {
        ComputeMoneyTest.getResultV1(1000, 3);
        ComputeMoneyTest.getResultV2(1000, 3);
        ComputeMoneyTest.getResultV3(1000, 3);
    }
}
