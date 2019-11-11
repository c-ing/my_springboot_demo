package com.example.my_springboot_demo.lock_demo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: cdc
 * @Date: 2019/11/7 11:05
 */
public class Test implements Runnable {

    ReentrantLock lock = new ReentrantLock();

    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        set();
        lock.unlock();
    }

    public void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        Test ss = new Test();
        Test ss1 = new Test();
        new Thread(ss).start();
        new Thread(ss1).start();
        new Thread(ss).start();
        new Thread(ss1).start();
        new Thread(ss).start();
        new Thread(ss1).start();
    }
}
