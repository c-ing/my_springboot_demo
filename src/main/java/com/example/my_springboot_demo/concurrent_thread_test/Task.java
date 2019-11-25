package com.example.my_springboot_demo.concurrent_thread_test;


import java.util.Date;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: cdc
 * @Date: 2019/11/13 11:59
 */
public class Task implements Runnable {

    private CyclicBarrier cyclicBarrier;
    public static final Integer LOCK_TIME = 3;//加锁等待时间3秒

    public Task(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {

        Lock lock = null;
        try {
            // 等待所有任务准备就绪
            cyclicBarrier.await();
             lock = LockUtil.getLock("test");
            // 测试内容
            if(lock.tryLock(LOCK_TIME, TimeUnit.SECONDS)){
                System.out.println("ssss " + new Date());
                System.out.println(Thread.currentThread());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
            //System.out.println(String.format("有%s个线程在保持锁",Integer.toString(getHoldCount(lock))));
            System.out.println(String.format("有%s个线程在等待锁",Integer.toString(getQueueLength(lock))));

        }
    }

    public int getQueueLength(Lock lock) {
        ReentrantLock reentrantLock = (ReentrantLock)lock;
        return reentrantLock.getQueueLength();
    }

    public int getHoldCount(Lock lock) {
        ReentrantLock reentrantLock = (ReentrantLock)lock;
        return reentrantLock.getHoldCount();
    }

    public static void main(String[] args) {
        int count = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++)
            executorService.execute(new Task(cyclicBarrier));

        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

