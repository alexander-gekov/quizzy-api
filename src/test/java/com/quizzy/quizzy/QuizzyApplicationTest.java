package com.quizzy.quizzy;


import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuizzyApplicationTest {

    @Autowired
    private UserResource userController;
    @Autowired
    private QuizResource quizController;

    @Test
    public void contextLoads() throws Exception{
        assertThat(userController).isNotNull();
        assertThat(quizController).isNotNull();
    }
}
