package com.example.my_springboot_demo.designer_model.factory_model;

/**
 * @Author: cdc
 * @Date: 2019/11/8 10:42
 * @Descriptiion: 为了让子类继承坐标，这里我们使用抽象类来定义敌人。
 * 具体子类，我们这里假设只有两种，敌机类和坦克类。
 */
public abstract class Enemy {

    //敌人的坐标，会被子类继承
    protected int x;
    protected int y;

    //初始化坐标

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //抽象方法，在地图上绘制
    public abstract void show();
}
