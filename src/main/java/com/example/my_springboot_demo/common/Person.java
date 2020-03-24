package com.example.my_springboot_demo.common;

/**
 * @Author: cdc
 * @Date: 2020/3/14 11:20
 */
public class Person {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name: " + name + ", age: " + age;
    }
}
