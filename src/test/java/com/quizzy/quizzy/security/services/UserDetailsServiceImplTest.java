package com.quizzy.quizzy.security.services;

import com.quizzy.quizzy.model.User;
import com.quizzy.quizzy.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTest {

    private UserDetailsServiceImpl userDetailsServiceImplUnderTest;

    @Before
    public void setUp() {
        userDetailsServiceImplUnderTest = new UserDetailsServiceImpl();
        userDetailsServiceImplUnderTest.userRepository = mock(UserRepository.class);
    }

    @Test
    public void testLoadUserByUsername() {

        final Optional<User> user = Optional.of(new User("username", "email", "password"));
        when(userDetailsServiceImplUnderTest.userRepository.findByUsername("username")).thenReturn(user);

        final UserDetails result = userDetailsServiceImplUnderTest.loadUserByUsername("username");

    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsername_UserRepositoryReturnsAbsent() {

        when(userDetailsServiceImplUnderTest.userRepository.findByUsername("username")).thenReturn(Optional.empty());

        final UserDetails result = userDetailsServiceImplUnderTest.loadUserByUsername("username");

    }
}
