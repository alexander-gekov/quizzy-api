package com.quizzy.quizzy;

import com.quizzy.quizzy.model.Question;
import com.quizzy.quizzy.model.Quiz;
import com.quizzy.quizzy.payload.request.QuestionRequest;
import com.quizzy.quizzy.repository.QuestionRepository;
import com.quizzy.quizzy.repository.QuizRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class QuestionResource {
    private final QuestionRepository repository;
    private final QuizRepository quizRepository;

    QuestionResource(QuestionRepository repository, QuizRepository quizRepository) {
        this.repository = repository;
        this.quizRepository = quizRepository;
    }

    @CrossOrigin
    @GetMapping("/questions")
    public List<Question> all() {
        return repository.findAll();
    }

    @CrossOrigin
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
        newQuestion.setSeconds(questionRequest.getSeconds());
        Optional<Quiz> quiz = quizRepository.findById(questionRequest.getQuiz_id());
        if (quiz.isEmpty())
            throw new NoSuchElementException();
        newQuestion.setQuiz(quiz.get());
        repository.save(newQuestion);
        return newQuestion;
    }

    @CrossOrigin
    @GetMapping("/questions/{id}")
    public Question one(@PathVariable int id) {
        Optional<Question> question = repository.findById(id);
        if (question.isEmpty())
            throw new UserNotFoundException("id-" + id);
        return question.get();
    }

    @CrossOrigin
    @PutMapping("/questions/{id}")
    public Question replaceQuestion(@RequestBody QuestionRequest questionRequest, @PathVariable int id) {
        Optional<Question> optionalQuestion = repository.findById(id);
        if(optionalQuestion.isPresent()){
            optionalQuestion.get().setQuestionString(questionRequest.getQuestionString());
            optionalQuestion.get().setAnswer1(questionRequest.getAnswer1());
            optionalQuestion.get().setAnswer2(questionRequest.getAnswer2());
            optionalQuestion.get().setAnswer3(questionRequest.getAnswer3());
            optionalQuestion.get().setAnswer4(questionRequest.getAnswer4());
            optionalQuestion.get().setCorrectAnswer(questionRequest.getCorrectAnswer());
            optionalQuestion.get().setSeconds(questionRequest.getSeconds());
            Optional<Quiz> quiz = quizRepository.findById(questionRequest.getQuiz_id());
            if (quiz.isEmpty())
                throw new NoSuchElementException();
            optionalQuestion.get().setQuiz(quiz.get());
            return repository.save(optionalQuestion.get());
        }
        else{
            throw new NoSuchElementException();
        }
    }

    @CrossOrigin
    @DeleteMapping("/questions/{id}")
    public void deleteQuestion(@PathVariable int id) {
        repository.deleteById(id);
    }

    @CrossOrigin
    @GetMapping("/questions/quiz/{id}")
    public List<Question> getByQuiz(@PathVariable int id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if (quiz.isEmpty())
            throw new UserNotFoundException("id-" + id);
        return repository.findByQuiz(quiz);
    }
}
