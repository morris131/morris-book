package com.morris.pattern.strategy.demo;

public class Client {

    public static void main(String[] args) {

        Context context = new Context(new AddStrategy());
        System.out.println("9 + 3 = " + context.calculate(9, 3));

        context.setStrategy(new SubtractStrategy());
        System.out.println("9 - 3 = " + context.calculate(9, 3));

        context.setStrategy(new MultiplyStrategy());
        System.out.println("9 * 3 = " + context.calculate(9, 3));

        context.setStrategy(new DivideStrategy());
        System.out.println("9 / 3 = " + context.calculate(9, 3));

    }
}
