package com.example.my_springboot_demo.designer_model.factory_model;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Random;

/**
 * @Author: cdc
 * @Date: 2019/11/8 10:57
 */
public class SimpleFactory {

    private int screenWidth;
    private Random random; //随机数

    public SimpleFactory(int screenWidth) {
        this.screenWidth = screenWidth;
        this.random = new Random();
    }

    public Enemy create(String type) {
        int x = random.nextInt(screenWidth);//生成敌人坐标随机数
        Enemy enemy = null;
        switch (type) {
            case "Airplane":
                enemy = new Airplane(x, 0);//实例化飞机
                break;
            case "Tank":
                enemy = new Tank(x, 0); // 实例化坦克
                break;
        }
        return enemy;
    }
}
