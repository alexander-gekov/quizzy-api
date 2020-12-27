package com.quizzy.quizzy;

import com.quizzy.quizzy.model.User;
import com.quizzy.quizzy.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserResourceTest {

    @Mock
    private UserRepository mockRepository;

    private UserResource userResourceUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        userResourceUnderTest = new UserResource(mockRepository);
    }

    @Test
    public void testAll() {
        // Setup
        final List<User> expectedResult = List.of(new User("username", "email", "password"));

        // Configure UserRepository.findAll(...).
        final List<User> users = List.of(new User("username", "email", "password"));
        when(mockRepository.findAll()).thenReturn(users);

        // Run the test
        final List<User> result = userResourceUnderTest.all();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testNewUser() {
        // Setup
        final User newUser = new User("username", "email", "password");
        final User expectedResult = new User("username", "email", "password");

        // Configure UserRepository.save(...).
        final User user = new User("username", "email", "password");
        when(mockRepository.save(new User("username", "email", "password"))).thenReturn(user);

        // Run the test
        final User result = userResourceUnderTest.newUser(newUser);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetUser() {
        // Setup
        final User expectedResult = new User("username", "email", "password");

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(new User("username", "email", "password"));
        when(mockRepository.findById(0)).thenReturn(user);

        // Run the test
        final User result = userResourceUnderTest.getUser(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetUser_UserRepositoryReturnsAbsent() {
        // Setup
        final User expectedResult = new User("username", "email", "password");
        when(mockRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final User result = userResourceUnderTest.getUser(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testReplaceUser() {
        // Setup
        final User newUser = new User("username", "email", "password");
        final User expectedResult = new User("username", "email", "password");

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(new User("username", "email", "password"));
        when(mockRepository.findById(0)).thenReturn(user);

        // Configure UserRepository.save(...).
        final User user1 = new User("username", "email", "password");
        when(mockRepository.save(new User("username", "email", "password"))).thenReturn(user1);

        // Run the test
        final User result = userResourceUnderTest.replaceUser(newUser, 0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test(expected = UserNotFoundException.class)
    public void testReplaceUser_UserRepositoryFindByIdReturnsAbsent() {
        // Setup
        final User newUser = new User("username", "email", "password");
        final User expectedResult = new User("username", "email", "password");
        when(mockRepository.findById(0)).thenReturn(Optional.empty());

        // Configure UserRepository.save(...).
        final User user = new User("username", "email", "password");
        when(mockRepository.save(new User("username", "email", "password"))).thenReturn(user);

        // Run the test
        final User result = userResourceUnderTest.replaceUser(newUser, 0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testDeleteEmployee() {
        // Setup

        // Run the test
        final String result = userResourceUnderTest.deleteEmployee(0);

        // Verify the results
        assertThat(result).isEqualTo("Successfully deleted user with id : 0");
        verify(mockRepository).deleteById(0);
    }
}
