package com.az.task.component;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.az.task.model.StockLevel;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
@Data
public class TaskComponent {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value("${outbound.endpoint}")
	private String destination;
	
	private StockLevel stockLevel;
	public StockLevel getStockLevel() {
		return stockLevel;
	}
	
	@JmsListener(destination="${inbound.endpoint}")
	public void process(StockLevel stockLevel) {
		if(stockLevel!=null) {
			log.info(":::: Message Recieved :::: ");
			this.displayResult(stockLevel);			
			this.sendToDestinationTopicQueue(stockLevel);
			// For Unit-Testing purpose
			this.stockLevel = stockLevel;
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
	
	public void sendToDestinationTopicQueue(StockLevel stockLevel) {
		log.info("sendToDestinationTopicQueue method Start");
		this.jmsTemplate.convertAndSend(destination, stockLevel);
		log.info("Message successfully published on Topic-Queue");
	}
	
}
