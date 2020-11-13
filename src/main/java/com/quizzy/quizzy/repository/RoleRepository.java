package com.quizzy.quizzy.repository;

import com.quizzy.quizzy.model.ERole;
import com.quizzy.quizzy.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(ERole name);
}
