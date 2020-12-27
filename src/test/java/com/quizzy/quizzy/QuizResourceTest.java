package com.quizzy.quizzy;

import com.quizzy.quizzy.model.Quiz;
import com.quizzy.quizzy.model.User;
import com.quizzy.quizzy.payload.request.QuizRequest;
import com.quizzy.quizzy.repository.QuestionRepository;
import com.quizzy.quizzy.repository.QuizRepository;
import com.quizzy.quizzy.repository.UserRepository;
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

public class QuizResourceTest {

    @Mock
    private QuizRepository mockRepository;
    @Mock
    private QuestionRepository mockQuestionRepository;
    @Mock
    private UserRepository mockUserRepository;

    private QuizResource quizResourceUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        quizResourceUnderTest = new QuizResource(mockRepository, mockQuestionRepository, mockUserRepository);
    }

    @Test
    public void testAll() {
        // Setup
        when(mockRepository.findAll()).thenReturn(List.of(new Quiz()));

        // Run the test
        final List<Quiz> result = quizResourceUnderTest.all();

        // Verify the results
    }

    @Test
    public void testMyCollection() {
        // Setup

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(new User("username", "email", "password"));
        when(mockUserRepository.findById(0)).thenReturn(user);

        when(mockRepository.findByUser(Optional.of(new User("username", "email", "password")))).thenReturn(List.of(new Quiz()));

        // Run the test
        final List<Quiz> result = quizResourceUnderTest.myCollection(0);

        // Verify the results
    }

    @Test
    public void testMyCollection_UserRepositoryReturnsAbsent() {
        // Setup
        when(mockUserRepository.findById(0)).thenReturn(Optional.empty());
        when(mockRepository.findByUser(Optional.of(new User("username", "email", "password")))).thenReturn(List.of(new Quiz()));

        // Run the test
        final List<Quiz> result = quizResourceUnderTest.myCollection(0);

        // Verify the results
    }

    @Test
    public void testNewQuiz() {
        // Setup
        final QuizRequest quizRequest = new QuizRequest();
        quizRequest.setName("name");
        quizRequest.setTopic("topic");
        quizRequest.setUser_id(0);

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(new User("username", "email", "password"));
        when(mockUserRepository.findById(0)).thenReturn(user);

        when(mockRepository.save(any(Quiz.class))).thenReturn(new Quiz());

        // Run the test
        final Quiz result = quizResourceUnderTest.newQuiz(quizRequest);

        // Verify the results
    }

    @Test(expected= NoSuchElementException.class)
    public void testNewQuiz_UserRepositoryReturnsAbsent() {
        // Setup
        final QuizRequest quizRequest = new QuizRequest();
        quizRequest.setName("name");
        quizRequest.setTopic("topic");
        quizRequest.setUser_id(0);

        when(mockUserRepository.findById(0)).thenReturn(Optional.empty());
        when(mockRepository.save(any(Quiz.class))).thenReturn(new Quiz());

        // Run the test
        final Quiz result = quizResourceUnderTest.newQuiz(quizRequest);

        // Verify the results
    }

    @Test
    public void testOne() {
        // Setup
        when(mockRepository.findById(0)).thenReturn(Optional.of(new Quiz()));

        // Run the test
        final Quiz result = quizResourceUnderTest.one(0);

        // Verify the results
    }

    @Test(expected= UserNotFoundException.class)
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
        final QuizRequest newQuiz = new QuizRequest();
        newQuiz.setName("name");
        newQuiz.setTopic("topic");
        newQuiz.setUser_id(0);

        when(mockRepository.findById(0)).thenReturn(Optional.of(new Quiz()));

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(new User("username", "email", "password"));
        when(mockUserRepository.findById(0)).thenReturn(user);

        when(mockRepository.save(any(Quiz.class))).thenReturn(new Quiz());

        // Run the test
        final Quiz result = quizResourceUnderTest.replaceUser(newQuiz, 0);

        // Verify the results
    }

    @Test
    public void testReplaceUser_QuizRepositoryFindByIdReturnsAbsent() {
        // Setup
        final QuizRequest newQuiz = new QuizRequest();
        newQuiz.setName("name");
        newQuiz.setTopic("topic");
        newQuiz.setUser_id(0);

        when(mockRepository.findById(0)).thenReturn(Optional.empty());

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(new User("username", "email", "password"));
        when(mockUserRepository.findById(0)).thenReturn(user);

        when(mockRepository.save(any(Quiz.class))).thenReturn(new Quiz());

        // Run the test
        final Quiz result = quizResourceUnderTest.replaceUser(newQuiz, 0);

        // Verify the results
    }

    @Test(expected= NoSuchElementException.class)
    public void testReplaceUser_UserRepositoryReturnsAbsent() {
        // Setup
        final QuizRequest newQuiz = new QuizRequest();
        newQuiz.setName("name");
        newQuiz.setTopic("topic");
        newQuiz.setUser_id(0);

        when(mockRepository.findById(0)).thenReturn(Optional.of(new Quiz()));
        when(mockUserRepository.findById(0)).thenReturn(Optional.empty());
        when(mockRepository.save(any(Quiz.class))).thenReturn(new Quiz());

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
