package com.example.my_springboot_demo.block_queue_demo;

/**
 * @Author: cdc
 * @Date: 2019/12/24 10:17
 *
 * 我们通过定义一个 Queue 接口来实现两种队列，该队列是有界队列，使用数组的方式实现，
 * 如果你有兴趣也可以使用链表或栈来实现这个队列。
 * 提供：
 *      put 方法添加元素（满了则阻塞），
 *      take 方法弹出元素（没有元素则阻塞）。
 */
public interface Queue<E> {

    // 添加新元素，当队列满则阻塞
    void put(E e) throws InterruptedException;

    // 弹出队列头元素，当队列空则阻塞
    E take() throws InterruptedException;

    // 队列元素个数
    int size();

    // 队列是否为空
    boolean isEmpty();
}
