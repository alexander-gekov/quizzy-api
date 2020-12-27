package com.quizzy.quizzy.repository;

import com.quizzy.quizzy.model.Quiz;
import com.quizzy.quizzy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    List<Quiz> findByUser(Optional<User> user);
}
