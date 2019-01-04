# SpringBoot-JMS with ActiveMQ

It's a simple Spring-Boot Microservice for Consuming messages from ActiveMQ Queues and Publish it to ActiveMQ Topics. I'm using MarshallingMessageConverter with Jaxb2Marshaller for both consuming and publishing XML message.

I have also created Unit test and EmbeddedActiveMQ integration test.


* Run ActiveMQ.
* Default url:  http://localhost:8161/
* Login by default user **(admin/admin)**
* Create Queue with name **source** in ActiveMQ-Queues. 
* Please set activeMQ url, inbound.endpoing and outbound.endpoint in **properties.yml**

```
spring:
  activemq:
    broker-url: tcp://localhost:61616
inbound:
  endpoint: source
outbound:
  endpoint: destination
  
```
* Run *run.bat* file or execute *mvn clean install spring-boot:run*
* Now send message from created **source** queue. (past bellow sample XML in message body)

```
<UC_STOCK_LEVEL_IFD>
	<CTRL_SEG>
		<TRNNAM>UU_SSSS_LEVEL</TRNNAM>
		<TRNVER>20180100</TRNVER>
		<UUID>0de01919-81eb-4cc7-a51d-15f6085fc1a4</UUID>
		<WH_ID>WHHHH</WH_ID>
		<CLIENT_ID>CLI</CLIENT_ID>
		<ISO_2_CTRY_NAME>xxxx</ISO_2_CTRY_NAME>
		<REQUEST_ID>bcccc8-5a07-4hi6-8yyy-8290d3ccfb51</REQUEST_ID>
		<ROUTE_ID>6543</ROUTE_ID>
	</CTRL_SEG>
</UC_STOCK_LEVEL_IFD>
```

* Check task.log file or console for details.
