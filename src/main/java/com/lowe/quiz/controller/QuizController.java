package com.lowe.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lowe.quiz.models.Response;
import com.lowe.quiz.processor.QuizProcessor;

@RestController
public class QuizController {

	@Autowired
	QuizProcessor processor;
	
	@GetMapping("/coding/exercise/quiz")
	public Response getQuiz(@RequestParam(value = "questions", defaultValue = "5") int numQuestions,
							@RequestParam(value = "categories", defaultValue = "11,12") String commaSeparatedCategories) {
		return processor.getQuiz(numQuestions, commaSeparatedCategories);
	}
	
}
