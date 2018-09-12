## ThreadPoolExecutor详解
通过Executor框架的工具类Executors，可以创建3种类型的ThreadPoolExecutor。
- FixedThreadPool。
- SingleThreadExecutor。
- CachedThreadPool。

### FixedThreadPool
FixedThreadPool被称为可重用固定线程数的线程池。下面是FixedThreadPool的源代码实现。

```
    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>());
    }
```

1. 如果当前运行的线程数少于corePoolSize，则创建新线程来执行任务。
2. 如果当前运行的线程数等于corePoolSize），将任务加入LinkedBlockingQueue。
3. 线程执行完1中的任务后，会在循环中反复从LinkedBlockingQueue获取任务来执行。

FixedThreadPool使用无界队列LinkedBlockingQueue作为线程池的工作队列（队列的容量为Integer.MAX_VALUE）。使用无界队列作为工作队列会对线程池带来如下影响。
1. 当线程池中的线程数达到corePoolSize后，新任务将在无界队列中等待，因此线程池中的线程数不会超过corePoolSize。
2. 由于1，使用无界队列时maximumPoolSize将是一个无效参数。
3. 由于1和2，使用无界队列时keepAliveTime将是一个无效参数。
4. 由于使用无界队列，运行中的FixedThreadPool（未执行方法shutdown()或
shutdownNow()）不会拒绝任务（不会调用RejectedExecutionHandler.rejectedExecution方法）。

### SingleThreadExecutor
SingleThreadExecutor是使用单个worker线程的Executor。下面是SingleThreadExecutor的源代码实现。

```
    public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>()));
    }
```
1. 如果当前线程池中无运行的线程，则创建一个新线程来执行任务。
2. 如果当前线程池中有一个运行的线程，将任务加入Linked-BlockingQueue。
3. 线程执行完1中的任务后，会在一个无限循环中反复从LinkedBlockingQueue获取任务来执行。


### CachedThreadPool
CachedThreadPool是一个会根据需要创建新线程的线程池。下面是创建CachedThreadPool的源代码。

```
    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                      60L, TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>());
    }
```
CachedThreadPool使用没有容量的SynchronousQueue作为线程池的工作队列，但
CachedThreadPool的maximumPool是无界的。这意味着，如果主线程提交任务的速度高于
maximumPool中线程处理任务的速度时，CachedThreadPool会不断创建新线程。极端情况下，
CachedThreadPool会因为创建过多线程而耗尽CPU和内存资源。

1. 由于corePoolSize为0，首先执行SynchronousQueue.offer（Runnabletask）。如果当前maximumPool中有空闲线程正在执行SynchronousQueue.poll（keepAliveTime，TimeUnit.NANOSECONDS），那么主线程执行offer操作与空闲线程执行的poll操作配对成功，主线程把任务交给空闲线程执行，execute()方法执行完成；否则执行下面的步骤2）。
2. 当初始maximumPool为空，或者maximumPool中当前没有空闲线程时，将没有线程执行SynchronousQueue.poll（keepAliveTime，TimeUnit.NANOSECONDS）。这种情况下，步骤1）将失败。此时CachedThreadPool会创建一个新线程执行任务，execute()方法执行完成。
3. 在步骤2）中新创建的线程将任务执行完后，会执行SynchronousQueue.poll（keepAliveTime，TimeUnit.NANOSECONDS）。这个poll操作会让空闲线程最多在SynchronousQueue中等待60秒钟。如果60秒钟内主线程提交了一个新任务（主线程执行步骤1）），那么这个空闲线程将执行主线程提交的新任务；否则，这个空闲线程将终止。由于空闲60秒的空闲线程会被终止，因此长时间保持空闲的CachedThreadPool不会使用任何资源。