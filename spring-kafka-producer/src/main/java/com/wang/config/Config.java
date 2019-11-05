package com.wang.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.wang.util.KafkaProperties;

@Configuration
public class Config {
	
	@Autowired
	KafkaProperties kafkaProperties;
	
	@Bean
	ProducerFactory<Integer, String> kafkaProducerFactory() {
		DefaultKafkaProducerFactory<Integer, String> kafkaProducerFactory=new DefaultKafkaProducerFactory<Integer, String> (getProps());
		return kafkaProducerFactory;
	}
	
	@Bean
	public Map<String, Object>getProps(){
		Map<String, Object> props = new HashMap<String, Object>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.servers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, kafkaProperties.keySerializer);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,kafkaProperties.valSerializer);
        props.put(ProducerConfig.RETRIES_CONFIG,kafkaProperties.retries);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG,kafkaProperties.size);
        props.put(ProducerConfig.LINGER_MS_CONFIG,kafkaProperties.ms);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG,kafkaProperties.memory);
        props.put(ProducerConfig.ACKS_CONFIG,kafkaProperties.acks);
        return props;
	}
	
	@Bean
	public KafkaTemplate<Integer, String>  kafkaTemplate() {
		KafkaTemplate<Integer, String> kafkaTemplate=new KafkaTemplate<Integer, String> (kafkaProducerFactory(), true);
		kafkaTemplate.setDefaultTopic(kafkaProperties.defaultTopic);
		return kafkaTemplate;
	}
}
