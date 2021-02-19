package com.lowe.quiz.models;

import java.util.List;

public class Quiz {
	
	private String category;
	private List<Results> results;

	public Quiz() {
		super();
	}

	public Quiz(String category, List<Results> results) {
		super();
		this.category = category;
		this.results = results;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Results> getResults() {
		return results;
	}

	public void setResults(List<Results> results) {
		this.results = results;
	}

	
	
	
}
