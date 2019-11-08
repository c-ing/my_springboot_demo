package com.example.my_springboot_demo.designer_model.factory_model;

import java.util.Random;

/**
 * @Author: cdc
 * @Date: 2019/11/8 10:49
 */
public class Client {

    public static void main(String[] args) {
        int screenWidth = 100; //屏幕宽度
        System.out.println("游戏开始");
        Random random = new Random();// 准备随机数

        int x = random.nextInt(screenWidth); //生成敌机横坐标随机数
        Enemy airplan = new Airplane(x, 0); //实例化飞机
        airplan.show();// 显示飞机

        x = random.nextInt(screenWidth); //坦克同上
        Enemy tank = new Tank(x, 0);
        tank.show();

        // 简单工厂, 并不是一种设计模式，只是对实例化逻辑进行了一层简单包裹而已
        // 我们应该对生产方式（工厂方法）进行抽象化
        System.out.println("简单工厂，游戏开始");
        SimpleFactory simpleFactory = new SimpleFactory(100);
        simpleFactory.create("Airplane").show();
        simpleFactory.create("Tank").show();


        //int screenWidth = 100;
        System.out.println("游戏开始");
        Factory factory = new RandomFactory();
        for (int i = 0; i < 10; i++) {
            factory.create(screenWidth).show();
        }
    }
}
