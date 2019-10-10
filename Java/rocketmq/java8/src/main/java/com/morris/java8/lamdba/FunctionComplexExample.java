package com.morris.java8.lamdba;

import java.util.function.Function;

public class FunctionComplexExample {

    public static void main(String[] args) {

        // andThen
        Function<Integer, Integer> f = x -> x + 1; // f(x)
        Function<Integer, Integer> g = x -> x * 2; // g(x)
        Function<Integer, Integer> andThen =  f.andThen(g); // g(f)=g(f(x))

        Integer apply = andThen.apply(1);
        System.out.println(apply);

        // compose
        Function<Integer, Integer> y = x -> x + 1; // y(x)
        Function<Integer, Integer> z = x -> x * 2; // z(x)
        Function<Integer, Integer> compose =  y.compose(z); // y(z)=y(z(x))

        Integer apply2 = compose.apply(1);
        System.out.println(apply2);

    }
}
