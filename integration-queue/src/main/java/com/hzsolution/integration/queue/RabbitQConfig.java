package com.hzsolution.integration.queue;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.MessagingMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitQConfig {
    @Value("spring.rabbitmq.host")
    private String hostname;

    @Value("spring.rabbitmq.port")
    private int port;

    @Value("spring.rabbitmq.username")
    private String username;

    @Value("spring.rabbitmq.password")
    private String password;

    @Value("spring.rabbitmq.inbound.routingkey")
    private String inboundRoutingKey;

    @Value("spring.rabbitmq.outboundroutingkey")
    private String outboundRoutingKey;

    // for message consumer
    @Bean
    Queue myInboundQueue() {
        return new Queue(inboundRoutingKey, true);
    }

    @Bean
    TopicExchange myInboundExchange() {
        return new TopicExchange(""); // default exchange
    }

    @Bean
    Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(inboundRoutingKey);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(inboundRoutingKey);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(RabbitQListner rabbitQListner) {
        return new MessageListenerAdapter(rabbitQListner, "processMessage");
    }

    // for message producer
    @Bean
    public RabbitTemplate rabbitTemplate() {
        final RabbitTemplate template = new RabbitTemplate(outboundConnectionFactory());
        template.setMessageConverter(new MessagingMessageConverter());
        return template;
    }

    public ConnectionFactory outboundConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(hostname);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }
}
