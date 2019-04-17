package com.morris.ch2;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
	
	public AtomicInteger inc = new AtomicInteger(0);

    public void increase() {
    	inc.incrementAndGet();
    }

    public static void main(String[] args) {

        final AtomicIntegerTest test = new AtomicIntegerTest();

        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };

            }.start();
        }
         
        while(Thread.activeCount()>1) { //保证前面的线程都执行完
        	Thread.yield();
        }
        System.out.println(test.inc);

    }
}
