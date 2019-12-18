package com.example.my_springboot_demo.lock_demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: cdc
 * @Date: 2019/12/13 14:40
 */
public class HeapTest {

    static class StaticObject {
    }

    public static void main(String[] args) {
        List<StaticObject> list = new ArrayList<StaticObject>();
        int i = 1;

        //不断的向堆中添加对象
        while (true) {
            list.add(new StaticObject());
            i++;
            System.out.println(i);
            System.out.println(list.size());
        }
    }
}
