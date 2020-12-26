package com.quizzy.quizzy;

import com.quizzy.quizzy.model.Question;
import com.quizzy.quizzy.model.Quiz;
import com.quizzy.quizzy.payload.request.QuestionRequest;
import com.quizzy.quizzy.payload.response.QuestionResponse;
import com.quizzy.quizzy.repository.QuestionRepository;
import com.quizzy.quizzy.repository.QuizRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class QuestionResource {
    private final QuestionRepository repository;
    private final QuizRepository quizRepository;

    QuestionResource(QuestionRepository repository, QuizRepository quizRepository) {
        this.repository = repository;
        this.quizRepository = quizRepository;
    }

    @GetMapping("/questions")
    public List<Question> all() {
        return repository.findAll();
    }

    @PostMapping("/questions")
    @PreAuthorize("hasRole('USER')")
    public Question newQuestion(@RequestBody QuestionRequest questionRequest) {
        Question newQuestion = new Question();
        newQuestion.setQuestionString(questionRequest.getQuestionString());
        newQuestion.setAnswer1(questionRequest.getAnswer1());
        newQuestion.setAnswer2(questionRequest.getAnswer2());
        newQuestion.setAnswer3(questionRequest.getAnswer3());
        newQuestion.setAnswer4(questionRequest.getAnswer4());
        newQuestion.setCorrectAnswer(questionRequest.getCorrectAnswer());
        Optional<Quiz> quiz = quizRepository.findById(questionRequest.getQuiz_id());
        if (quiz.isEmpty())
            throw new NoSuchElementException();
        newQuestion.setQuiz(quiz.get());
        repository.save(newQuestion);
        return newQuestion;
    }

    @GetMapping("/questions/{id}")
    public Question one(@PathVariable int id) {
        Optional<Question> question = repository.findById(id);
        if (question.isEmpty())
            throw new UserNotFoundException("id-" + id);
        return question.get();
    }

    @PutMapping("/question/{id}")
    public Question replaceQuestion(@RequestBody Question newQuestion, @PathVariable int id) {

        return repository.findById(id)
                .map(question -> {
                    question.setQuestionString(newQuestion.getQuestionString());
                    question.setAnswer1(newQuestion.getAnswer1());
                    question.setAnswer2(newQuestion.getAnswer2());
                    question.setAnswer3(newQuestion.getAnswer3());
                    question.setAnswer4(newQuestion.getAnswer4());
                    question.setQuiz(newQuestion.getQuiz());
                    return repository.save(question);
                })
                .orElseGet(() -> {
                    newQuestion.setId(id);
                    return repository.save(newQuestion);
                });
    }

    @DeleteMapping("/questions/{id}")
    public void deleteQuestion(@PathVariable int id) {
        repository.deleteById(id);
    }

    @GetMapping("/questions/quiz/{id}")
    public List<Question> getByQuiz(@PathVariable int id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if (quiz.isEmpty())
            throw new UserNotFoundException("id-" + id);
        return repository.findByQuiz(quiz);
    }
}
