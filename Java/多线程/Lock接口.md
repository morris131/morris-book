## Lock接口
在Lock接口出现之前，Java程序是靠synchronized关键字实现锁功能的，而Java SE 5之后，并发包中新增了Lock接口（以及相关实现类）用来实现锁功能，它提供了与synchronized关键字类似的同步功能，只是在使用时需要显式地获取和释放锁。虽然它缺少了（通过synchronized块或者方法所提供的）隐式获取释放锁的便捷性，但是却拥有了锁获取与释放的可操作性、可中断的获取锁以及超时获取锁等多种synchronized关键字所不具备的同步特性。

### Lock的使用
```java
Lock lock = new ReentrantLock();
lock.lock();
try {
    // TODO
} finally {
lock.unlock();
}
```

在finally块中释放锁，目的是保证在获取到锁之后，最终能够被释放。不要将获取锁的过程写在try块中，因为如果在获取锁（自定义锁的实现）时发生了异常，异常抛出的同时，也会导致锁无故释放。

### Lock的基本方法
1. lock()获得锁
2. unlock() 释放锁
3. tryLock() 这个方法确保了在需要锁的时候，是未加锁的状态。返回true,表示是未加锁的，返回false表示已加锁
4. new Condition()  返回当前lock的一个Condition实例

### Lock与synchronized的区别
1. synchronized 在成功完成功能或者抛出异常时，虚拟机会自动释放线程占有的锁；而Lock对象在发生异常时，如果没有主动调用unLock()方法去释放锁，则锁对象会一直持有，因此使用Lock时需要在finally块中释放锁；
2. lock接口锁可以通过多种方法来尝试获取锁包括立即返回是否成功的tryLock(),以及一直尝试获取的lock()方法和尝试等待指定时间长度获取的方法，相对灵活了许多比synchronized;
3. 通过在读多，写少的高并发情况下，我们用ReentrantReadWriteLock分别获取读锁和写锁来提高系统的性能，因为读锁是共享锁，即可以同时有多个线程读取共享资源，而写锁则保证了对共享资源的修改只能是单线程的。