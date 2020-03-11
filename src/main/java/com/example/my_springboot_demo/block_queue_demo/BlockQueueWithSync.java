package com.example.my_springboot_demo.block_queue_demo;

/**
 * @Author: cdc
 * @Date: 2019/12/24 10:21
 *
 * 基于 synchronized 的实现:
 *      核心思路：
 *          添加元素时队列满则阻塞
 *          弹出元素时队列空则阻塞
 *          添加元素后唤醒消费者
 *          弹出元素后唤醒生产者
 */
public class BlockQueueWithSync<E> implements Queue<E> {

    private E[] array;
    private int head; //对头指针
    private int tail; // 队尾指针

    private volatile int size; // 队列元素个数

    public BlockQueueWithSync(int capacity) {
        array = (E[])new Object[capacity];
    }

    @Override
    public void put(E e) throws InterruptedException {
        // 当队列满的时候阻塞
        while (size == array.length) {
            this.wait();
        }

        array[tail] = e;
        // 队列装满后索引归零
        if (++tail == array.length) {
            tail = 0;
        }
        ++size;
        // 通知其他消费端有数据了
        this.notifyAll();
    }

    @Override
    public E take() throws InterruptedException {
        //当队列空的时候阻塞
        while (isEmpty()) {
            this.wait();
        }

        E element = array[head];
        // 消费完后从0开始
        if (++head == array.length) {
            head = 0;
        }
        --size;
        // 通知其他生产者可以生产了
        this.notifyAll();
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        Queue<Integer> blockQueueWithSync = new BlockQueueWithSync<>(3);
        try {
            blockQueueWithSync.put(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
