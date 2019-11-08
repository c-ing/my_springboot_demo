package com.example.my_springboot_demo.designer_model.builder_model;

/**
 * @Author: cdc
 * @Date: 2019/10/31 17:48
 */
public class Test {

    public static void main(String[] args) {
        // 装机员小美
        Builder builder = new AssemblerBuilder();
        // 老板把小明的需求转给小美
        Director director = new Director(builder);
        // 老板最后拿到成品机子，工作全由小美去做
        Computer computer = director.createComputer("Intel 酷睿i9 7900X","三星M9T 2TB （HN-M201RAD）","技嘉AORUS Z270X-Gaming 7","科赋Cras II 红灯 16GB DDR4 3000") ;
        System.out.println("小明这台电脑使用的是：\n"+computer.getMainBoard()+" 主板\n"+computer.getCpu()+" CPU\n"+computer.getHardDisk()+"硬盘\n"+computer.getMainBoard()+" 内存\n");

        // computerB
        // 不使用建造者模式
        Computer computerb = new Computer("主板","cpu","hd","电源","显卡"
                ,"鼠标","机箱","鼠标垫") ;
        System.out.println("使用普通的构造方法组装电脑："+computer.toString());

        // 使用建造者模式
        ComputerB computerB = new ComputerB.ComputerBuilder("主板","cpu","hd","电源","显卡").setMouse("鼠标").build() ;
        System.out.println("使用建造者模式组装电脑："+computerB.toString());

    }
}
