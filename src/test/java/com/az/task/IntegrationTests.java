package com.az.task;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import javax.jms.ConnectionFactory;

import org.apache.activemq.junit.EmbeddedActiveMQBroker;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.az.task.component.TaskComponent;
import com.az.task.model.CtrlSeg;
import com.az.task.model.StockLevel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties= {"spring.activemq.broker-url=vm://localhost?broker.persistent=false&broker.useShutdownHook=false","spring.activemq.in-memory=true"})
public class IntegrationTests{			

	@ClassRule
	public static EmbeddedActiveMQBroker broker = new EmbeddedActiveMQBroker();	
	
	@Autowired
	private ConnectionFactory connectionFactory;
	
	@Autowired
	private MarshallingMessageConverter marshallingMessageConverter;
	
	@Value("${outbound.endpoint}")
	private String destination;
	
	@Value("${inbound.endpoint}")
	private String source;		
	private JmsTemplate jmsTemplate;		
	
	@Autowired
	private TaskComponent taskComponent;			
	
	private StockLevel stockLevel;
	
	@Before
	public void configurations() {
		connectionFactory = broker.createConnectionFactory();		
		this.jmsTemplate = new JmsTemplate(this.connectionFactory);		
		this.jmsTemplate.setMessageConverter(marshallingMessageConverter);
		this.prepareDummyData();				
	}	
	
	
	@Test
	public void consumerFromQueuesAndSendToTopic() {
		
		// For consume test I need to Publish message to ("source") ActiveMQ-Queues 
		// Then it will consume from same Queue and Send to ("destination") ActiveMQ-Topics
		this.jmsTemplate.convertAndSend(source,stockLevel);
		// With In three seconds. @JmsListener from TaskComponent will consume message.
		
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {	
			log.error(e.toString());
		}
        assertThat(this.taskComponent.getStockLevel()).isEqualTo(stockLevel);                
	}		
	
	private void prepareDummyData() {
		StockLevel stockLevel = new StockLevel();		
		CtrlSeg ctrlSeg = new CtrlSeg("UU_SSSS_LEVEL","20180100",
				"0de01919-81eb-4cc7-a51d-15f6085fc1a4","WHHHH","CLI","xxxx",
				"bcccc8-5a07-4hi6-8yyy-8290d3ccfb51","6543");		
		stockLevel.setCtrlSegList(Arrays.asList(ctrlSeg));				
		this.stockLevel = stockLevel;
	}

	
}