package com.az.task.config;

import java.util.HashMap;
import java.util.Map;

import javax.jms.ConnectionFactory;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class JmsConfig {

	@Bean
	public MarshallingMessageConverter createMarshallingMessageConverter(final Jaxb2Marshaller jaxb2Marshaller) {
		
		return new MarshallingMessageConverter(jaxb2Marshaller);
	}
	
	@Bean
	public Jaxb2Marshaller createJaxb2Marshaller(@Value("${jaxb.package}") final String packageToScane) {		
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();		
		jaxb2Marshaller.setPackagesToScan(packageToScane);
		Map<String, Object> properties = new HashMap<>();
		properties.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxb2Marshaller.setMarshallerProperties(properties);		
		return jaxb2Marshaller;
	}
	
	@Bean
	public JmsTemplate jmsTemplate(final MarshallingMessageConverter marshallingMessageConverter,ConnectionFactory connectionFactory){
	    JmsTemplate template = new JmsTemplate();
	    template.setConnectionFactory(connectionFactory);	    
	    template.setMessageConverter(marshallingMessageConverter);	    
	    template.setPubSubDomain(true);
	    return template;
	}
}
