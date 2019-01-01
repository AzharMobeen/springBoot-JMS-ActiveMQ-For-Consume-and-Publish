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
@Component
public class ConsumerJMS {

	
	@Autowired
	private JmsTemplate jmsTemplate;

	@Value("${outbound.endpoint}")
	private String destination;

	@Autowired
	private PublisherJMS publisherJMS;
	
	private StockLevel stockLevel;
	
	public StockLevel getStockLevel() {
		return stockLevel;
	}

	//@JmsListener(destination="${inbound.endpoint}",containerFactory="jmsListenerContainerFactory")	
	public StockLevel consume(StockLevel stockLevel) {
		if(stockLevel!=null) {
			log.info(":::: Message Recieved :::: ");
			this.displayResult(stockLevel);	
			this.jmsTemplate.setPubSubDomain(true);
			publisherJMS.sendToDestinationTopics(stockLevel,this.jmsTemplate);
			this.stockLevel = stockLevel;
			return stockLevel;
		}
		else {
			log.error("stockLevel :: Null");
			return stockLevel;
		}
	}

	private void displayResult(StockLevel stockLevel) {
		if (!CollectionUtils.isEmpty(stockLevel.getCtrlSegList()))
			stockLevel.getCtrlSegList().forEach(ctrlSeg -> {
				log.info(":::: " + ctrlSeg.toString() + " :::: ");
			});
		else
			log.error("stockLevel.getCtrlSegList :: null");
	}

	
}
