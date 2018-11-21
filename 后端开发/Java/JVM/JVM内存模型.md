---
title: JVM内存模型
date: 2018-10-04
categories: JVM
tags: [JVM]
---

# JVM内存模型

下图是jdk7 jvm内存模型图。

![内存模型图](https://github.com/morris131/morris-book/raw/master/%E5%90%8E%E7%AB%AF%E5%BC%80%E5%8F%91/Java/JVM/images/jvm%E5%86%85%E5%AD%98%E6%A8%A1%E5%9E%8B.png)

## 程序计数器
程序计数器是当前线程所执行的字节码的行号指示器。

JVM支持多个线程同时运行，每个线程都有自己的程序计数器。倘若当前执行的是 JVM 的方法，则该寄存器中保存当前执行指令的地址；倘若执行的是native 方法，则PC寄存器中为空。

此内存区域是唯一一个在Java虚拟机规范中没有规定任何OutOfMemoryError 情况的区域。


## 虚拟机栈

![虚拟机栈](https://github.com/morris131/morris-book/raw/master/%E5%90%8E%E7%AB%AF%E5%BC%80%E5%8F%91/Java/JVM/images/%E6%A0%88%E5%B8%A7.png)

每个线程有一个私有的栈，随着线程的创建而创建，每当线程调用一个java方法时,虚拟机都会在该线程的java栈中压入一个新的栈帧，栈帧用于存储局部变量表、操作栈、动态链接、方法出口等信息。每一个方法被调用直至执行完成的过程，就对应着一个栈帧在虚拟机栈中从入栈到出栈的过程。

局部变量表所需的内存空间在编译期间完成分配，当进入一个方法时，这个方法需要在帧中分配多大的局部变量空间是完全确定的，在方法运行期间不会改变局部变量表的大小。

虚拟机栈会有两种异常状况：
1. 如果线程请求的栈深度大于虚拟机所允许的深度，将抛出StackOverflowError异常
2. 当扩展时无法申请到足够的内存时会抛出OutOfMemoryError异常

## 本地方法栈
本地方法栈与虚拟机栈所发挥的作用是非常相似的，其区别不过是虚拟机栈为虚拟机执行Java 方法服务，而本地方法栈则是为虚拟机使用到的Native方法服务。与虚拟机栈一样，本地方法栈区域也会抛出StackOverflowError和OutOfMemoryError异常。

## Java堆
Java堆存放对象实例以及数组，可以根据虚拟机参数-Xmx和-Xms来控制堆的大小。如果在堆中没有内存完成实例分配，并且堆也无法再扩展时，将会抛出OutOfMemoryError异常。Java 堆中可以细分为：新生代和老年代；再细致一点的有Eden 空间、From Survivor 空间、To Survivor 空间。

## 方法区
方法区也称为永久代， 用于存储已被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。当方法区无法满足内存分配需求时，将抛出OutOfMemoryError 异常。

运行时常量池是方法区的一部分。Class文件中除了有类的版本、字段、方法、接口等描述等信息外，还有一项信息是常量池，用于存放编译期生成的各种字面量和符号引用，这部分内容将在类加载后存放到方法区的运行时常量池中。

## 直接内存
直接内存不是虚拟机运行时数据区的一部分，直接内存的分配不会受到JVM的限制，但是会受到物理内存的限制，内存不足时出现OutOfMemoryError。

在JDK1.4中新加入了NIO（New Input/Output）类，引入了一种基于通道（Channel）与缓冲区（Buffer）的I/O方式，它可以使用Native函数库直接分配堆外内存，然后通过一个存储在Java堆里面的DirectByteBuffer 对象作为这块内存的引用进行操作。这样能在一些场景中显著提高性能，因为避免了在Java堆和Native 堆中来回复制数据。

## 元空间
方法区和永久代有着本质的区别。前者是 JVM 的规范，而后者则是 JVM 规范的一种实现，并且只有HotSpot才有 “PermGen space”，而对于其他类型的虚拟机，如 JRockit（Oracle）、J9（IBM） 并没有“PermGen space”。

元空间的本质和永久代类似，都是对JVM规范中方法区的实现。不过元空间与永久代之间最大的区别在于：元空间并不在虚拟机中，而是使用本地内存。因此，默认情况下，元空间的大小仅受本地内存限制，但可以通过以下参数来指定元空间的大小：
- -XX:MetaspaceSize，初始空间大小，达到该值就会触发垃圾收集进行类型卸载，同时GC会对该值进行调整：如果释放了大量的空间，就适当降低该值；如果释放了很少的空间，那么在不超过MaxMetaspaceSize时，适当提高该值。
- -XX:MaxMetaspaceSize，最大空间，默认是没有限制的。

除了上面两个指定大小的选项以外，还有两个与 GC 相关的属性：
- -XX:MinMetaspaceFreeRatio，在GC之后，最小的Metaspace剩余空间容量的百分比，减少为分配空间所导致的垃圾收集
- -XX:MaxMetaspaceFreeRatio，在GC之后，最大的Metaspace剩余空间容量的百分比，减少为释放空间所导致的垃圾收集


