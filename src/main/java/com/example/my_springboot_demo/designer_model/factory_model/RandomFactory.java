package com.example.my_springboot_demo.designer_model.factory_model;

import java.util.Random;

/**
 * @Author: cdc
 * @Date: 2019/11/8 11:11
 */
public class RandomFactory implements Factory {

    private Random random = new Random();

    @Override
    public Enemy create(int screenWidth) {
        Enemy enemy = null;
        if(random.nextBoolean()){
            enemy = new Airplane(random.nextInt(screenWidth), 0);//实例化飞机
        }else{
            enemy = new Tank(random.nextInt(screenWidth), 0);//实例化坦克
        }
        return enemy;
    }
}
