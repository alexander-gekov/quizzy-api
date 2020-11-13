package com.quizzy.quizzy;

import com.quizzy.quizzy.model.Quiz;
import com.quizzy.quizzy.repository.QuizRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600000)
@RestController
@RequestMapping("/api/quizzes")
public class QuizResource {
    private final QuizRepository repository;

    QuizResource(QuizRepository repository){
        this.repository = repository;
    }

    @CrossOrigin
    @GetMapping("/")
    public List<Quiz> all(){
        return repository.findAll();
    }

    @PostMapping("/")
    public Quiz newQuiz(@RequestBody Quiz newQuiz){
        return repository.save(newQuiz);
    }

    @GetMapping("/{id}")
    public Quiz one(@PathVariable int id){
        Optional<Quiz> quiz = repository.findById(id);
        if (quiz.isEmpty())
            throw new UserNotFoundException("id-" + id);
        return quiz.get();
    }

    @PutMapping("/{id}")
    public Quiz replaceUser(@RequestBody Quiz newQuiz, @PathVariable int id){

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

    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable int id){
        repository.deleteById(id);
    }
}
