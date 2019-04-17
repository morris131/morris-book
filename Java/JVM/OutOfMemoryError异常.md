---
title: OutOfMemoryError异常
date: 2018-10-07
categories: JVM
tags: [OOM]
---

# OutOfMemoryError异常

## 堆溢出
堆存放的是对象和数组，只要不断的创建对象或数组，堆就会溢出。

-Xmx：设置JVM最大堆内存。
-Xms：设置JVM初始堆内存。此值可以设置与-Xmx相同，以避免每次垃圾回收完成后JVM重新分配内存。
-Xmn: 设置年轻代的大小

[HeapOOM.class]()
```
package com.morris.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args: -Xms20m -Xmx20m -Xmn10m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/dump/ -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 */
public class HeapOOM {

    public static void main(String[] args) {

        List<byte[]> list = new ArrayList<byte[]>();

        int i = 0;

        boolean flag = true;

        while (flag) {

            try {
                i++;
                list.add(new byte[1024 * 1024]);//每次增加一个1M大小的数组对象
            } catch (Throwable e) {
                e.printStackTrace();
                flag = false;
                System.out.println("count=" + i);//记录运行的次数
            }

        }

    }

}
```
运行结果如下：

```
"D:\Program Files\Java\jdk1.8.0_172\bin\java.exe" -Xms20m -Xmx20m -Xmn10m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/dump/ -XX:+PrintGCDetails -XX:+PrintGCTimeStamps "-javaagent:D:\Program Files\JetBrains\IntelliJ IDEA 2018.1.4\lib\idea_rt.jar=55885:D:\Program Files\JetBrains\IntelliJ IDEA 2018.1.4\bin" -Dfile.encoding=UTF-8 -classpath "D:\Program Files\Java\jdk1.8.0_172\jre\lib\charsets.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\deploy.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\access-bridge-64.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\cldrdata.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\dnsns.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\jaccess.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\jfxrt.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\localedata.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\nashorn.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\sunec.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\sunjce_provider.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\sunmscapi.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\sunpkcs11.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\ext\zipfs.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\javaws.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\jce.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\jfr.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\jfxswt.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\jsse.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\management-agent.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\plugin.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\resources.jar;D:\Program Files\Java\jdk1.8.0_172\jre\lib\rt.jar;D:\gitPrj\morris-book\后端开发\Java\JVM\jvm\target\classes;C:\Users\wj65651\.m2\repository\cglib\cglib\2.2.2\cglib-2.2.2.jar;C:\Users\wj65651\.m2\repository\asm\asm\3.3.1\asm-3.3.1.jar;C:\Users\wj65651\.m2\repository\mysql\mysql-connector-java\5.1.47\mysql-connector-java-5.1.47.jar" com.morris.jvm.oom.HeapOOM
0.102: [GC (Allocation Failure) [PSYoungGen: 7311K->792K(9216K)] 7311K->5920K(19456K), 0.0043026 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
0.107: [Full GC (Ergonomics) [PSYoungGen: 792K->0K(9216K)] [ParOldGen: 5128K->5766K(10240K)] 5920K->5766K(19456K), [Metaspace: 3428K->3428K(1056768K)], 0.0061325 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
0.114: [Full GC (Ergonomics) [PSYoungGen: 7408K->3072K(9216K)] [ParOldGen: 5766K->9877K(10240K)] 13175K->12949K(19456K), [Metaspace: 3440K->3440K(1056768K)], 0.0121787 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
0.126: [Full GC (Ergonomics) [PSYoungGen: 7535K->7168K(9216K)] [ParOldGen: 9877K->9876K(10240K)] 17413K->17044K(19456K), [Metaspace: 3441K->3441K(1056768K)], 0.0060028 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
0.132: [Full GC (Allocation Failure) [PSYoungGen: 7168K->7168K(9216K)] [ParOldGen: 9876K->9858K(10240K)] 17044K->17026K(19456K), [Metaspace: 3441K->3441K(1056768K)], 0.0096972 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
java.lang.OutOfMemoryError: Java heap space
Dumping heap to /dump/\java_pid13060.hprof ...
java.lang.OutOfMemoryError: Java heap space
	at com.morris.jvm.oom.HeapOOM.main(HeapOOM.java:23)
Heap dump file created [18280772 bytes in 0.015 secs]
count=17
Heap
 PSYoungGen      total 9216K, used 7378K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
  eden space 8192K, 90% used [0x00000000ff600000,0x00000000ffd34ab0,0x00000000ffe00000)
  from space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
  to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
 ParOldGen       total 10240K, used 9858K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
  object space 10240K, 96% used [0x00000000fec00000,0x00000000ff5a08d8,0x00000000ff600000)
 Metaspace       used 3472K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 379K, capacity 388K, committed 512K, reserved 1048576K

```

