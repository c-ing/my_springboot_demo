package com.example.my_springboot_demo.设计模式.工厂模式.简单工厂模式;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: cdc
 * @Date: 2020/3/14 15:30
 */
public class RuleConfigParserFactory {

   /* private static final Map<String, IRuleConfigParser> cachedParsers = new HashMap<>();

    // 缓存实例对象
    static {
        cachedParsers.put("json", new JsonRuleConfigParser());
        cachedParsers.put("xml",new XmRuleConfigParser());
        cachedParsers.put("yaml",new YamlRuleConfigParser());
        cachedParsers.put("properties",new PropertiesRuleConfigParser());
    }

    public static IRuleConfigParser createParser(String configFormat) {
        if (configFormat == null || configFormat.isEmpty()) {
            return null;
        }
        return cachedParsers.get(configFormat.toLowerCase());
    }*/
}
