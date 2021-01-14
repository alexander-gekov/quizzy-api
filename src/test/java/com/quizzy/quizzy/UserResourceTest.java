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
        final List<User> expectedResult = List.of(new User("username", "email", "password"));

        final List<User> users = List.of(new User("username", "email", "password"));
        when(mockRepository.findAll()).thenReturn(users);

        final List<User> result = userResourceUnderTest.all();

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testNewUser() {
        final User newUser = new User("username", "email", "password");
        final User expectedResult = new User("username", "email", "password");

        final User user = new User("username", "email", "password");
        when(mockRepository.save(new User("username", "email", "password"))).thenReturn(user);

        final User result = userResourceUnderTest.newUser(newUser);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetUser() {
        final User expectedResult = new User("username", "email", "password");

        final Optional<User> user = Optional.of(new User("username", "email", "password"));
        when(mockRepository.findById(0)).thenReturn(user);

        final User result = userResourceUnderTest.getUser(0);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetUser_UserRepositoryReturnsAbsent() {
        final User expectedResult = new User("username", "email", "password");
        when(mockRepository.findById(0)).thenReturn(Optional.empty());

        final User result = userResourceUnderTest.getUser(0);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testReplaceUser() {
        final User newUser = new User("username", "email", "password");
        final User expectedResult = new User("username", "email", "password");

        final Optional<User> user = Optional.of(new User("username", "email", "password"));
        when(mockRepository.findById(0)).thenReturn(user);

        final User user1 = new User("username", "email", "password");
        when(mockRepository.save(new User("username", "email", "password"))).thenReturn(user1);

        final User result = userResourceUnderTest.replaceUser(newUser, 0);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test(expected = UserNotFoundException.class)
    public void testReplaceUser_UserRepositoryFindByIdReturnsAbsent() {

        final User newUser = new User("username", "email", "password");
        final User expectedResult = new User("username", "email", "password");
        when(mockRepository.findById(0)).thenReturn(Optional.empty());


        final User user = new User("username", "email", "password");
        when(mockRepository.save(new User("username", "email", "password"))).thenReturn(user);


        final User result = userResourceUnderTest.replaceUser(newUser, 0);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testDeleteEmployee() {

        final String result = userResourceUnderTest.deleteEmployee(0);


        assertThat(result).isEqualTo("Successfully deleted user with id : 0");
        verify(mockRepository).deleteById(0);
    }
}
