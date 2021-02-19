package com.lowe.quiz.processor;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lowe.quiz.models.Quiz;
import com.lowe.quiz.models.RemoteResponse;
import com.lowe.quiz.models.RemoteResponseDetails;
import com.lowe.quiz.models.Response;
import com.lowe.quiz.models.Results;
import com.lowe.quiz.remote.RemoteServiceProvider;

@Component
public class QuizProcessor {

	private final ExecutorService executorService = Executors.newFixedThreadPool(5);

	ArgsInSupplier<RemoteResponseDetails, Results> resultSupplier = x -> {
		List<String> all_answers = new ArrayList<String>();
		all_answers.add(x.getCorrect_answer());
		all_answers.addAll(x.getIncorrect_answers());
		return new Results(x.getType(), x.getDifficulty(), x.getQuestion(), x.getCorrect_answer(), all_answers);
	};

	@Autowired
	RemoteServiceProvider serviceProvider;

	private List<RemoteResponseDetails> fetch(final int size, final List<String> categories) {

		List<RemoteResponseDetails> responseDetailsList = new ArrayList<>();

		try {
			List<Callable<RemoteResponse>> callables = categories.stream().map(c -> new Callable<RemoteResponse>() {
				@Override
				public RemoteResponse call() throws Exception {
					return serviceProvider.getQuestions(size, c);
				}
			}).collect(Collectors.toList());

			List<Future<RemoteResponse>> futures = executorService.invokeAll(callables);
			for (Future<RemoteResponse> fr : futures) {
				RemoteResponse temp = fr.get();
				if (temp != null && temp.getResults() != null) {
					responseDetailsList.addAll(temp.getResults());
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseDetailsList;
	}

	public Response getQuiz(int size, String commaSeparatedCategories) {
		RemoteResponse response = new RemoteResponse();
		response.setResults(new ArrayList<>());
		Map<String, List<Results>> map = fetch(size, Arrays.asList(commaSeparatedCategories.split(","))).stream()
				.collect(Collectors.groupingBy(RemoteResponseDetails::getCategory,
						Collectors.mapping(m -> resultSupplier.apply(m), Collectors.toList())));
		List<Quiz> quiz = new ArrayList<Quiz>();
		map.keySet().forEach(key -> {
			quiz.add(new Quiz(key, map.get(key)));
		});
		return new Response(quiz);

	}
}
