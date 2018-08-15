## ThreadLocal的使用
ThreadLocal，即线程变量，是一个以ThreadLocal对象为键、任意对象为值的存储结构。这个结构被附带在线程上，也就是说一个线程可以根据一个ThreadLocal对象查询到绑定在这个线程上的一个值。可以通过set(T)方法来设置一个值，在当前线程下再通过get()方法获取到原先设置的值。

### 简单使用

```java
package com.morris.ch3;

import java.util.Random;

public class SimpleThreadLocalTest {
	
	static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
	
	public static void main(String[] args) {
		new A().start();
		new A().start();
		new A().start();
	}
	
	static class A extends Thread {
		@Override
		public void run() {
			for(int i = 0; i < 10; i++) {
				long l = new Random().nextLong();
				System.out.println(Thread.currentThread().getName() +" set: " + l);
				threadLocal.set(l);
				System.out.println(Thread.currentThread().getName() +" get: " + threadLocal.get());
			}
		}
	}	
}

```

### 解决SimpleDateFormat非线程安全
```java
package com.morris.ch3;

import java.text.SimpleDateFormat;

public class DateUtil {
	
	private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();
	
	public static SimpleDateFormat getDateFormat(String pattern) {
		SimpleDateFormat sdf = threadLocal.get();
		if(null == sdf) {
			sdf = new SimpleDateFormat(pattern);
			threadLocal.set(sdf);
		}
		return sdf;
	}

}

```