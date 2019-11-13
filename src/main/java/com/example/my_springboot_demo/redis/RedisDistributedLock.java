package com.example.my_springboot_demo.redis;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.util.NumberUtils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: cdc
 * @Date: 2019/11/12 11:07
 */
public class RedisDistributedLock extends DistributedLock {

    private RedisOperations<String ,String> redisOperations;
    private String key;
    private String value;

    //通过Lua脚本进行解锁, 使对比锁的值+删除成为原子操作, 确保解锁操作的正确性. 简单来说就是防止删了别人的锁.
    private static final String COMPARE_AND_DELETE =        // (一)
            "if redis.call('get',KEYS[1]) == ARGV[1]\n" +
                    "then\n" +
                    "    return redis.call('del',KEYS[1])\n" +
                    "else\n" +
                    "    return 0\n" +
                    "end";

    public RedisDistributedLock(RedisOperations<String, String> redisOperations, String key, String value) {
        this.redisOperations = redisOperations;
        this.key = key;
        this.value = value;
    }

    @Override
    public void release() {
        List<String> keys = Collections.singletonList(key);
        //使用RedisOperations执行Lua脚本进行解锁操作.
        redisOperations.execute(new DefaultRedisScript<>(COMPARE_AND_DELETE), keys, value);
    }

    @Override
    public void close() throws Exception {
        super.close();
    }



    public RedisOperations<String, String> getRedisOperations() {
        return redisOperations;
    }

    public void setRedisOperations(RedisOperations<String, String> redisOperations) {
        this.redisOperations = redisOperations;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
