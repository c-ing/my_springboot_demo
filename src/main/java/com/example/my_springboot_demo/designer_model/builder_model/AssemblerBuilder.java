package com.example.my_springboot_demo.designer_model.builder_model;

/**
 * @Author: cdc
 * @Date: 2019/10/31 17:40
 */
public class AssemblerBuilder implements Builder {

    private Computer computer = new Computer();

    @Override
    public void createMainBoard(String mainBoard) {
        computer.setMainBoard(mainBoard);
    }

    @Override
    public void createCpu(String cpu) {
        computer.setCpu(cpu);
    }

    @Override
    public void createhardDisk(String hardDisk) {
        computer.setHardDisk(hardDisk);
    }

    @Override
    public void createMemory(String memory) {
        computer.setMemory(memory);
    }

    @Override
    public Computer createComputer() {
        return computer;
    }
}
