package com.az.task.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "UC_STOCK_LEVEL_IFD")
public class StockLevel {
	
	@XmlElement(name = "CTRL_SEG")
	private List<CtrlSeg> ctrlSegList;

}
