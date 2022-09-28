package com.example.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafka.service.KafkaProducerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/demo")
@Api(value = "示例", tags = "示例demo")
public class DemoController {

	@Autowired
	private KafkaProducerService kafkaProducerService;

	/**
	 * 测试同步发送
	 * 
	 * @throws Exception
	 */
	@GetMapping("testSendMessageSync")
	@ApiOperation("测试同步发送")
	void testSendMessageSync() throws Exception {
		String topic = "hello-kafka-test-topic";
		String key = "key1";
		String message = "firstMessage";
		kafkaProducerService.sendMessageSync(topic, key, message);
	}

	/**
	 * 测试异步发送
	 */
	@GetMapping("testSendMessageAsync")
	@ApiOperation("测试异步发送")
	public void testSendMessageAsync() {
		String topic = "hello-kafka-test-topic";
		String message = "firstAsyncMessage";
		kafkaProducerService.sendMessageAsync(topic, message);
	}

	/**
	 * 测试批量消费
	 * 
	 * @throws Exception
	 */
	@GetMapping("testConsumerBatch")
	@ApiOperation("测试批量消费")
	public void testConsumerBatch() throws Exception {
		// 写入多条数据到批量topic：hello-batch
		String topic = "hello-batch";
		for (int i = 0; i < 20; i++) {
			kafkaProducerService.sendMessageSync(topic, null, "batchMessage" + i);
		}
	}

}