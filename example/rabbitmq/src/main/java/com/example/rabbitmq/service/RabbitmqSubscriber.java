package com.example.rabbitmq.service;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
public class RabbitmqSubscriber {
	
	// 1.默认队列
	@RabbitListener(queues = "queue")
	public void queueDouble1(String text, Channel channel, Message message) throws IOException {
		System.out.println("queueDouble1:" + text);
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}

	@RabbitListener(queues = "queue")
	public void queueDouble2(String text, Channel channel, Message message) throws IOException {
		System.out.println("queueDouble2:" + text);
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}

	// 2.主题队列
	@RabbitListener(queues = "queue1")
	public void queue1(String text, Channel channel, Message message) throws IOException {
		System.out.println("queue1:" + text);
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}

	@RabbitListener(queues = "queue2")
	public void queue2(String text, Channel channel, Message message) throws IOException {
		System.out.println("queue2:" + text);
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}

	// 3.直连队列
	@RabbitListener(queues = "queue3")
	public void queue3(String text, Channel channel, Message message) throws IOException {
		System.out.println("queue3:" + text);
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}

	@RabbitListener(queues = "queue4")
	public void queue4(String text, Channel channel, Message message) throws IOException {
		System.out.println("queue4:" + text);
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}
}