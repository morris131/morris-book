package com.morris.rocketmq.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionProducer {

    public static void main(String[] args) throws Exception {

        TransactionMQProducer producer = new TransactionMQProducer("rocketmq-demo");
        producer.setNamesrvAddr("192.168.252.10:9876");

        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        });
        producer.setExecutorService(executorService);

        producer.setTransactionListener(new TransactionListener() {
            private AtomicInteger transactionIndex = new AtomicInteger(0);

            private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                int value = transactionIndex.getAndIncrement();
                System.out.println(Thread.currentThread().getName()+  "-executeLocalTransaction:" + new String(msg.getBody()) + ",value=" + value);
                int status = value % 3;
                localTrans.put(msg.getTransactionId(), status);
                return LocalTransactionState.UNKNOW;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                System.out.println(Thread.currentThread().getName()+  "-checkLocalTransaction:" + new String(msg.getBody()));
                Integer status = localTrans.get(msg.getTransactionId());
                if (null != status) {
                    switch (status) {
                        case 0:
                            return LocalTransactionState.COMMIT_MESSAGE;
                        case 1:
                            return LocalTransactionState.COMMIT_MESSAGE;
                        case 2:
                            return LocalTransactionState.ROLLBACK_MESSAGE;
                    }
                }
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });

        producer.start();

        for(int i = 0; i < 10; i++) {
            Message message = new Message("transactionTag", ("transactionDemo" + i).getBytes());
            producer.sendMessageInTransaction(message, i);
            System.out.println(message);
        }


        TimeUnit.MINUTES.sleep(1);
        producer.shutdown();

    }

}
