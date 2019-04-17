## volatile的使用

### volatile可见性
```java

//线程1

boolean stop = false;

while(!stop){

    doSomething();

}

 

//线程2

stop = true;
```
在大多数时候，这个代码能够把线程中断，但是也有可能会导致无法中断线程（虽然这个可能性很小，但是只要一旦发生这种情况就会造成死循环了）。

每个线程在运行过程中都有自己的工作内存，那么线程1在运行的时候，会将stop变量的值拷贝一份放在自己的工作内存当中。当线程2更改了stop变量的值之后，但是还没来得及写入主存当中，线程2转去做其他事情了，那么线程1由于不知道线程2对stop变量的更改，因此还会一直循环下去。

但是用volatile修饰之后就变得不一样了：

　　第一：使用volatile关键字会强制将修改的值立即写入主存；

　　第二：使用volatile关键字的话，当线程2进行修改时，会导致线程1的工作内存中缓存变量stop的缓存行无效（反映到硬件层的话，就是CPU的L1或者L2缓存中对应的缓存行无效）；

　　第三：由于线程1的工作内存中缓存变量stop的缓存行无效，所以线程1再次读取变量stop的值时会去主存读取。

　　那么在线程2修改stop值时（当然这里包括2个操作，修改线程2工作内存中的值，然后将修改后的值写入内存），会使得线程1的工作内存中缓存变量stop的缓存行无效，然后线程1读取时，发现自己的缓存行无效，它会等待缓存行对应的主存地址被更新之后，然后去对应的主存读取最新的值。那么线程1读取到的就是最新的正确的值。

### volatile有序性

volatile能在一定程度上保证有序性。

volatile关键字禁止指令重排序有两层意思：

1. 当程序执行到volatile变量的读操作或者写操作时，在其前面的操作的更改肯定全部已经进行，且结果已经对后面的操作可见；在其后面的操作肯定还没有进行；
2. 在进行指令优化时，不能将在对volatile变量访问的语句放在其后面执行，也不能把volatile变量后面的语句放到其前面执行。

```java
//x、y为非volatile变量
//flag为volatile变量
x = 2;        //语句1
y = 0;        //语句2
flag = true;  //语句3
x = 4;         //语句4
y = -1;       //语句5 
```

由于flag变量为volatile变量，那么在进行指令重排序的过程的时候，不会将语句3放到语句1、语句2前面，也不会讲语句3放到语句4、语句5后面。但是要注意语句1和语句2的顺序、语句4和语句5的顺序是不作任何保证的。并且volatile关键字能保证，执行到语句3时，语句1和语句2必定是执行完毕了的，且语句1和语句2的执行结果对语句3、语句4、语句5是可见的。

回到前面举的一个例子：
```java
//线程1:
context = loadContext();   //语句1
inited = true;             //语句2

//线程2:
while(!inited ){
  sleep() 
}
doSomethingwithconfig(context); 
```

前面举这个例子的时候，提到有可能语句2会在语句1之前执行，那么久可能导致context还没被初始化，而线程2中就使用未初始化的context去进行操作，导致程序出错。

这里如果用volatile关键字对inited变量进行修饰，就不会出现这种问题了，因为当执行到语句2时，必定能保证context已经初始化完毕。

### volatile不保证原子性
```java
package com.morris.ch2;

public class VolatileNotAtomic {
	
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {

        final VolatileNotAtomic test = new VolatileNotAtomic();

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

```

自增操作是不具备原子性的，它包括读取变量的原始值、进行加1操作、写入工作内存。那么就是说自增操作的三个子操作可能会分割开执行，就有可能导致下面这种情况出现：

　　假如某个时刻变量inc的值为10，

　　线程1对变量进行自增操作，线程1先读取了变量inc的原始值，然后线程1被阻塞了；

　　然后线程2对变量进行自增操作，线程2也去读取变量inc的原始值，由于线程1只是对变量inc进行读取操作，而没有对变量进行修改操作，所以不会导致线程2的工作内存中缓存变量inc的缓存行无效，所以线程2会直接去主存读取inc的值，发现inc的值时10，然后进行加1操作，并把11写入工作内存，最后写入主存。

　　然后线程1接着进行加1操作，由于已经读取了inc的值，注意此时在线程1的工作内存中inc的值仍然为10，所以线程1对inc进行加1操作后inc的值为11，然后将11写入工作内存，最后写入主存。

　　那么两个线程分别进行了一次自增操作后，inc只增加了1。

根源就在这里，自增操作不是原子性操作，而且volatile也无法保证对变量的任何操作都是原子性的。

　　把上面的代码改成以下任何一种都可以达到效果：

采用synchronized：
```java
package com.morris.ch2;

public class SynchronizedAtomic {
	public volatile int inc = 0;

    public synchronized void increase() {
        inc++;
    }

    public static void main(String[] args) {

        final SynchronizedAtomic test = new SynchronizedAtomic();

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

```

采用Lock：
```java
package com.morris.ch2;

import java.util.concurrent.locks.ReentrantLock;

public class LockAtomic {
	
	public volatile int inc = 0;
	
	public ReentrantLock lock = new ReentrantLock();

    public void increase() {
    	try {			
    		lock.lock();
    		inc++;
		} finally {
			lock.unlock();
		}
    }

    public static void main(String[] args) {

        final LockAtomic test = new LockAtomic();

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

```

采用AtomicInteger：
```java
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

```

