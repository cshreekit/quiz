package com.lowe.quiz.processor;

@FunctionalInterface
public interface ArgsInSupplier<RequestType, ResponseType> {
	
	public ResponseType apply (RequestType requestType);
	
}
