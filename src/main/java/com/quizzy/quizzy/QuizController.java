package com.quizzy.quizzy;

import com.quizzy.quizzy.model.Quiz;
import com.quizzy.quizzy.model.User;
import com.quizzy.quizzy.repository.QuizRepository;
import com.quizzy.quizzy.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuizController {
    private final QuizRepository repository;

    QuizController(QuizRepository repository){
        this.repository = repository;
    }

    @GetMapping("/quizzes")
    List<Quiz> all(){
        return repository.findAll();
    }

    @PostMapping("/quizzes")
    Quiz newQuiz(@RequestBody Quiz newQuiz){
        return repository.save(newQuiz);
    }

    @GetMapping("/quizzes/{id}")
    Quiz one(@PathVariable String id){
        return repository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/quizzes/{id}")
    Quiz replaceUser(@RequestBody Quiz newQuiz, @PathVariable int id){

        return repository.findById(String.valueOf(id))
                .map(quiz -> {
                    quiz.setName(newQuiz.getName());
                    quiz.setTopic(newQuiz.getTopic());
                    return repository.save(quiz);
                })
                .orElseGet(()->{
                    newQuiz.setId(id);
                    return repository.save(newQuiz);
                });
    }

    @DeleteMapping("/quizzes/{id}")
    void deleteQuiz(@PathVariable String id){
        repository.deleteById(id);
    }
}
