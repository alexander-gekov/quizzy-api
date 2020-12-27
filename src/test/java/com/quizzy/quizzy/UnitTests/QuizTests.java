package com.quizzy.quizzy.UnitTests;

import com.quizzy.quizzy.model.Quiz;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class QuizTests {

    @Test
    public void QuizConstructorTest(){
        Quiz quiz = new Quiz("name","topic");
        assertEquals("name",quiz.getName());
        assertEquals("topic",quiz.getTopic());
    }

    @Test
    public void QuizCreateTest() {
        Quiz quiz = new Quiz("name", "topic", new ArrayList<>());

        assertEquals("name", quiz.getName());
        assertEquals("topic", quiz.getTopic());
        assertEquals(Collections.emptyList(), quiz.getQuestions());
    }

    @Test
    public void GetNameTest() {
        Quiz quiz = new Quiz("name", "topic", new ArrayList<>());


        assertEquals("name", quiz.getName());
    }

    @Test
    public void SetNameTest() {
        Quiz quiz = new Quiz("name", "topic", new ArrayList<>());

        quiz.setName("name2");
        assertEquals("name2", quiz.getName());
    }
}
