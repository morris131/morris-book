## OutOfMemoryError异常

### 堆溢出
堆存放的是对象，只要不断的创建对象，堆就会溢出。

```
package com.morris.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args: -Xms 20m -Xmx 20m
 *
 */
public class HeapOOM {
	
	public static void main(String[] args) {
		List<Object> list = new ArrayList<>();
		while(true) {
			list.add(new Object());
		}
	}

}

```
运行时限制堆的最大值(-Xmx)和最小值(-Xms)。
运行结果如下：

```
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Arrays.java:3210)
	at java.util.Arrays.copyOf(Arrays.java:3181)
	at java.util.ArrayList.grow(ArrayList.java:265)
	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:239)
	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:231)
	at java.util.ArrayList.add(ArrayList.java:462)
	at com.morris.jvm.oom.HeapOOM.main(HeapOOM.java:15)

```

### 虚拟机栈溢出
虚拟机栈这个区域会出现两种异常状况：
1. 线程请求的栈深度大于虚拟机所允许的深度，将抛出StackOverflowError 异常； 
2. 当虚拟机栈扩展时无法申请到足够的内存时会抛出OutOfMemoryError异常。（无法重现）

```
package com.morris.jvm.oom;

public class StackSOE {
	
	private static void test() {
		test();
	}
	
	public static void main(String[] args) {
		test();
	}

}

```
运行结果如下：

```
Exception in thread "main" java.lang.StackOverflowError
	at com.morris.jvm.oom.StackSOE.test(StackSOE.java:6)
	... ...
```

### 本地方法栈溢出
不断的创建线程就会导致本地方法栈溢出。

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

### 方法区溢出
字符串常量池方法溢出。
```
package com.morris.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args: -XX:PermSize=2m -XX:MaxPermSize=2m
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
借助CGLib不停的往方法区插入类使方法区出现内存溢出异常。

```
package com.morris.jvm.oom;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * VM args： -XX:PermSize=2M -XX:MaxPermSize=2M 
 */
public class MethodAreaOOM {

	public static void main(String[] args) {
		while (true) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {
				@Override
				public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
					return proxy.invokeSuper(obj, args);
				}
			});
			enhancer.create();
		}
	}

	static class OOMObject {

	}
}

```
运行结果如下：

```
Error occurred during initialization of VM
java.lang.OutOfMemoryError: PermGen space
	at java.io.InputStreamReader.<init>(InputStreamReader.java:74)
	at java.io.FileReader.<init>(FileReader.java:72)
	at sun.misc.MetaIndex.registerDirectory(MetaIndex.java:166)
	at sun.misc.Launcher$ExtClassLoader$1.run(Launcher.java:146)
	at sun.misc.Launcher$ExtClassLoader$1.run(Launcher.java:142)
	at java.security.AccessController.doPrivileged(Native Method)
	at sun.misc.Launcher$ExtClassLoader.getExtClassLoader(Launcher.java:141)
	at sun.misc.Launcher.<init>(Launcher.java:71)
	at sun.misc.Launcher.<clinit>(Launcher.java:57)
	at java.lang.ClassLoader.initSystemClassLoader(ClassLoader.java:1489)
	at java.lang.ClassLoader.getSystemClassLoader(ClassLoader.java:1474)


```
### 直接内存溢出

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
