package com.morris.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

public class BroadcastingPushExample {


    public static void main(String[] args) throws Exception {
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("touna-fund");
//        consumer.setNamesrvAddr("192.168.252.10:9876");
//        consumer.setMessageModel(MessageModel.BROADCASTING);
//
//        consumer.subscribe("TopicTest77", "*");
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });
//
//        consumer.start();
//
//        System.out.printf("Consumer Started.%n");



    }
}
