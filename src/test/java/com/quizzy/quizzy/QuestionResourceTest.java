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

        when(mockRepository.findAll()).thenReturn(List.of(new Question()));

        final List<Question> result = questionResourceUnderTest.all();

    }

    @Test
    public void testNewQuestion() {

        final QuestionRequest questionRequest = new QuestionRequest();
        questionRequest.setQuestionString("questionString");
        questionRequest.setAnswer1("answer1");
        questionRequest.setAnswer2("answer2");
        questionRequest.setAnswer3("answer3");
        questionRequest.setAnswer4("answer4");
        questionRequest.setQuiz_id(0);
        questionRequest.setCorrectAnswer("correctAnswer");
        questionRequest.setSeconds(0);

        when(mockQuizRepository.findById(0)).thenReturn(Optional.of(new Quiz()));
        when(mockRepository.save(any(Question.class))).thenReturn(new Question());

        final Question result = questionResourceUnderTest.newQuestion(questionRequest);

    }

    @Test(expected= NoSuchElementException.class)
    public void testNewQuestion_QuizRepositoryReturnsAbsent() {

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


        final Question result = questionResourceUnderTest.newQuestion(questionRequest);


    }

    @Test
    public void testOne() {

        when(mockRepository.findById(0)).thenReturn(Optional.of(new Question()));


        final Question result = questionResourceUnderTest.one(0);


    }

    @Test(expected= UserNotFoundException.class)
    public void testOne_QuestionRepositoryReturnsAbsent() {

        when(mockRepository.findById(0)).thenReturn(Optional.empty());


        final Question result = questionResourceUnderTest.one(0);

    }

    @Test
    public void testReplaceQuestion() {

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
        when(mockQuizRepository.findById(0)).thenReturn(Optional.of(new Quiz()));
        when(mockRepository.save(any(Question.class))).thenReturn(new Question());


        final Question result = questionResourceUnderTest.replaceQuestion(questionRequest, 0);


    }

    @Test(expected= NoSuchElementException.class)
    public void testReplaceQuestion_QuestionRepositoryFindByIdReturnsAbsent() {

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
        when(mockQuizRepository.findById(0)).thenReturn(Optional.of(new Quiz()));
        when(mockRepository.save(any(Question.class))).thenReturn(new Question());


        final Question result = questionResourceUnderTest.replaceQuestion(questionRequest, 0);


    }

    @Test(expected= NoSuchElementException.class)
    public void testReplaceQuestion_QuizRepositoryReturnsAbsent() {

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


        final Question result = questionResourceUnderTest.replaceQuestion(questionRequest, 0);


    }

    @Test
    public void testDeleteQuestion() {

        questionResourceUnderTest.deleteQuestion(0);

        verify(mockRepository).deleteById(0);
    }

    @Test
    public void testGetByQuiz() {
        when(mockQuizRepository.findById(0)).thenReturn(Optional.of(new Quiz()));
        when(mockRepository.findByQuiz(Optional.of(new Quiz()))).thenReturn(List.of(new Question()));

        final List<Question> result = questionResourceUnderTest.getByQuiz(0);

    }

    @Test(expected= UserNotFoundException.class)
    public void testGetByQuiz_QuizRepositoryReturnsAbsent() {
        when(mockQuizRepository.findById(0)).thenReturn(Optional.empty());
        when(mockRepository.findByQuiz(Optional.of(new Quiz()))).thenReturn(List.of(new Question()));

        final List<Question> result = questionResourceUnderTest.getByQuiz(0);

    }
}
