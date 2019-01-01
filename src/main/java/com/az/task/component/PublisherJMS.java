package com.az.task.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.az.task.model.StockLevel;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class PublisherJMS {	
	
	@Value("${outbound.endpoint}")
	private String destination;		
	
	public void sendToDestinationTopics(StockLevel stockLevel, JmsTemplate jmsTemplate) {
		log.info("sendToDestinationTopicQueue method Start");
		jmsTemplate.convertAndSend(destination, stockLevel);
		log.info("Message successfully published on Topic-Queue");
	}
	
	public void sendToDestinationQueues(String destination, StockLevel stockLevel, JmsTemplate jmsTemplate) {
		log.info("sendToDestinationTopicQueue method Start");
		jmsTemplate.convertAndSend(destination, stockLevel);
		log.info("Message successfully published on Queue");
	}
}
