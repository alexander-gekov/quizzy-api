package com.quizzy.quizzy;

import com.quizzy.quizzy.model.Quiz;
import com.quizzy.quizzy.model.User;
import com.quizzy.quizzy.payload.request.QuizRequest;
import com.quizzy.quizzy.repository.QuestionRepository;
import com.quizzy.quizzy.repository.QuizRepository;
import com.quizzy.quizzy.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class QuizResource {
    private final QuizRepository repository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    QuizResource(QuizRepository repository, QuestionRepository questionRepository,UserRepository userRepository) {
        this.repository = repository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/quizzes")
    public List<Quiz> all() {
        return repository.findAll();
    }

    @CrossOrigin
    @GetMapping("/quizzes/user/{id}")
    public List<Quiz> myCollection(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        return repository.findByUser(user);
    }

    @CrossOrigin
    @PostMapping("/quizzes")
    public Quiz newQuiz(@RequestBody QuizRequest quizRequest) {
        Quiz newQuiz = new Quiz();
        newQuiz.setName(quizRequest.getName());
        newQuiz.setTopic(quizRequest.getTopic());
        newQuiz.setQuestions(new ArrayList<>());
        Optional<User> user = userRepository.findById(quizRequest.getUser_id());
        if (user.isEmpty())
            throw new NoSuchElementException();
        newQuiz.setUser(user.get());
        repository.save(newQuiz);
        return newQuiz;
    }

    @GetMapping("quizzes/{id}")
    public Quiz one(@PathVariable int id) {
        Optional<Quiz> quiz = repository.findById(id);
        if (quiz.isEmpty())
            throw new UserNotFoundException("id-" + id);
        return quiz.get();
    }
    
    @PutMapping("quizzes/{id}")
    public Quiz replaceUser(@RequestBody QuizRequest newQuiz, @PathVariable int id) {

        return repository.findById(id)
                .map(quiz -> {
                    quiz.setName(newQuiz.getName());
                    quiz.setTopic(newQuiz.getTopic());
                    Optional<User> user = userRepository.findById(newQuiz.getUser_id());
                    if (user.isEmpty())
                        throw new NoSuchElementException();
                    quiz.setUser(user.get());
                    return repository.save(quiz);
                })
                .orElseGet(() -> {
                    Quiz quiz = new Quiz();
                    quiz.setName(newQuiz.getName());
                    quiz.setTopic(newQuiz.getTopic());
                    quiz.setQuestions(new ArrayList<>());
                    Optional<User> user = userRepository.findById(newQuiz.getUser_id());
                    if (user.isEmpty())
                        throw new NoSuchElementException();
                    quiz.setUser(user.get());
                    quiz.setId(id);
                    return repository.save(quiz);
                });
    }

    @DeleteMapping("quizzes/{id}")
    public void deleteQuiz(@PathVariable int id) {
        repository.deleteById(id);
    }
}
