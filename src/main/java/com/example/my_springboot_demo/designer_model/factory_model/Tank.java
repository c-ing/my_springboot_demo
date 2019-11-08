package com.example.my_springboot_demo.designer_model.factory_model;

/**
 * @Author: cdc
 * @Date: 2019/11/8 10:47
 */
public class Tank extends Enemy {

    public Tank(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("坦克出现坐标：" + x + "," + y);
        System.out.println("坦克向玩家发起攻击......");
    }
}
