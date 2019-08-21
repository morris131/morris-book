package com.morris.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

public class MessageQueueSelectorExample {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("rocketmq-demo");
        producer.setNamesrvAddr("192.168.252.10:9876");
        producer.start();
        Message msg = new Message("TopicTest77", "TagA1", "message queue selector mq ".getBytes(RemotingHelper.DEFAULT_CHARSET));

        MessageQueueSelector selector = new MessageQueueSelector() {
            public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                Integer order = (Integer) o;
                return list.get(order % list.size());
            }
        };

        SendResult sendResult = producer.send(msg, selector, 1);
        System.out.println(sendResult);
        producer.shutdown();
    }

}
