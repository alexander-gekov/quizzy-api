package com.quizzy.quizzy;

import com.quizzy.quizzy.model.Question;
import com.quizzy.quizzy.model.Quiz;
import com.quizzy.quizzy.payload.request.QuestionRequest;
import com.quizzy.quizzy.repository.QuestionRepository;
import com.quizzy.quizzy.repository.QuizRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class QuestionResourceTest {

    @Mock
    private QuestionRepository mockRepository;
    @Mock
    private QuizRepository mockQuizRepository;

    private QuestionResource questionResourceUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        questionResourceUnderTest = new QuestionResource(mockRepository, mockQuizRepository);
    }

    @Test
    public void testAll() {
        // Setup
        when(mockRepository.findAll()).thenReturn(List.of(new Question()));

        // Run the test
        final List<Question> result = questionResourceUnderTest.all();

        // Verify the results
    }

    @Test
    public void testNewQuestion() {
        // Setup
        final QuestionRequest questionRequest = new QuestionRequest();
        questionRequest.setQuestionString("questionString");
        questionRequest.setAnswer1("answer1");
        questionRequest.setAnswer2("answer2");
        questionRequest.setAnswer3("answer3");
        questionRequest.setAnswer4("answer4");
        questionRequest.setQuiz_id(0);
        questionRequest.setCorrectAnswer("correctAnswer");
        questionRequest.setSeconds(0);

        // Configure QuizRepository.findById(...).
        final Optional<Quiz> quiz = Optional.of(new Quiz("name", "topic", List.of(new Question())));
        when(mockQuizRepository.findById(0)).thenReturn(quiz);

        when(mockRepository.save(any(Question.class))).thenReturn(new Question());

        // Run the test
        final Question result = questionResourceUnderTest.newQuestion(questionRequest);

        // Verify the results
    }

    @Test(expected = NoSuchElementException.class)
    public void testNewQuestion_QuizRepositoryReturnsAbsent() {
        // Setup
        final QuestionRequest questionRequest = new QuestionRequest();
        questionRequest.setQuestionString("questionString");
        questionRequest.setAnswer1("answer1");
        questionRequest.setAnswer2("answer2");
        questionRequest.setAnswer3("answer3");
        questionRequest.setAnswer4("answer4");
        questionRequest.setQuiz_id(0);
        questionRequest.setCorrectAnswer("correctAnswer");
        questionRequest.setSeconds(0);

        when(mockQuizRepository.findById(0)).thenReturn(Optional.empty());
        when(mockRepository.save(any(Question.class))).thenReturn(new Question());

        // Run the test
        final Question result = questionResourceUnderTest.newQuestion(questionRequest);

        // Verify the results
    }

    @Test
    public void testOne() {
        // Setup
        when(mockRepository.findById(0)).thenReturn(Optional.of(new Question()));

        // Run the test
        final Question result = questionResourceUnderTest.one(0);

        // Verify the results
    }

    @Test(expected = UserNotFoundException.class)
    public void testOne_QuestionRepositoryReturnsAbsent() {
        // Setup
        when(mockRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Question result = questionResourceUnderTest.one(0);

        // Verify the results
    }

    @Test
    public void testReplaceQuestion() {
        // Setup
        final QuestionRequest questionRequest = new QuestionRequest();
        questionRequest.setQuestionString("questionString");
        questionRequest.setAnswer1("answer1");
        questionRequest.setAnswer2("answer2");
        questionRequest.setAnswer3("answer3");
        questionRequest.setAnswer4("answer4");
        questionRequest.setQuiz_id(0);
        questionRequest.setCorrectAnswer("correctAnswer");
        questionRequest.setSeconds(0);

        when(mockRepository.findById(0)).thenReturn(Optional.of(new Question()));

        // Configure QuizRepository.findById(...).
        final Optional<Quiz> quiz = Optional.of(new Quiz("name", "topic", List.of(new Question())));
        when(mockQuizRepository.findById(0)).thenReturn(quiz);

        when(mockRepository.save(any(Question.class))).thenReturn(new Question());

        // Run the test
        final Question result = questionResourceUnderTest.replaceQuestion(questionRequest, 0);

        // Verify the results
    }

    @Test(expected = NoSuchElementException.class)
    public void testReplaceQuestion_QuestionRepositoryFindByIdReturnsAbsent() {
        // Setup
        final QuestionRequest questionRequest = new QuestionRequest();
        questionRequest.setQuestionString("questionString");
        questionRequest.setAnswer1("answer1");
        questionRequest.setAnswer2("answer2");
        questionRequest.setAnswer3("answer3");
        questionRequest.setAnswer4("answer4");
        questionRequest.setQuiz_id(0);
        questionRequest.setCorrectAnswer("correctAnswer");
        questionRequest.setSeconds(0);

        when(mockRepository.findById(0)).thenReturn(Optional.empty());

        // Configure QuizRepository.findById(...).
        final Optional<Quiz> quiz = Optional.of(new Quiz("name", "topic", List.of(new Question())));
        when(mockQuizRepository.findById(0)).thenReturn(quiz);

        when(mockRepository.save(any(Question.class))).thenReturn(new Question());

        // Run the test
        final Question result = questionResourceUnderTest.replaceQuestion(questionRequest, 0);

        // Verify the results
    }

    @Test(expected = NoSuchElementException.class)
    public void testReplaceQuestion_QuizRepositoryReturnsAbsent() {
        // Setup
        final QuestionRequest questionRequest = new QuestionRequest();
        questionRequest.setQuestionString("questionString");
        questionRequest.setAnswer1("answer1");
        questionRequest.setAnswer2("answer2");
        questionRequest.setAnswer3("answer3");
        questionRequest.setAnswer4("answer4");
        questionRequest.setQuiz_id(0);
        questionRequest.setCorrectAnswer("correctAnswer");
        questionRequest.setSeconds(0);

        when(mockRepository.findById(0)).thenReturn(Optional.of(new Question()));
        when(mockQuizRepository.findById(0)).thenReturn(Optional.empty());
        when(mockRepository.save(any(Question.class))).thenReturn(new Question());

        // Run the test
        final Question result = questionResourceUnderTest.replaceQuestion(questionRequest, 0);

        // Verify the results
    }

    @Test
    public void testDeleteQuestion() {
        // Setup

        // Run the test
        questionResourceUnderTest.deleteQuestion(0);

        // Verify the results
        verify(mockRepository).deleteById(0);
    }

    @Test
    public void testGetByQuiz() {
        // Setup

        // Configure QuizRepository.findById(...).
        final Optional<Quiz> quiz = Optional.of(new Quiz("name", "topic", List.of(new Question())));
        when(mockQuizRepository.findById(0)).thenReturn(quiz);

        when(mockRepository.findByQuiz(Optional.of(new Quiz("name", "topic", List.of(new Question()))))).thenReturn(List.of(new Question()));

        // Run the test
        final List<Question> result = questionResourceUnderTest.getByQuiz(0);

        // Verify the results
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetByQuiz_QuizRepositoryReturnsAbsent() {
        // Setup
        when(mockQuizRepository.findById(0)).thenReturn(Optional.empty());
        when(mockRepository.findByQuiz(Optional.of(new Quiz("name", "topic", List.of(new Question()))))).thenReturn(List.of(new Question()));

        // Run the test
        final List<Question> result = questionResourceUnderTest.getByQuiz(0);

        // Verify the results
    }
}
