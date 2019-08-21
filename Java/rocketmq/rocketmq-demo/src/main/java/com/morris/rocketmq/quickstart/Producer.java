package com.morris.rocketmq.quickstart;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class Producer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("rocketmq-demo");
        producer.setNamesrvAddr("192.168.252.10:9876");
        producer.start();
        Message msg = new Message("TopicTest77", "TagA1", "Hello RocketMQ ".getBytes(RemotingHelper.DEFAULT_CHARSET));
        SendResult sendResult = producer.send(msg);
        System.out.println(sendResult);
        producer.shutdown();
    }

}
