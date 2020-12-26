package com.quizzy.quizzy;

import com.quizzy.quizzy.model.Question;
import com.quizzy.quizzy.model.Quiz;
import com.quizzy.quizzy.repository.QuestionRepository;
import com.quizzy.quizzy.repository.QuizRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class QuizResource {
    private final QuizRepository repository;
    private final QuestionRepository questionRepository;

    QuizResource(QuizRepository repository, QuestionRepository questionRepository) {
        this.repository = repository;
        this.questionRepository = questionRepository;
    }

    @CrossOrigin
    @GetMapping("/quizzes")
    public List<Quiz> all() {
        return repository.findAll();
    }

    @PostMapping("/quizzes")
    public Quiz newQuiz(@RequestBody Quiz newQuiz) {
        return repository.save(newQuiz);
    }

    @GetMapping("quizzes/{id}")
    public Quiz one(@PathVariable int id) {
        Optional<Quiz> quiz = repository.findById(id);
        if (quiz.isEmpty())
            throw new UserNotFoundException("id-" + id);
        return quiz.get();
    }

    @PutMapping("quizzes/{id}")
    public Quiz replaceUser(@RequestBody Quiz newQuiz, @PathVariable int id) {

        return repository.findById(id)
                .map(quiz -> {
                    quiz.setName(newQuiz.getName());
                    quiz.setTopic(newQuiz.getTopic());
                    return repository.save(quiz);
                })
                .orElseGet(() -> {
                    newQuiz.setId(id);
                    return repository.save(newQuiz);
                });
    }

    @DeleteMapping("quizzes/{id}")
    public void deleteQuiz(@PathVariable int id) {
        repository.deleteById(id);
    }

}
