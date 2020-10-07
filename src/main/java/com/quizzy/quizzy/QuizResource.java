package com.quizzy.quizzy;

import com.quizzy.quizzy.model.Quiz;
import com.quizzy.quizzy.model.User;
import com.quizzy.quizzy.repository.QuizRepository;
import com.quizzy.quizzy.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuizResource {
    private final QuizRepository repository;

    QuizResource(QuizRepository repository){
        this.repository = repository;
    }

    @CrossOrigin
    @GetMapping("/quizzes")
    List<Quiz> all(){
        return repository.findAll();
    }

    @CrossOrigin
    @PostMapping("/quizzes")
    Quiz newQuiz(@RequestBody Quiz newQuiz){
        return repository.save(newQuiz);
    }

    @CrossOrigin
    @GetMapping("/quizzes/{id}")
    Quiz one(@PathVariable int id){
        return repository.findById(id).get();
    }

    @CrossOrigin
    @PutMapping("/quizzes/{id}")
    Quiz replaceUser(@RequestBody Quiz newQuiz, @PathVariable int id){

        return repository.findById(id)
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

    @CrossOrigin
    @DeleteMapping("/quizzes/{id}")
    void deleteQuiz(@PathVariable int id){
        repository.deleteById(id);
    }
}
