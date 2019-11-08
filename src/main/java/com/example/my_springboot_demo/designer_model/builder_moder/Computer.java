package com.example.my_springboot_demo.designer_model.builder_moder;

/**
 * @Author: cdc
 * @Date: 2019/10/31 17:32
 * @Description: 建造者模式
 * 这是个产品类
 * 原文链接：https://juejin.im/post/5a23bdd36fb9a045272568a6#heading-2
 */


public class Computer {

    private String cpu ; // cpu
    private String hardDisk ; //硬盘
    private String mainBoard ; // 主板
    private String memory ; // 内存
    private String powerSupplier ; // 电源
    private String graphicsCard;   // 显卡

    // 其它一些可选配置
    private String mouse ; // 鼠标
    private String computerCase ; //机箱
    private String mousePad ;   //鼠标垫
    private String other ;  //其它配件

    public Computer() {
    }

    public Computer(String mainBoard, String cpu, String hd, String powerSupplier,
                    String graphicsCard, String mouse, String computerCase, String mousePad, String other){
        this.mainBoard = mainBoard ;
        this.cpu = cpu ;
        this.hardDisk = hd ;
        this.powerSupplier = powerSupplier ;
        this.graphicsCard = graphicsCard ;
        this.mouse = mouse ;
        this.computerCase = computerCase ;
        this.mousePad = mousePad ;
        this.other = other ;
    }

    public Computer(String mainBoard,String cpu,String hd,String powerSupplier,
                    String graphicsCard,String mouse,String computerCase,String mousePad){
        this.mainBoard = mainBoard ;
        this.cpu = cpu ;
        this.hardDisk = hd ;
        this.powerSupplier = powerSupplier ;
        this.graphicsCard = graphicsCard ;
        this.mouse = mouse ;
        this.computerCase = computerCase ;
        this.mousePad = mousePad ;
    }


    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(String hardDisk) {
        this.hardDisk = hardDisk;
    }

    public String getMainBoard() {
        return mainBoard;
    }

    public void setMainBoard(String mainBoard) {
        this.mainBoard = mainBoard;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getMouse() {
        return mouse;
    }

    public void setMouse(String mouse) {
        this.mouse = mouse;
    }

    public String getComputerCase() {
        return computerCase;
    }

    public void setComputerCase(String computerCase) {
        this.computerCase = computerCase;
    }

    public String getMousePad() {
        return mousePad;
    }

    public void setMousePad(String mousePad) {
        this.mousePad = mousePad;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getPowerSupplier() {
        return powerSupplier;
    }

    public void setPowerSupplier(String powerSupplier) {
        this.powerSupplier = powerSupplier;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }


}
