package com.quizzy.quizzy.security.services;

import com.quizzy.quizzy.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDetailsImplTest {

    private UserDetailsImpl userDetailsImplUnderTest;

    @Before
    public void setUp() {
        userDetailsImplUnderTest = new UserDetailsImpl(0, "username", "email", "password", List.of());
    }

    @Test
    public void testIsAccountNonExpired() {
        final boolean result = userDetailsImplUnderTest.isAccountNonExpired();

        assertThat(result).isTrue();
    }

    @Test
    public void testIsAccountNonLocked() {
        final boolean result = userDetailsImplUnderTest.isAccountNonLocked();

        assertThat(result).isTrue();
    }

    @Test
    public void testIsCredentialsNonExpired() {
        final boolean result = userDetailsImplUnderTest.isCredentialsNonExpired();

        assertThat(result).isTrue();
    }

    @Test
    public void testIsEnabled() {
        final boolean result = userDetailsImplUnderTest.isEnabled();

        assertThat(result).isTrue();
    }

    @Test
    public void testEquals() {
        final boolean result = userDetailsImplUnderTest.equals("o");

        assertThat(result).isFalse();
    }
}
