package com.example.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;

/**
 * 第二种：topic交换机模式（主题模式）
 *
 */
public class TopicExchangeConfiguration {
	
	@Bean
	public Queue queue1() {
		return new Queue("queue1", false);
	}

	@Bean
	public Queue queue2() {
		return new Queue("queue2", false);
	}

	/**
	 * 	声明交换机类型：存在4个参数（String name, boolean durable, boolean autoDelete,
	 * Map<String, Object> arguments） 这里的参数基本和queue一样的理解
	 * 
	 * @return
	 */
	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange("topic", false, false);
	}

	/**
	 * 	绑定队列到交换机上面
	 * 
	 * @return
	 */
	@Bean
	public Binding binding1() {
		return BindingBuilder.bind(queue1()).to(topicExchange()).with("*.topic");
	}

	/**
	 * 	这里存在两种匹配符 *：代表一个单位的字符（1.topic） #：代表多个单位的字符(2.2.topic)
	 * 
	 * @return
	 */
	@Bean
	public Binding binding2() {
		return BindingBuilder.bind(queue2()).to(topicExchange()).with("#.topic");
	}
}