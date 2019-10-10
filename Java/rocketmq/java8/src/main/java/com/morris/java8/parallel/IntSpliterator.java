package com.morris.java8.parallel;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class IntSpliterator implements Spliterator<Integer> {

    private int start;

    private int end;

    private int current;

    private int[] data;

    public Stream<Integer> stream() {
       return StreamSupport.stream(this, false);
    }

    public Stream<Integer> parallelStream() {
        return StreamSupport.stream(this, true);
    }

    public IntSpliterator(int[] data) {
        this(0, data.length, data);
    }

    public IntSpliterator(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.current = start;
        this.data = data;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Integer> action) {

        if(current < end) {
            action.accept(data[current++]);
            return true;
        }
        return false;
    }

    @Override
    public Spliterator<Integer> trySplit() {

        if(start - end <= 2) {
            return null;
        }

        int mid=start+((end-start)/2);
        int newStart=mid;
        int newEnd=end;
        end=mid;
        return new IntSpliterator(newStart, newEnd, data);

    }

    @Override
    public long estimateSize() {
        return data.length - end;
    }

    @Override
    public int characteristics() {
        return Spliterator.ORDERED|Spliterator.SUBSIZED|Spliterator.SIZED;
    }

    public static void main(String[] args) {
        int[] ints = IntStream.rangeClosed(1, 10).toArray();
        IntSpliterator intSpliterator = new IntSpliterator(ints);

        intSpliterator.stream().forEach(System.out::println);
        //intSpliterator.parallelStream().forEach(System.out::print);

    }


}
