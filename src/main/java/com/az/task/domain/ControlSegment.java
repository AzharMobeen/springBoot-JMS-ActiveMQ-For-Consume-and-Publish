package com.az.task.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CTRL_SEG")
public class ControlSegment {

	public ControlSegment() {
		// TODO Auto-generated constructor stub
	}
	
    @XmlElement(name="TRNNAM")
    private String trnName;
    
    @XmlElement(name="TRNVER")
    private String trnver;
    
    @XmlElement(name="UUID")
    private String uuid;
    
    @XmlElement(name="WH_ID")
    private String warehouseId;
    
    @XmlElement(name="CLIENT_ID")
    private String clientId;
    
    @XmlElement(name="ISO_2_CTRY_NAME")
    private String ctryName;
    
    @XmlElement(name="REQUEST_ID")
    private String requestId;
    
    @XmlElement(name="ROUTE_ID")
    private String routeId;    
}
