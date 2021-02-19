package com.lowe.quiz.models;

import java.util.List;

public class RemoteResponse {
	
	private Long response_code;
	private List<RemoteResponseDetails> results;
	public Long getResponse_code() {
		return response_code;
	}
	public void setResponse_code(Long response_code) {
		this.response_code = response_code;
	}
	public List<RemoteResponseDetails> getResults() {
		return results;
	}
	public void setResults(List<RemoteResponseDetails> results) {
		this.results = results;
	}	
}
