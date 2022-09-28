package com.example.kafka.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.extern.slf4j.Slf4j;

/**
 * kafka 生产服务
 *
 **/
@Slf4j
@Service
public class KafkaProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	/**
	 * 发送消息（同步）
	 * 
	 * @param topic   主题
	 * @param key     键
	 * @param message 值
	 */
	public void sendMessageSync(String topic, String key, String message)
			throws InterruptedException, ExecutionException, TimeoutException {
		// 可以指定最长等待时间，也可以不指定
		kafkaTemplate.send(topic, message).get(10, TimeUnit.SECONDS);
		log.info("sendMessageSync => topic: {}, key: {}, message: {}", topic, key, message);
	}

	/**
	 * 发送消息（异步）
	 * 
	 * @param topic   主题
	 * @param message 消息内容
	 */
	public void sendMessageAsync(String topic, String message) {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
		// 添加回调
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onFailure(Throwable throwable) {
				log.error("sendMessageAsync failure! topic : {}, message: {}", topic, message);
			}

			@Override
			public void onSuccess(SendResult<String, String> stringStringSendResult) {
				log.info("sendMessageAsync success! topic: {}, message: {}", topic, message);
			}
		});
	}
}