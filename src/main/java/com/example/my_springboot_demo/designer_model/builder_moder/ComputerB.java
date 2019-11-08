package com.example.my_springboot_demo.designer_model.builder_moder;

/**
 * @Author: cdc
 * @Date: 2019/10/31 17:57
 */
public class ComputerB {

    private String mainBoard ;     // 主板
    private String cpu ;           // cpu
    private String hd ;            // 硬盘
    private String powerSupplier ; // 电源
    private String graphicsCard;   // 显卡

    // 其它一些可选配置
    private String mouse ; // 鼠标
    private String computerCase ; //机箱
    private String mousePad ;   //鼠标垫
    private String other ;  //其它配件

    // ComputerB 自己充当 Director
    private ComputerB(ComputerBuilder builder) {
        this.mainBoard = builder.mainBoard ;
        this.cpu = builder.cpu ;
        this.hd = builder.hd ;
        this.powerSupplier = builder.powerSupplier ;
        this.graphicsCard = builder.graphicsCard ;

        this.mouse = builder.mouse ;
        this.computerCase = builder.computerCase ;
        this.mousePad = builder.mousePad ;
        this.other = builder.other ;
    }


    // 声明一个静态内存类 Builder
    public static class ComputerBuilder{
        // 一个电脑的必须配置
        private String mainBoard ;     // 主板
        private String cpu ;           // cpu
        private String hd ;            // 硬盘
        private String powerSupplier ; // 电源
        private String graphicsCard;   // 显卡

        // 其它一些可选配置
        private String mouse ; // 鼠标
        private String computerCase ; //机箱
        private String mousePad ;   //鼠标垫
        private String other ;  //其它配件

        // 这里声明一些必须要传的参数「规定这些参数是必须传的，这里只是举例，再实中可能参数都是可选的」
        public ComputerBuilder(String mainBoard,String cpu,String hd,String powerSupplier,String graphicsCard/*,String mousePad,String mouse*/){
            this.mainBoard = mainBoard ;
            this.cpu = cpu ;
            this.hd = hd ;
            this.powerSupplier = powerSupplier ;
            this.graphicsCard = graphicsCard ;
          /*  this.mousePad = mousePad;
            this.mouse = mouse;*/
        }

       /* public ComputerBuilder setMainBoard(String mainBoard) {
            this.mainBoard = mainBoard;
            return this ;
        }
*/
        public ComputerBuilder setCpu(String cpu) {
            this.cpu = cpu;
            return this ;
        }

        public ComputerBuilder setHd(String hd) {
            this.hd = hd;
            return this;
        }

        public ComputerBuilder setMouse(String mouse) {
            this.mouse = mouse;
            return this;
        }

        public ComputerBuilder setMousePad(String mousePad) {
            this.mousePad = mousePad;
            return this;
        }

        // 生成最终的产品
        public ComputerB build(){
            return new ComputerB(this) ;
        }
    }



    public String getMainBoard() {
        return mainBoard;
    }

    public void setMainBoard(String mainBoard) {
        this.mainBoard = mainBoard;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getHd() {
        return hd;
    }

    public void setHd(String hd) {
        this.hd = hd;
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

    @Override
    public String toString() {
        return this.mainBoard + " "+this.cpu + " " + this.computerCase + " " + this.graphicsCard
        +" " + this.hd + " " + this.mouse + " " + this.mousePad + " " ;
    }
}
