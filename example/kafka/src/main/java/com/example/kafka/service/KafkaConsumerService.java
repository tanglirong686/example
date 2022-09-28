package com.example.kafka.service;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * kafka 消费服务
 *
 * @author Leo
 * @create 2020/12/31 16:06
 **/

@Service
public class KafkaConsumerService {

	private static final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);

	/**
	 * 消费单条消息,topics 可以监听多个topic，如：topics = {"topic1", "topic2"}
	 * 
	 * @param message 消息
	 */
	@KafkaListener(id = "consumerSingle", topics = "hello-kafka-test-topic")
	public void consumerSingle(String message) {
		log.info("consumerSingle ====> message: {}", message);
	}

	/**
	 * 批量消费消息
	 * 
	 * @param messages
	 */
	@KafkaListener(id = "consumerBatch", topics = "hello-batch")
	public void consumerBatch(List<ConsumerRecord<String, String>> messages) {
		log.info("consumerBatch =====> messageSize: {}", messages.size());
		log.info(messages.toString());
	}
	
	/**
	 * @param message
	 */
	@KafkaListener(id = "consumerCurve", topics = "ResultQ")
	public void consumerCurve(String message) {
		log.info("consumerCurve ====> message: {}", message);
	}
}