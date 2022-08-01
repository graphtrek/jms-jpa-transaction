package co.grtk.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.Queue;

@Configuration
@EnableJms
public class JMSConfig {
	@Value("${activemq.broker-url}")
	private String brokerUrl;

	@Value("${activemq.message-queue}")
	private String messageQueueName;

	@Bean
	public Queue queue(){
		return new ActiveMQQueue(messageQueueName);
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
		jmsTemplate.setSessionTransacted(true);
		jmsTemplate.setMessageConverter(messageConverter());
		return jmsTemplate;
	}

	@Bean
	public CachingConnectionFactory connectionFactory() {
		CachingConnectionFactory factory = new CachingConnectionFactory();
		ActiveMQConnectionFactory activeMQConnFactory = new ActiveMQConnectionFactory();
		activeMQConnFactory.setBrokerURL(brokerUrl);
		factory.setTargetConnectionFactory(activeMQConnFactory);
		factory.setClientId("client123");
		return factory;
	}

	@Bean
	public JmsListenerContainerFactory<?> queueListenerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setMessageConverter(messageConverter());
		return factory;
	}

	@Bean
	public MessageConverter messageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
}