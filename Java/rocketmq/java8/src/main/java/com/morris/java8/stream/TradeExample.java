package com.morris.java8.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TradeExample {

    public static void main(String[] args) {
        Trader tom = new Trader("tom", "beijing");
        Trader morris = new Trader("morris", "shenzhen");
        Trader bob = new Trader("bob", "beijing");
        Trader peter = new Trader("peter", "beijing");

        List<Transaction> transactionList = Arrays.asList(
                new Transaction(tom, 2011, 300),
                new Transaction(morris, 2011, 500),
                new Transaction(morris, 2012, 1000),
                new Transaction(bob, 2011, 700),
                new Transaction(peter, 2011, 500),
                new Transaction(peter, 2012, 800)
        );

        // 查询2011所有的交易，并按金额排序
        transactionList.stream().filter(t -> 2011 == t.getYear()).sorted(Comparator.comparing(Transaction::getValue)).forEach(System.out::println);

        System.out.println("----------");

        // 交易员都在哪些不同的城市工作过
        transactionList.stream().map(t -> t.getTrader().getCity()).distinct().forEach(System.out::println);

        System.out.println("----------");

        // 查找所有来自北京的交易员，并按名字排序
        transactionList.stream().map(t -> t.getTrader()).filter(t -> "beijing".equals(t.getCity())).sorted(Comparator.comparing(Trader::getName)).distinct().forEach(System.out::println);

        System.out.println("----------");

        // 返回所有交易员的姓名字符串，按字母顺序排序
        String reduce = transactionList.stream().map(t -> t.getTrader().getName()).distinct().sorted().reduce("", (a, b) -> a + b);
        System.out.println(reduce);

        System.out.println("----------");

        // 有没有交易员在深圳工作
        boolean b = transactionList.stream().anyMatch(t -> "shenzhen".equals(t.getTrader().getCity()));
        System.out.println(b);

        // 计算在北京的交易员的交易总额
        transactionList.stream().filter(t -> "beijing".equals(t.getTrader().getCity())).map(t -> t.getValue()).reduce(0, (x, y) -> x + y);

        // 查出最大交易额
        transactionList.stream().max(Comparator.comparing(Transaction::getValue)).ifPresent(System.out::println);

        // 查出最小交易额
        transactionList.stream().min(Comparator.comparing(Transaction::getValue)).ifPresent(System.out::println);


    }
}
