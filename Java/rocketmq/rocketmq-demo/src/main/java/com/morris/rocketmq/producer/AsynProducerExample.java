package com.morris.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.TimeUnit;

public class AsynProducerExample {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("rocketmq-demo");
        producer.setNamesrvAddr("192.168.252.10:9876");
        producer.start();
        Message msg = new Message("TopicTest77", "TagA1", "Hello RocketMQ ".getBytes(RemotingHelper.DEFAULT_CHARSET));
        producer.send(msg, new SendCallback() {
            public void onSuccess(SendResult sendResult) {
                System.out.println(sendResult);
            }

            public void onException(Throwable throwable) {
                System.out.println(throwable);
            }
        });
        TimeUnit.SECONDS.sleep(1);
        producer.shutdown();
    }
}
