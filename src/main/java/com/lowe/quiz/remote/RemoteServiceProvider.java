package com.lowe.quiz.remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lowe.quiz.models.RemoteResponse;

@Service
public class RemoteServiceProvider {

	@Autowired
	RestTemplate restTemplate;
		
	private final String REMOTE_BASE_URL = "https://opentdb.com/api.php";
	public RemoteResponse getQuestions(int numOfQuestions, String category) {
			
		 ResponseEntity<RemoteResponse> response =  restTemplate
				 .exchange(
						 REMOTE_BASE_URL+"?amount="+numOfQuestions+"&category="+category,
						 HttpMethod.GET,
						 null,
						 RemoteResponse.class
						 );
		 return response.getBody();
	}
}
