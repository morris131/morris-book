package com.morris.java8.lamdba;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReferenceExample {

    public static void main(String[] args) {

        // 指向静态方法的方法引用
        Function<String, Integer> function1 = Integer::parseInt; // 等价于下面
        Function<String, Integer> function2 = (String i) -> Integer.parseInt(i);

        // 指向任意类型实例方法的方法引用
        Function<String, String> function3 = String::toLowerCase; // 等价于下面
        Function<String, String> function4 = (String i) -> i.toLowerCase();

        BiFunction<String, Integer, String> biFunction = (String s, Integer i) -> s.substring(i);
        BiFunction<String, Integer, String> biFunction2 = String::substring;

        // 指向现有对象的实例方法的方法引用
        String str = "hello";
        Supplier<Integer> supplier = () -> str.length();
        Supplier<Integer> supplier2 = str::length;

        Function<Integer, String> function5 = (Integer i) -> str.substring(i);
        Function<Integer, String> function6 = str::substring;

        // 构造函数引用
        Supplier<String> stringSupplier = () -> new String();
        Supplier<String> stringSupplier2 = String::new;

        Function<String, String> stringFunction = (String s)->new String(s);
        Function<String, String> stringFunction2 = String::new;

        BiFunction<Runnable, String, Thread> stringBiFunction = (Runnable r, String b)-> new Thread(r, b);
        BiFunction<Runnable, String, Thread> stringBiFunction2 = Thread::new;

        ThreeFunction<ThreadGroup, Runnable, String, Thread> threeFunction = (ThreadGroup g, Runnable r, String b)-> new Thread(g, r, b);
        ThreeFunction<ThreadGroup, Runnable, String, Thread> threeFunction2 = Thread::new;

    }

    interface ThreeFunction<A, B, C, D> {
        D triple(A a, B b, C c);
    }

}
