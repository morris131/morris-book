package com.morris.pattern.facade;

public class Computer {

    private Cpu cpu = new Cpu();

    private Memory memory = new Memory();

    private Disk disk = new Disk();

    public void start() {
        System.out.println("Computer start begin");
        cpu.start();
        memory.start();
        disk.start();
        System.out.println("Computer start end");
    }

    public void shutdown() {
        System.out.println("Computer shutdown begin");
        cpu.shutdown();
        memory.shutdown();
        disk.shutdown();
        System.out.println("Computer shutdown end");
    }

}
