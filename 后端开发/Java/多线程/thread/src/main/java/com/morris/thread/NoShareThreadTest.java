package com.morris.thread;

public class NoShareThreadTest {

	public static void main(String[] args) {
		NoShareCountThread a = new NoShareCountThread("A");
		NoShareCountThread b = new NoShareCountThread("B");
		NoShareCountThread c = new NoShareCountThread("C");
		a.start();
		b.start();
		c.start();
	}

}
