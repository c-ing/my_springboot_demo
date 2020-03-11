package com.example.my_springboot_demo.block_queue_demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



/**
 * @Author: cdc
 * @Date: 2019/12/24 13:56
 */
public class BlockQueueWithLock<E> implements Queue<E> {

    private E[] array;
    private int head;
    private int teail;

    private volatile int size;

    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public BlockQueueWithLock(int capacity) {
        array = (E[])new Object[capacity];
    }

    @Override
    public void put(E e) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            // 队列满，阻塞
            while (size == array.length) {
                System.out.println("====队列满，阻塞====");
                System.out.println("===ThreadId=="+ Thread.currentThread().getName());
                // 方法会释放锁，让当前线程等待，支持唤醒，支持中断。超时之后返回的，结果为false；超时之前返回的，结果为true
                notFull.await(5, TimeUnit.SECONDS);
            }
            array[teail] = e;
            if (++teail == array.length) {
                teail = 0;
            }
            ++size;
            notEmpty.signal(); // 会唤醒一个等待中的线程，然后被唤醒的线程会被加入同步队列，去尝试获取锁
        }finally {
            System.out.println("====释放锁====");
            System.out.println("===ThreadId=="+ Thread.currentThread().getName());
            lock.unlock();
        }
    }

    @Override
    public E take() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            // 队列空，阻塞
            while (isEmpty()) {
                notEmpty.await();
            }
            E element = array[head];
            if (++head == array.length) {
                head = 0;
            }
            --size;
            //通知isFull条件队列有元素出去
            notFull.signal();
            return element;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        lock.lock();
        try {
            return size;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        lock.lock();
        try {
            return size == 0;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new BlockQueueWithLock<>(2);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 1; i <=10;i++) {
            final int finalNum = 1;
            executorService.execute(()->{
                try {
                    queue.put(finalNum);
                   // Integer take = queue.take();
                  //  System.out.println("item: " + take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            Thread.sleep(10000);
            System.out.println("begin take");
            Integer take = queue.take();
            System.out.println("item: " + take);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

     //   executorService.notifyAll();

        executorService.shutdown();
    }
}
