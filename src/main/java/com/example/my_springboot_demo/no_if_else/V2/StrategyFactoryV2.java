package com.example.my_springboot_demo.no_if_else.V2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: cdc
 * @Date: 2019/12/18 14:50
 * @Description:利用spring 自动注解快速实现策略模式+工厂模式
 */
@Component
public class StrategyFactoryV2 {

    //关键功能 Spring 会自动将 StrategyV2 接口的类注入到这个list中
    @Autowired
    private List<StrategyV2> strategyV2s;

    public List<StrategyV2> getStrategyV2s() {
        return strategyV2s;
    }

    public StrategyV2 getStrategyV2ByType(Integer type) {
        if (type == null) {
            return null;
        }
        StrategyV2 strategyV2 = strategyV2s.stream().filter(s -> type.equals(s.getType())).findFirst().orElse(null);
        return strategyV2;
    }
}
