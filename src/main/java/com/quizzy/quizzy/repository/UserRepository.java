package com.quizzy.quizzy.repository;

import com.quizzy.quizzy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
