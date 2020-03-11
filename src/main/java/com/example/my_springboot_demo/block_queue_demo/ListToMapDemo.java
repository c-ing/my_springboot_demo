package com.example.my_springboot_demo.block_queue_demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: cdc
 * @Date: 2019/12/25 10:17
 */
public class ListToMapDemo {

    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public static void main(String[] args) {

        ListToMapDemo listToMapDemo1 = new ListToMapDemo();
        ListToMapDemo listToMapDemo2 = new ListToMapDemo();
        ListToMapDemo listToMapDemo3 = new ListToMapDemo();
        ListToMapDemo listToMapDemo4 = new ListToMapDemo();
        ListToMapDemo listToMapDemo5 = new ListToMapDemo();

        listToMapDemo1.setName("a");
        listToMapDemo1.setValue(3);
        listToMapDemo2.setName("b");
        listToMapDemo2.setValue(4);
        listToMapDemo3.setName("a");
        listToMapDemo3.setValue(2);
        listToMapDemo4.setName("c");
        listToMapDemo4.setValue(7);
        listToMapDemo5.setName("c");
        listToMapDemo5.setValue(6);

        List<ListToMapDemo> list = new ArrayList<>();
        list.add(listToMapDemo1);
        list.add(listToMapDemo2);
        list.add(listToMapDemo3);
        list.add(listToMapDemo4);
        list.add(listToMapDemo5);

        Map<String, List<ListToMapDemo>> batchMap = list.stream().collect(Collectors.groupingBy(t->t.getName()));
        System.out.println(batchMap);

        list.stream().sorted();
        System.out.println(list);
    }
}
