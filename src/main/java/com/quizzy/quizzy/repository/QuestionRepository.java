package com.quizzy.quizzy.repository;

import com.quizzy.quizzy.model.Question;
import com.quizzy.quizzy.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,String> {
}
