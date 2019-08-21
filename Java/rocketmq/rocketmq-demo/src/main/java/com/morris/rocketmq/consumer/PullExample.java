package com.morris.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PullExample {

    private static final Map<MessageQueue, Long> OFFSE_TABLE = new HashMap<MessageQueue, Long>();

    public static void main(String[] args) throws Exception {
        DefaultMQPullConsumer Consumer = new DefaultMQPullConsumer("rocketmq-demo");
        Consumer.setNamesrvAddr("192.168.252.10:9876");
        Consumer.start();
        Set<MessageQueue> mqs = Consumer.fetchSubscribeMessageQueues("TopicTest77");
        System.out.println("message queue size:" + mqs.size());
        for (MessageQueue mq : mqs) {
            System.out.printf("Consumefrom the Queue: " + mq + "%n");
            SINGLE_MQ:
            while (true) {
                try {
                    PullResult pullResult = Consumer.pullBlockIfNotFound(mq, null, getMessageQueueOffset(mq), 32);
                    System.out.println(pullResult);
                    putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                    switch (pullResult.getPullStatus()) {
                        case FOUND:
                            break;
                        case NO_MATCHED_MSG:
                            break;
                        case NO_NEW_MSG:
                            break SINGLE_MQ;
                        case OFFSET_ILLEGAL:
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Consumer.shutdown();
    }

    private static long getMessageQueueOffset(MessageQueue mq) {
        Long Offset = OFFSE_TABLE.get(mq);
        if (Offset != null) {
            return Offset;
        }
        return 0;
    }

    private static void putMessageQueueOffset(MessageQueue mq, long Offset) {
        OFFSE_TABLE.put(mq, Offset);
    }

}
