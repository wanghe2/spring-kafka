package com.wang.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class KafkaProperties {

	@Value("${kafka.consumer.bootstrap.servers}")
	public  String server;
	
	@Value("${kafka.consumer.enable.auto.commit}")
	public  Boolean commit;
	
	@Value("${kafka.consumer.auto.commit.interval.ms}")
	public  Integer interval;
	
	@Value("${kafka.consumer.group.id}")
	public  String groupId;
	
	@Value("${kafka.consumer.session.timeout.ms}")
	public  String timeout;
	
	@Value("${kafka.consumer.key.deserializer}")
	public  String keyDeserializer;
	
	@Value("${kafka.consumer.value.deserializer}")
	public  String valDeserializer;
	
}
