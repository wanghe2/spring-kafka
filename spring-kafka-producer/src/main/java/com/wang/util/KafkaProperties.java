package com.wang.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class KafkaProperties {

	@Value("${kafka.producer.bootstrap.servers}")
	public  String servers;
	
	@Value("${kafka.producer.acks}")
	public  String acks;
	
	@Value("${kafka.producer.retries}")
	public  String retries;
	
	@Value("${kafka.producer.linger.ms}")
	public  String ms;
	
	@Value("${kafka.producer.buffer.memory}")
	public  String memory;
	
	@Value("${kafka.producer.batch.size}")
	public  String size;
	
	@Value("${kafka.producer.defaultTopic}")
	public  String defaultTopic;
	
	@Value("${kafka.producer.key.serializer}")
	public  String keySerializer;
	
	@Value("${kafka.producer.value.serializer}")
	public  String valSerializer;
	
}
