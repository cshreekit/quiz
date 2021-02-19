package com.lowe.quiz.models;

import java.util.List;

public class Response {
	
	List<Quiz> quiz;
	
	public Response() {
		super();
	}

	public Response(List<Quiz> quiz) {
		super();
		this.quiz = quiz;
	}

	public List<Quiz> getQuiz() {
		return quiz;
	}

	public void setQuiz(List<Quiz> quiz) {
		this.quiz = quiz;
	}
	
	
}
