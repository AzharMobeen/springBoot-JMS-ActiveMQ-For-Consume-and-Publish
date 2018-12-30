package com.az.task.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CTRL_SEG")
public class CtrlSeg {

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
