package com.example.rabbitmq.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqPublisher {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Scheduled(cron = "0/15 * * * * ?")
	public void execute() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String time = formatter.format(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
		// 默认
		rabbitTemplate.convertAndSend("queue", time);
		// 主题模式
		rabbitTemplate.convertAndSend("topic", "1.topic", time);
		rabbitTemplate.convertAndSend("topic", "2.2.topic", time);
		// 直连模式
		rabbitTemplate.convertAndSend("direct", "direct.3", time);
		rabbitTemplate.convertAndSend("direct", "direct.4", time);
		// 广播模式
		rabbitTemplate.convertAndSend("fanout", "", time);
		// headers模式
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setHeader("header", "header");
		messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
		Message message = MessageBuilder.withBody(time.getBytes()).andProperties(messageProperties).build();
		rabbitTemplate.convertAndSend("headers", "", message);
	}
}