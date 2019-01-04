package com.az.task.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "UC_STOCK_LEVEL_IFD")
public class UCStockLevelIFD {
	
	@XmlElement(name = "CTRL_SEG")
	private List<ControlSegment> ctrlSegList;

}
