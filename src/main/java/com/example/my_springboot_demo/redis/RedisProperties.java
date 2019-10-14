package com.example.my_springboot_demo.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: cdc
 * @Date: 2019/10/14 14:54
 * @Description:读取redis配置信息并装载
 */
@Component
@ConfigurationProperties(prefix = "applicate")
public class RedisProperties {



}
