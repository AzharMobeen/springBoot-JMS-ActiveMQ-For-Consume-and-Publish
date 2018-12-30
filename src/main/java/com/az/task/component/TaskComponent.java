package com.az.task.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import com.az.task.model.StockLevel;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@EnableJms
@Component
public class TaskComponent {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value("${outbound.endpoint}")
	private String destination;
	
	
	@JmsListener(destination="${inbound.endpoint}")
	public void process(StockLevel stockLevel) {
		if(stockLevel!=null) {
			log.info(":::: Message Recieved :::: ");
			displayResult(stockLevel);
			sendToDestinationTopicQueue(stockLevel);
		}
		else
			log.error("stockLevel :: Null");
	}
	
	private void displayResult(StockLevel stockLevel) {
		if(!CollectionUtils.isEmpty(stockLevel.getCtrlSegList()))
			stockLevel.getCtrlSegList().forEach(ctrlSeg->{
				log.info(":::: "+ ctrlSeg.toString()+ " :::: ");
			});
		else
			log.error("stockLevel.getCtrlSegList :: null");
	}
	private void sendToDestinationTopicQueue(StockLevel stockLevel) {
		log.info("sendToDestinationTopicQueue method Start");
		jmsTemplate.convertAndSend(destination, stockLevel);
		log.info("Message successfully published on Topic-Queue");
	}
}
