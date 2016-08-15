### 常见内存溢出（OOM）
#### java堆溢出
Java堆用于存储对象实例，我们只要不断的创建对象，并且保证GC Roots到对象之间有可达路径来避免垃圾回收机制清除这些对象，就会在对象数量达到最大堆容量限制后产生内存溢出异常。
[HeapOOM.java](jvm/src/main/java/com/morris/jvm)
```java
package com.morris.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * -Xms20m：设置Java堆的最小值为20m
 * -Xmx20m:设置Java堆的最大值为20m,不可扩展
 * -XX:+HeapDumpOnOutOfMemoryError：设置内存溢出时Dump内存快照
 * @author Morris
 *
 */
public class HeapOOM {
	
	public static void main(String[] args) {
		List<HeapOOM> list = new ArrayList<HeapOOM>();
		
		while(true) {
			list.add(new HeapOOM());
		}
	}

}
```
```java
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
```
出现这种异常，一般手段是先通过内存映像分析工具(如Eclipse Memory Analyzer)对dump出来的堆转存快照进行分析，重点是确认内存中的对象是否是必要的，先分清是因为内存泄漏(Memory Leak)还是内存溢出(Memory Overflow)。

如果是内存泄漏，可进一步通过工具查看泄漏对象到GC Roots的引用链。于是就能找到泄漏对象时通过怎样的路径与GC Roots相关联并导致垃圾收集器无法自动回收。

如果不存在泄漏，那就应该检查虚拟机的参数(-Xmx与-Xms)的设置是否适当。`

#### 虚拟机栈和本地方法栈溢出
##### StackOverflowError异常
如果线程请求的栈深度大于虚拟机所允许的最大深度，将抛出StackOverflowError异常。

```java
package com.morris.jvm;

/**
 * VM args: -Xss128k
 * @author Morris
 *
 */
public class StackSOF {
	
	public static void main(String[] args) {
		hello();
	}

	private static void hello() {
		while (true) {
			hello();
		}
	}

}
```
```java
Exception in thread "main" java.lang.StackOverflowError
```

#### 方法区溢出

#### 直接内存溢出