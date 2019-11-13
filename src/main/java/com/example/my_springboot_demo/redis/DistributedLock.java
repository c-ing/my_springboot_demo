package com.example.my_springboot_demo.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: cdc
 * @Date: 2019/11/12 11:01
 * Distributed是分布式锁抽象类: 实现AutoCloseable接口, 可使用try-with-resource方便地完成自动解锁.
 */
abstract public class DistributedLock implements AutoCloseable {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    abstract public void release();

    @Override
    public void close() throws Exception {
        logger.debug("distributedLock lock close,{}",this.toString());
        this.release();
    }
}
