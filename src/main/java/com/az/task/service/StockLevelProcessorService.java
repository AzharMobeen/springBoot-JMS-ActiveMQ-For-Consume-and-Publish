package com.az.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.az.task.domain.UCStockLevelIFD;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Data
public class StockLevelProcessorService {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value("${outbound.endpoint}")
	private String destination;
	
	private UCStockLevelIFD stockLevel;
	public UCStockLevelIFD getStockLevel() {
		return stockLevel;
	}
	
	@JmsListener(destination="${inbound.endpoint}")
	public void process(UCStockLevelIFD stockLevel) {		
		
		log.info(":::: Message Recieved :::: ");
		this.displayResult(stockLevel);			
		this.sendToDestinationTopicQueue(stockLevel);		
		
	}
	
	private void displayResult(UCStockLevelIFD stockLevel) {
		if(!CollectionUtils.isEmpty(stockLevel.getCtrlSegList()))
			stockLevel.getCtrlSegList().forEach(ctrlSeg->{
				log.info("\n:::: "+ ctrlSeg.toString()+ " :::: ");
			});
		else
			log.error("stockLevel.getCtrlSegList :: null");
	}
	
	public void sendToDestinationTopicQueue(UCStockLevelIFD stockLevel) {
		log.info("\n\tsendToDestinationTopicQueue method Start");
		this.jmsTemplate.convertAndSend(destination, stockLevel);
		log.info("\n\tMessage successfully published on Topic-Queue");
		// For Testing purpose flag
		this.stockLevel = stockLevel;
	}
	
}
