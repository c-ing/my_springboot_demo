package com.example.my_springboot_demo.redis;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: cdc
 * @Date: 2019/10/14 15:38
 * @Descirbe: redis 相关bean的配置
 */

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{


}
