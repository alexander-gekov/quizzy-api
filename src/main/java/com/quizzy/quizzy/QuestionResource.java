package com.quizzy.quizzy;

import com.quizzy.quizzy.model.Question;
import com.quizzy.quizzy.repository.QuestionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class QuestionResource {
    private final QuestionRepository repository;

    QuestionResource(QuestionRepository repository){
        this.repository = repository;
    }

    @GetMapping("/questions")
    public List<Question> all(){
        return repository.findAll();
    }

    @PostMapping("/questions")
    public Question newQuestion(@RequestBody Question newQuestion){
        return repository.save(newQuestion);
    }

    @GetMapping("/questions/{id}")
    public Question one(@PathVariable int id){
        Optional<Question> question = repository.findById(id);
        if (question.isEmpty())
            throw new UserNotFoundException("id-" + id);
        return question.get();
    }

    @PutMapping("/question/{id}")
    public Question replaceQuestion(@RequestBody Question newQuestion, @PathVariable int id){

        return repository.findById(id)
                .map(question -> {
                    question.setQuestion(newQuestion.getQuestion());
//                    question.setAnswer1(newQuestion.getAnswer1());
//                    question.setAnswer2(newQuestion.getAnswer2());
//                    question.setAnswer3(newQuestion.getAnswer3());
//                    question.setAnswer4(newQuestion.getAnswer4());
                    return repository.save(question);
                })
                .orElseGet(()->{
                    newQuestion.setId(id);
                    return repository.save(newQuestion);
                });
    }

    @DeleteMapping("/questions/{id}")
    public void deleteQuestion(@PathVariable int id){
        repository.deleteById(id);
    }
}
