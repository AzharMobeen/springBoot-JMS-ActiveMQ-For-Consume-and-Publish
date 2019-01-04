package com.az.task.errors;

import org.springframework.util.ErrorHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListenerErrorHandler implements ErrorHandler{
	
	@Override
	public void handleError(Throwable t) {
		log.warn("\n\tListener is Unable to unmarshal the Message");
        log.error("\n\t"+t.getCause().getMessage());
		
	}

}
