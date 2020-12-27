package com.quizzy.quizzy.repository;

import com.quizzy.quizzy.model.Question;
import com.quizzy.quizzy.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
    List<Question> findByQuiz(Optional<Quiz> quiz);
}