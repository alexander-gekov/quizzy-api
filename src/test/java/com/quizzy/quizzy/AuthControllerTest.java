package com.quizzy.quizzy;

import com.quizzy.quizzy.model.ERole;
import com.quizzy.quizzy.model.Role;
import com.quizzy.quizzy.model.User;
import com.quizzy.quizzy.payload.request.LoginRequest;
import com.quizzy.quizzy.payload.request.SignupRequest;
import com.quizzy.quizzy.repository.RoleRepository;
import com.quizzy.quizzy.repository.UserRepository;
import com.quizzy.quizzy.security.jwt.JwtUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthControllerTest {

    private AuthController authControllerUnderTest;

    @Before
    public void setUp() {
        authControllerUnderTest = new AuthController();
        authControllerUnderTest.authenticationManager = mock(AuthenticationManager.class);
        authControllerUnderTest.userRepository = mock(UserRepository.class);
        authControllerUnderTest.roleRepository = mock(RoleRepository.class);
        authControllerUnderTest.encoder = mock(PasswordEncoder.class);
        authControllerUnderTest.jwtUtils = mock(JwtUtils.class);
    }

    @Test
    public void testRegisterUser() {
        // Setup
        final SignupRequest signUpRequest = new SignupRequest();
        signUpRequest.setUsername("username");
        signUpRequest.setEmail("email");
        signUpRequest.setPassword("password");
        signUpRequest.setRole(Set.of("value"));

        when(authControllerUnderTest.userRepository.existsByUsername("username")).thenReturn(false);
        when(authControllerUnderTest.userRepository.existsByEmail("email")).thenReturn(false);
        when(authControllerUnderTest.encoder.encode("charSequence")).thenReturn("result");
        when(authControllerUnderTest.roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(new Role(ERole.ROLE_USER)));

        // Configure UserRepository.save(...).
        final User user = new User("username", "email", "password");
        when(authControllerUnderTest.userRepository.save(new User("username", "email", "password"))).thenReturn(user);

        // Run the test
        final ResponseEntity<?> result = authControllerUnderTest.registerUser(signUpRequest);

        // Verify the results
    }

}
