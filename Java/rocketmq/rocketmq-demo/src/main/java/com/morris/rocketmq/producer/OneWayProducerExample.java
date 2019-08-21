package com.morris.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class OneWayProducerExample {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("rocketmq-demo");
        producer.setNamesrvAddr("192.168.252.10:9876");
        producer.start();
        Message msg = new Message("TopicTest77", "TagA1", "One Way".getBytes(RemotingHelper.DEFAULT_CHARSET));
        producer.sendOneway(msg);
        producer.shutdown();
    }
}
