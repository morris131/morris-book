package com.morris.pattern.facade;

public class Client {

    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.start();
        System.out.println("--------");
        computer.shutdown();
    }

}
