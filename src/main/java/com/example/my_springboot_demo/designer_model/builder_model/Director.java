package com.example.my_springboot_demo.designer_model.builder_model;

/**
 * @Author: cdc
 * @Date: 2019/10/31 17:42
 * @Description: 声明一个导演类「指挥者，这里可以装电脑的老板」，用来指挥组装过程，也就是组装电脑的流程
 */
public class Director {

    private Builder builder;

    // 使用多态，装机工非常多，我管你小美，小兰，小猪，我统统收了
    public Director(Builder builder) {
        this.builder = builder;
    }

    public Computer createComputer(String cpu, String hardDisk, String mainBoard, String memory) {
        //具体的工作是装机工去做
        this.builder.createMainBoard(mainBoard);
        this.builder.createCpu(cpu);
        this.builder.createMemory(memory);
        this.builder.createhardDisk(hardDisk);
        return this.builder.createComputer();
    }
}
