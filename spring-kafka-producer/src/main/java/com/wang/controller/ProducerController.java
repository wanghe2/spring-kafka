package com.wang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hsc
 * @date: 2018/6/19 14:44
 * @description 文件描述
 */

@RestController
@RequestMapping(value = "/kafka")
public class ProducerController {

    @SuppressWarnings("rawtypes")
	@Autowired
    KafkaTemplate kafkaTemplate;

    
    private  String optimizeTopic="topic1" ;

    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/producer" , method = RequestMethod.GET)
    public void producer(@RequestParam String param){
        kafkaTemplate.send(optimizeTopic,param+"-----第一次发消息");
        ListenableFuture<SendResult<String, String>> listenableFuture =  kafkaTemplate.sendDefault(param+"-------第二次消息");;
        //发送成功回调
        SuccessCallback<SendResult<String, String>> successCallback = new SuccessCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                //成功业务逻辑
                System.out.println("onSuccess");
            }
        };
        //发送失败回调
        FailureCallback failureCallback = new FailureCallback() {
            @Override
            public void onFailure(Throwable ex) {
                //失败业务逻辑
                System.out.println("onFailure");
            }
        };
        listenableFuture.addCallback(successCallback, failureCallback);
    }
}