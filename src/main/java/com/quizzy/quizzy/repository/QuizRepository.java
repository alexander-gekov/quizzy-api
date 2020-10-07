package com.quizzy.quizzy.repository;

import com.quizzy.quizzy.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
