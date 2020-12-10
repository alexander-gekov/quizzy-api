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
    public void testQuizCreate() {
        Quiz quiz = new Quiz("name", "topic", new ArrayList<>());

        assertEquals("name", quiz.getName());
        assertEquals("topic", quiz.getTopic());
        assertEquals(Collections.emptyList(), quiz.getQuestions());
    }

    @Test
    public void testGetName() {
        Quiz quiz = new Quiz("name", "topic", new ArrayList<>());


        assertEquals("name", quiz.getName());
    }

    @Test
    public void testSetName() {
        Quiz quiz = new Quiz("name", "topic", new ArrayList<>());

        quiz.setName("name2");
        assertEquals("name2", quiz.getName());
    }
}
