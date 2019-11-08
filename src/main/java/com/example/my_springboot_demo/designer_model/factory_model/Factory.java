package com.example.my_springboot_demo.designer_model.factory_model;

/**
 * @Author: cdc
 * @Date: 2019/11/8 11:09
 *
 * 这个工厂接口就是工厂方法的核心了，它具备这么一个功能（第3行），
 * 可以在屏宽之内来产出一个敌人，这就是我们抽象出来的工厂方法。
 * 然后我们来定义这个工厂方法的子类实现，随机工厂。
 */
public interface Factory {

    public Enemy create(int screenWidth);
}
