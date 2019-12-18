package com.example.my_springboot_demo.no_if_else.V2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: cdc
 * @Date: 2019/12/18 11:12
 * @Description: StrategyFactory 这里我使用的是静态内部类单例，在构造方法的时候，初始化好 需要的 Strategy，并把 list 转化为 map。
 * @note: 还可以改进的地方：可以尝试使用自定义注解，注解 Strategy 实现类 这样可以简化原来需在工厂类 List 添加一个 Stratey 策略
 */

@Component
public class StrategyFactory {

    //关键功能 Spring 会自动将 Strategy 接口的类注入到这个Map中

    private Map<Integer,StrategyV2> map;

    public StrategyFactory() {

        List<StrategyV2> strategies = new ArrayList<>();

        strategies.add(new OrdinaryStrategyV2());
        strategies.add(new SilverStrategyV2());
        strategies.add(new GoldStrategyV2());
        strategies.add(new PlatinumStrategyV2());

        map = strategies.stream().collect(Collectors.toMap(StrategyV2::getType,strategyV2 -> strategyV2));

         /* 等同上面
        map = new HashMap<>();
        for (Strategy strategy : strategies) {
            map.put(strategy.getType(), strategy);
        }*/
    }

    // 静态内部类单例，单例模式实现的一种
    public static class Holder {
        public static StrategyFactory instance = new StrategyFactory();
    }

    public static StrategyFactory getInstance() {
        return Holder.instance;
    }

    public StrategyV2 get(Integer type) {
        return map.get(type);
    }
}
