package com.quizzy.quizzy;

import com.quizzy.quizzy.model.Question;
import com.quizzy.quizzy.model.Quiz;
import com.quizzy.quizzy.repository.QuestionRepository;
import com.quizzy.quizzy.repository.QuizRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class QuizResourceTest {

    @Mock
    private QuizRepository mockRepository;
    @Mock
    private QuestionRepository mockQuestionRepository;

    private QuizResource quizResourceUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        quizResourceUnderTest = new QuizResource(mockRepository, mockQuestionRepository);
    }

    @Test
    public void testAll() {
        // Setup

        // Configure QuizRepository.findAll(...).
        final List<Quiz> quizzes = List.of(new Quiz("name", "topic", List.of(new Question())));
        when(mockRepository.findAll()).thenReturn(quizzes);

        // Run the test
        final List<Quiz> result = quizResourceUnderTest.all();

        // Verify the results
    }

    @Test
    public void testNewQuiz() {
        // Setup
        final Quiz newQuiz = new Quiz("name", "topic", List.of(new Question()));

        // Configure QuizRepository.save(...).
        final Quiz quiz = new Quiz("name", "topic", List.of(new Question()));
        when(mockRepository.save(any(Quiz.class))).thenReturn(quiz);

        // Run the test
        final Quiz result = quizResourceUnderTest.newQuiz(newQuiz);

        // Verify the results
    }

    @Test
    public void testOne() {
        // Setup

        // Configure QuizRepository.findById(...).
        final Optional<Quiz> quiz = Optional.of(new Quiz("name", "topic", List.of(new Question())));
        when(mockRepository.findById(0)).thenReturn(quiz);

        // Run the test
        final Quiz result = quizResourceUnderTest.one(0);

        // Verify the results
    }

    @Test(expected = UserNotFoundException.class)
    public void testOne_QuizRepositoryReturnsAbsent() {
        // Setup
        when(mockRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Quiz result = quizResourceUnderTest.one(0);

        // Verify the results
    }

    @Test
    public void testReplaceUser() {
        // Setup
        final Quiz newQuiz = new Quiz("name", "topic", List.of(new Question()));

        // Configure QuizRepository.findById(...).
        final Optional<Quiz> quiz = Optional.of(new Quiz("name", "topic", List.of(new Question())));
        when(mockRepository.findById(0)).thenReturn(quiz);

        // Configure QuizRepository.save(...).
        final Quiz quiz1 = new Quiz("name", "topic", List.of(new Question()));
        when(mockRepository.save(any(Quiz.class))).thenReturn(quiz1);

        // Run the test
        final Quiz result = quizResourceUnderTest.replaceUser(newQuiz, 0);

        // Verify the results
    }

    @Test
    public void testReplaceUser_QuizRepositoryFindByIdReturnsAbsent() {
        // Setup
        final Quiz newQuiz = new Quiz("name", "topic", List.of(new Question()));
        when(mockRepository.findById(0)).thenReturn(Optional.empty());

        // Configure QuizRepository.save(...).
        final Quiz quiz = new Quiz("name", "topic", List.of(new Question()));
        when(mockRepository.save(any(Quiz.class))).thenReturn(quiz);

        // Run the test
        final Quiz result = quizResourceUnderTest.replaceUser(newQuiz, 0);

        // Verify the results
    }

    @Test
    public void testDeleteQuiz() {
        // Setup

        // Run the test
        quizResourceUnderTest.deleteQuiz(0);

        // Verify the results
        verify(mockRepository).deleteById(0);
    }
}
