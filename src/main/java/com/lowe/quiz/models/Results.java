package com.lowe.quiz.models;

import java.util.List;

public class Results {

	private String type;
	private String difficulty;
	private String question;
	private String correct_answer;
	private List<String> all_answers;
		
	public Results() {
		super();
	}
	public Results(String type, String difficulty, String question, String correct_answer, List<String> all_answers) {
		super();
		this.type = type;
		this.difficulty = difficulty;
		this.question = question;
		this.correct_answer = correct_answer;
		this.all_answers = all_answers;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getCorrect_answer() {
		return correct_answer;
	}
	public void setCorrect_answer(String correct_answer) {
		this.correct_answer = correct_answer;
	}
	public List<String> getAll_answers() {
		return all_answers;
	}
	public void setAll_answers(List<String> all_answers) {
		this.all_answers = all_answers;
	}

	
	
}
