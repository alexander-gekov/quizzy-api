package com.quizzy.quizzy;

import com.quizzy.quizzy.model.Question;
import com.quizzy.quizzy.model.Quiz;
import com.quizzy.quizzy.repository.QuestionRepository;
import com.quizzy.quizzy.repository.QuizRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {
    private final QuestionRepository repository;

    QuestionController(QuestionRepository repository){
        this.repository = repository;
    }

    @GetMapping("/questions")
    List<Question> all(){
        return repository.findAll();
    }

    @PostMapping("/questions")
    Question newQuestion(@RequestBody Question newQuestion){
        return repository.save(newQuestion);
    }

    @GetMapping("/questions/{id}")
    Question one(@PathVariable String id){
        return repository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/question/{id}")
    Question replaceQuestion(@RequestBody Question newQuestion, @PathVariable int id){

        return repository.findById(String.valueOf(id))
                .map(question -> {
                    question.setQuestion(newQuestion.getQuestion());
                    question.setAnswer1(newQuestion.getAnswer1());
                    question.setAnswer2(newQuestion.getAnswer2());
                    question.setAnswer3(newQuestion.getAnswer3());
                    question.setAnswer4(newQuestion.getAnswer4());
                    return repository.save(question);
                })
                .orElseGet(()->{
                    newQuestion.setId(id);
                    return repository.save(newQuestion);
                });
    }

    @DeleteMapping("/questions/{id}")
    void deleteQuestion(@PathVariable String id){
        repository.deleteById(id);
    }
}
