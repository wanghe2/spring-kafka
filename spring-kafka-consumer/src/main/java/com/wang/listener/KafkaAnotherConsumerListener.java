package com.wang.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaAnotherConsumerListener {
   
    @KafkaListener(groupId="group2" ,topics = "topic1")
    void listener(ConsumerRecord<String, String> data){
        System.err.println("KafkaAnotherConsumerListener --消费者线程："+Thread.currentThread().getName()+"[ 消息 来自kafkatopic："+data.topic()+",分区："+data.partition()
                +" ，委托时间："+data.timestamp()+"]消息内容如下：");
        System.err.println(data.value());
    }
}