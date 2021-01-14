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
        when(mockRepository.findAll()).thenReturn(List.of(new Quiz()));

        final List<Quiz> result = quizResourceUnderTest.all();

    }

    @Test
    public void testMyCollection() {

        final Optional<User> user = Optional.of(new User("username", "email", "password"));
        when(mockUserRepository.findById(0)).thenReturn(user);

        when(mockRepository.findByUser(Optional.of(new User("username", "email", "password")))).thenReturn(List.of(new Quiz()));
        final List<Quiz> result = quizResourceUnderTest.myCollection(0);

    }

    @Test
    public void testMyCollection_UserRepositoryReturnsAbsent() {
        when(mockUserRepository.findById(0)).thenReturn(Optional.empty());
        when(mockRepository.findByUser(Optional.of(new User("username", "email", "password")))).thenReturn(List.of(new Quiz()));

        final List<Quiz> result = quizResourceUnderTest.myCollection(0);

    }

    @Test
    public void testNewQuiz() {
        final QuizRequest quizRequest = new QuizRequest();
        quizRequest.setName("name");
        quizRequest.setTopic("topic");
        quizRequest.setUser_id(0);

        final Optional<User> user = Optional.of(new User("username", "email", "password"));
        when(mockUserRepository.findById(0)).thenReturn(user);

        when(mockRepository.save(any(Quiz.class))).thenReturn(new Quiz());

        final Quiz result = quizResourceUnderTest.newQuiz(quizRequest);

    }

    @Test(expected= NoSuchElementException.class)
    public void testNewQuiz_UserRepositoryReturnsAbsent() {
        final QuizRequest quizRequest = new QuizRequest();
        quizRequest.setName("name");
        quizRequest.setTopic("topic");
        quizRequest.setUser_id(0);

        when(mockUserRepository.findById(0)).thenReturn(Optional.empty());
        when(mockRepository.save(any(Quiz.class))).thenReturn(new Quiz());

        final Quiz result = quizResourceUnderTest.newQuiz(quizRequest);

    }

    @Test
    public void testOne() {
        when(mockRepository.findById(0)).thenReturn(Optional.of(new Quiz()));

        final Quiz result = quizResourceUnderTest.one(0);
    }

    @Test(expected= UserNotFoundException.class)
    public void testOne_QuizRepositoryReturnsAbsent() {
        when(mockRepository.findById(0)).thenReturn(Optional.empty());

        final Quiz result = quizResourceUnderTest.one(0);

    }

    @Test
    public void testReplaceUser() {
        final QuizRequest newQuiz = new QuizRequest();
        newQuiz.setName("name");
        newQuiz.setTopic("topic");
        newQuiz.setUser_id(0);

        when(mockRepository.findById(0)).thenReturn(Optional.of(new Quiz()));

        final Optional<User> user = Optional.of(new User("username", "email", "password"));
        when(mockUserRepository.findById(0)).thenReturn(user);

        when(mockRepository.save(any(Quiz.class))).thenReturn(new Quiz());

        final Quiz result = quizResourceUnderTest.replaceUser(newQuiz, 0);

    }

    @Test
    public void testReplaceUser_QuizRepositoryFindByIdReturnsAbsent() {
        final QuizRequest newQuiz = new QuizRequest();
        newQuiz.setName("name");
        newQuiz.setTopic("topic");
        newQuiz.setUser_id(0);

        when(mockRepository.findById(0)).thenReturn(Optional.empty());

        final Optional<User> user = Optional.of(new User("username", "email", "password"));
        when(mockUserRepository.findById(0)).thenReturn(user);

        when(mockRepository.save(any(Quiz.class))).thenReturn(new Quiz());

        final Quiz result = quizResourceUnderTest.replaceUser(newQuiz, 0);

    }

    @Test(expected= NoSuchElementException.class)
    public void testReplaceUser_UserRepositoryReturnsAbsent() {
        final QuizRequest newQuiz = new QuizRequest();
        newQuiz.setName("name");
        newQuiz.setTopic("topic");
        newQuiz.setUser_id(0);

        when(mockRepository.findById(0)).thenReturn(Optional.of(new Quiz()));
        when(mockUserRepository.findById(0)).thenReturn(Optional.empty());
        when(mockRepository.save(any(Quiz.class))).thenReturn(new Quiz());

        final Quiz result = quizResourceUnderTest.replaceUser(newQuiz, 0);

    }

    @Test
    public void testDeleteQuiz() {

        quizResourceUnderTest.deleteQuiz(0);

        verify(mockRepository).deleteById(0);
    }
}
