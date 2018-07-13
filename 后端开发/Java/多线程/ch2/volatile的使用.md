## volatile��ʹ��

### volatile�ɼ���
```java

//�߳�1

boolean stop = false;

while(!stop){

    doSomething();

}

 

//�߳�2

stop = true;
```
�ڴ����ʱ����������ܹ����߳��жϣ�����Ҳ�п��ܻᵼ���޷��ж��̣߳���Ȼ��������Ժ�С������ֻҪһ��������������ͻ������ѭ���ˣ���

ÿ���߳������й����ж����Լ��Ĺ����ڴ棬��ô�߳�1�����е�ʱ�򣬻Ὣstop������ֵ����һ�ݷ����Լ��Ĺ����ڴ浱�С����߳�2������stop������ֵ֮�󣬵��ǻ�û���ü�д�����浱�У��߳�2תȥ�����������ˣ���ô�߳�1���ڲ�֪���߳�2��stop�����ĸ��ģ���˻���һֱѭ����ȥ��

������volatile����֮��ͱ�ò�һ���ˣ�

������һ��ʹ��volatile�ؼ��ֻ�ǿ�ƽ��޸ĵ�ֵ����д�����棻

�����ڶ���ʹ��volatile�ؼ��ֵĻ������߳�2�����޸�ʱ���ᵼ���߳�1�Ĺ����ڴ��л������stop�Ļ�������Ч����ӳ��Ӳ����Ļ�������CPU��L1����L2�����ж�Ӧ�Ļ�������Ч����

���������������߳�1�Ĺ����ڴ��л������stop�Ļ�������Ч�������߳�1�ٴζ�ȡ����stop��ֵʱ��ȥ�����ȡ��

������ô���߳�2�޸�stopֵʱ����Ȼ�������2���������޸��߳�2�����ڴ��е�ֵ��Ȼ���޸ĺ��ֵд���ڴ棩����ʹ���߳�1�Ĺ����ڴ��л������stop�Ļ�������Ч��Ȼ���߳�1��ȡʱ�������Լ��Ļ�������Ч������ȴ������ж�Ӧ�������ַ������֮��Ȼ��ȥ��Ӧ�������ȡ���µ�ֵ����ô�߳�1��ȡ���ľ������µ���ȷ��ֵ��

### volatile������

volatile����һ���̶��ϱ�֤�����ԡ�

volatile�ؼ��ֽ�ָֹ����������������˼��

1. ������ִ�е�volatile�����Ķ���������д����ʱ������ǰ��Ĳ����ĸ��Ŀ϶�ȫ���Ѿ����У��ҽ���Ѿ��Ժ���Ĳ����ɼ����������Ĳ����϶���û�н��У�
2. �ڽ���ָ���Ż�ʱ�����ܽ��ڶ�volatile�������ʵ������������ִ�У�Ҳ���ܰ�volatile������������ŵ���ǰ��ִ�С�

```java
//x��yΪ��volatile����
//flagΪvolatile����
x = 2;        //���1
y = 0;        //���2
flag = true;  //���3
x = 4;         //���4
y = -1;       //���5 
```

����flag����Ϊvolatile��������ô�ڽ���ָ��������Ĺ��̵�ʱ�򣬲��Ὣ���3�ŵ����1�����2ǰ�棬Ҳ���ὲ���3�ŵ����4�����5���档����Ҫע�����1�����2��˳�����4�����5��˳���ǲ����κα�֤�ġ�����volatile�ؼ����ܱ�֤��ִ�е����3ʱ�����1�����2�ض���ִ������˵ģ������1�����2��ִ�н�������3�����4�����5�ǿɼ��ġ�

�ص�ǰ��ٵ�һ�����ӣ�
```java
//�߳�1:
context = loadContext();   //���1
inited = true;             //���2

//�߳�2:
while(!inited ){
  sleep() 
}
doSomethingwithconfig(context); 
```

ǰ���������ӵ�ʱ���ᵽ�п������2�������1֮ǰִ�У���ô�ÿ��ܵ���context��û����ʼ�������߳�2�о�ʹ��δ��ʼ����contextȥ���в��������³������

���������volatile�ؼ��ֶ�inited�����������Σ��Ͳ���������������ˣ���Ϊ��ִ�е����2ʱ���ض��ܱ�֤context�Ѿ���ʼ����ϡ�

### volatile����֤ԭ����
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
         
        while(Thread.activeCount()>1) { //��֤ǰ����̶߳�ִ����
        	Thread.yield();
        }
        System.out.println(test.inc);

    }

}

```

���������ǲ��߱�ԭ���Եģ���������ȡ������ԭʼֵ�����м�1������д�빤���ڴ档��ô����˵���������������Ӳ������ܻ�ָִ�У����п��ܵ�����������������֣�

��������ĳ��ʱ�̱���inc��ֵΪ10��

�����߳�1�Ա������������������߳�1�ȶ�ȡ�˱���inc��ԭʼֵ��Ȼ���߳�1�������ˣ�

����Ȼ���߳�2�Ա������������������߳�2Ҳȥ��ȡ����inc��ԭʼֵ�������߳�1ֻ�ǶԱ���inc���ж�ȡ��������û�жԱ��������޸Ĳ��������Բ��ᵼ���߳�2�Ĺ����ڴ��л������inc�Ļ�������Ч�������߳�2��ֱ��ȥ�����ȡinc��ֵ������inc��ֵʱ10��Ȼ����м�1����������11д�빤���ڴ棬���д�����档

����Ȼ���߳�1���Ž��м�1�����������Ѿ���ȡ��inc��ֵ��ע���ʱ���߳�1�Ĺ����ڴ���inc��ֵ��ȻΪ10�������߳�1��inc���м�1������inc��ֵΪ11��Ȼ��11д�빤���ڴ棬���д�����档

������ô�����̷ֱ߳������һ������������incֻ������1��

��Դ�������������������ԭ���Բ���������volatileҲ�޷���֤�Ա������κβ�������ԭ���Եġ�

����������Ĵ���ĳ������κ�һ�ֶ����ԴﵽЧ����

����synchronized��
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
         
        while(Thread.activeCount()>1) { //��֤ǰ����̶߳�ִ����
        	Thread.yield();
        }
        System.out.println(test.inc);

    }
}

```

����Lock��
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
         
        while(Thread.activeCount()>1) { //��֤ǰ����̶߳�ִ����
        	Thread.yield();
        }
        System.out.println(test.inc);

    }
}

```

����AtomicInteger��
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
         
        while(Thread.activeCount()>1) { //��֤ǰ����̶߳�ִ����
        	Thread.yield();
        }
        System.out.println(test.inc);

    }
}

```