## 虚拟机栈溢出
虚拟机栈这个区域会出现两种异常状况：
1. 线程请求的栈深度大于虚拟机所允许的深度，将抛出StackOverflowError 异常； 
2. 当虚拟机栈扩展时无法申请到足够的内存时会抛出OutOfMemoryError异常。（无法重现）

函数嵌套的层数很大程度上有栈帧的大小决定的。栈帧越大，函数调用的次数就越多。

-Xss：指定栈帧的大小

[StackSOE.java]()
```
package com.morris.jvm.oom;

public class StackSOE {

	private static int index = 1;
	
	private static void test() {
		index++;
		test();
	}
	
	public static void main(String[] args) {
		try {
			test();
		}catch (Throwable e){
			System.out.println("Stack deep : "+index);
			e.printStackTrace();

		}
	}

}
```
运行结果如下：

```
Stack deep : 22895
java.lang.StackOverflowError
	at com.morris.jvm.oom.StackSOE.test(StackSOE.java:9)
	... ...
```

## 本地方法栈溢出
不断的创建线程就会导致本地方法栈溢出。

[NativeMethodStackOOM.java]()
```
package com.morris.jvm.oom;

public class NativeMethodStackOOM {
	
	public static void main(String[] args) {
		
		while(true) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (true) {
					}
					
				}
			}).start();
		}
	}

}

```
此代码会出现假死，谨慎运行。

## 方法区溢出

-XX:PermSize：指定永久代初始内存
-XX:MaxPermSize：指定永久代最大内存

## 字符串常量池方法溢出

[ConstantPoolOOM.java]()
```
package com.morris.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args: -XX:PermSize=2m -XX:MaxPermSize=2m
 * jdk 7运行
 *
 */
public class ConstantPoolOOM {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		int i = 0;
		while (true) {
			list.add(String.valueOf(i++).intern());
		}
	}

}
```
运行结果如下：
```
Error occurred during initialization of VM
java.lang.OutOfMemoryError: PermGen space
	at sun.misc.Launcher$ExtClassLoader.getExtClassLoader(Launcher.java:141)
	at sun.misc.Launcher.<init>(Launcher.java:71)
	at sun.misc.Launcher.<clinit>(Launcher.java:57)
	at java.lang.ClassLoader.initSystemClassLoader(ClassLoader.java:1489)
	at java.lang.ClassLoader.getSystemClassLoader(ClassLoader.java:1474)
```

## 方法区内存溢出

```
package com.morris.jvm.oom;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;


/**
 * VM args： -XX:PermSize=2M -XX:MaxPermSize=2M 
 */
public class MethodAreaOOM {

	public static void main(String[] args) {
		URL url = null;

		List<ClassLoader> classLoaderList = new ArrayList<ClassLoader>();

		try {
			url = new File("/tmp").toURI().toURL();
			URL[] urls = {url};

			while (true){
				ClassLoader loader = new URLClassLoader(urls);
				classLoaderList.add(loader);
				loader.loadClass("com.morris.jvm.oom.MethodAreaOOM.OOMObject");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static class OOMObject {

	}
}
```
运行结果如下：

```
Error occurred during initialization of VM
java.lang.OutOfMemoryError: PermGen space
	at sun.misc.Launcher$ExtClassLoader.getExtClassLoader(Launcher.java:141)
	at sun.misc.Launcher.<init>(Launcher.java:71)
	at sun.misc.Launcher.<clinit>(Launcher.java:57)
	at java.lang.ClassLoader.initSystemClassLoader(ClassLoader.java:1489)
	at java.lang.ClassLoader.getSystemClassLoader(ClassLoader.java:1474)

```

## 直接内存溢出

```
package com.morris.jvm.oom;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * VM args:-Xmx20M -XX:MaxDirectMemorySize=10M
 *
 */
@SuppressWarnings("restriction")
public class DirectMemoryOOM {
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Field field =Unsafe.class.getDeclaredFields()[0];
		field.setAccessible(true);
		
		Unsafe unsafe = (Unsafe) field.get(null);
		
		while(true) {
			unsafe.allocateMemory(1024*1024);
		}
	}
}

```
运行结果如下：

```
Exception in thread "main" java.lang.OutOfMemoryError
	at sun.misc.Unsafe.allocateMemory(Native Method)
	at com.morris.jvm.oom.DirectMemoryOOM.main(DirectMemoryOOM.java:20)

```
