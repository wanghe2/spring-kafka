package com.wang.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import com.wang.listener.KafkaAnotherConsumerListener;
import com.wang.listener.KafkaConsumerListener;
import com.wang.util.KafkaProperties;

@Configuration
public class Config {
	
	@Autowired
	KafkaProperties kafkaProperties;
	
	@Bean
	public ConsumerFactory<Integer,String>kafkaConsumerFactory(){
		ConsumerFactory<Integer, String>consumerFactory=new DefaultKafkaConsumerFactory<Integer, String>(consumerProperties());
		return consumerFactory;
	}
	
	   @Bean
	    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>>
	    kafkaListenerContainerFactory() {
	       ConcurrentKafkaListenerContainerFactory<Integer, String> factory =
	                new ConcurrentKafkaListenerContainerFactory<Integer, String>();
	        factory.setConsumerFactory(kafkaConsumerFactory());
	        factory.setConcurrency(3);
	        factory.getContainerProperties().setPollTimeout(3000);
	        return factory;
	    }
	
	@Bean
    public Map<String, Object> consumerProperties() {
        Map<String, Object> props= new HashMap<String, Object>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.server);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,  kafkaProperties.groupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,  kafkaProperties.commit);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, kafkaProperties.interval);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,  kafkaProperties.timeout);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,  kafkaProperties.keyDeserializer);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,  kafkaProperties.valDeserializer);
        return props;
    }
	
	@Bean
    public KafkaConsumerListener kafkaConsumerListener(){
        return new KafkaConsumerListener();
    }

	@Bean
    public KafkaAnotherConsumerListener kafkaAnotherConsumerListener(){
        return new KafkaAnotherConsumerListener();
    }


	
	
}
