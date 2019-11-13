package com.example.my_springboot_demo.redis;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: cdc
 * @Date: 2019/10/14 15:56
 */

@Component
public class RedisService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 默认过期时长，单位：秒
     */
    public static final long DEFAULT_EXPIRE = 60 * 60 * 24;

    /**
     * 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1;




    public boolean existsKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 重名名key，如果newKey已经存在，则newKey的原值被覆盖
     *
     * @param oldKey
     * @param newKey
     */
    public void renameKey(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * newKey不存在时才重命名
     *
     * @param oldKey
     * @param newKey
     * @return 修改成功返回true
     */
    public boolean renameKeyNotExist(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 删除key
     *
     * @param key
     */
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除多个key
     *
     * @param keys
     */
    public void deleteKey(String... keys) {
        Set<String> kSet = Stream.of(keys).map(k -> k).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }

    /**
     * 删除Key的集合
     *
     * @param keys
     */
    public void deleteKey(Collection<String> keys) {
        Set<String> kSet = keys.stream().map(k -> k).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }

    /**
     * 设置key的生命周期
     *
     * @param key
     * @param time
     * @param timeUnit
     */
    public void expireKey(String key, long time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 指定key在指定的日期过期
     *
     * @param key
     * @param date
     */
    public void expireKeyAt(String key, Date date) {
        redisTemplate.expireAt(key, date);
    }

    /**
     * 查询key的生命周期
     *
     * @param key
     * @param timeUnit
     * @return
     */
    public long getKeyExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    /**
     * 将key设置为永久有效
     *
     * @param key
     */
    public void persistKey(String key) {
        redisTemplate.persist(key);
    }


    /**
     * 简单的分布式锁实现
     * try lock
     * @author piaoruiqing
     * 链接：https://juejin.im/post/5ce5638ef265da1b91636a56
     * @param key       lock key
     * @param value     value
     * @param timeout   timeout
     * @param unit  	time unit
     * @return
     */
    public Boolean tryLock(String key, String value, long timeout, TimeUnit unit) {

        // 对应的redis的执行命令是：set dlock:test-try-lock a EX 10 NX
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeout, unit);
    }

    // 加锁方法实现, 满足自动解锁和重试
    public DistributedLock acquire(String key, long timeout, int retries, long waitingTime) throws InterruptedException {
        //锁值要保证唯一, 使用4位随机字符串+时间戳基本可满足需求
        //注: UUID.randomUUID()在高并发情况下性能不佳.
        final String value = RandomStringUtils.randomAlphanumeric(4) + System.currentTimeMillis(); // (一)
        do {
            //尝试加锁
            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(key, value, timeout, TimeUnit.MILLISECONDS); // (二)
            if (result) {
                return new RedisDistributedLock(stringRedisTemplate, key, value);
            }
            if (retries > NumberUtils.INTEGER_ZERO) {
                TimeUnit.MILLISECONDS.sleep(waitingTime);
            }
            if(Thread.currentThread().isInterrupted()){
                break;
            }
        } while (retries-- > NumberUtils.INTEGER_ZERO);

        return null;
    }
}
