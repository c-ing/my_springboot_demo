package com.example.my_springboot_demo.ctrler;

import com.example.my_springboot_demo.redis.RedisTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: cdc
 * @Date: 2019/10/14 15:16
 */
@Controller
@RequestMapping("/test")
public class RedisCtrler {

    @Autowired
    private RedisTemplateService redisTemplateService;

    @RequestMapping
    public void setRedisKey(@RequestParam String key, @RequestParam String value) {
        Boolean result = redisTemplateService.set(key, value);
        System.out.println("设置redis的结果"+result);
    }
}
