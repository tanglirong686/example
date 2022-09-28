package com.example.rabbitmq.config;

import java.text.MessageFormat;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 	消息发布配置
 */
@Configuration
public class PublisherConfiguration {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Bean
	private RabbitTemplate rabbitTemplate() {
		// 1、设置publisher-confirms为true
		// 2、发布确认，只是在exchange范围
		// 3、如果没有exchange,则false.如果过为true,则说明发送到exchange成功
		rabbitTemplate.setConfirmCallback((correlationData, ack, s) -> {
			if (ack) {
				System.out.println("send success");
			} else {
				System.out.println("send fail");
			}
		});
		// 1、设置publisher-returns为true
		// 2、如果没有发布成功，则将消息返回。当然这只是在接受消息层，不是exchange。
		rabbitTemplate.setReturnCallback((message, id, reason, exchange, routingKey) -> {
			StringBuffer buffer = new StringBuffer();
			buffer.append("----------------------------------------\n");
			buffer.append("接受消息: {0},失败!\n");
			buffer.append("消息ID: {1}\n");
			buffer.append("原因: {2}\n");
			buffer.append("exchange: {3}\n");
			buffer.append("routingKey: {4}\n");
			buffer.append("----------------------------------------");
			MessageFormat messageFormat = new MessageFormat(buffer.toString());
			String text = messageFormat.format(new Object[]{new String(message.getBody()), id, reason, exchange, routingKey});
			System.out.println(text);
		});
		return rabbitTemplate;
	}
}