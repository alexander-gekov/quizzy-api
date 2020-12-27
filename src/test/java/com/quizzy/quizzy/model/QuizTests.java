package com.quizzy.quizzy.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuizTests {

    @Test
    public void QuizConstructorTest(){
        User user = new User("username","email","password");
        Quiz quiz = new Quiz("name","topic",user);
        assertEquals("name",quiz.getName());
        assertEquals("topic",quiz.getTopic());
    }

    @Test
    public void QuizCreateTest() {
        User user = new User("username","email","password");
        Quiz quiz = new Quiz("name", "topic", user);

        assertEquals("name", quiz.getName());
        assertEquals("topic", quiz.getTopic());
        assertEquals(user, quiz.getUser());
    }

    @Test
    public void GetNameTest() {
        User user = new User("username","email","password");
        Quiz quiz = new Quiz("name", "topic", user);


        assertEquals("name", quiz.getName());
    }

    @Test
    public void SetNameTest() {
        User user = new User("username","email","password");
        Quiz quiz = new Quiz("name", "topic", user);

        quiz.setName("name2");
        assertEquals("name2", quiz.getName());
    }
}
