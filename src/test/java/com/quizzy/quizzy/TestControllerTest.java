package com.quizzy.quizzy;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestControllerTest {

    private TestController testControllerUnderTest;

    @Before
    public void setUp() {
        testControllerUnderTest = new TestController();
    }

    @Test
    public void testAllAccess() {

        final String result = testControllerUnderTest.allAccess();

        assertThat(result).isEqualTo("Public Content.");
    }

    @Test
    public void testUserAccess() {

        final String result = testControllerUnderTest.userAccess();

        assertThat(result).isEqualTo("User Content.");
    }

    @Test
    public void testModeratorAccess() {

        final String result = testControllerUnderTest.moderatorAccess();

        assertThat(result).isEqualTo("Moderator Board.");
    }

    @Test
    public void testAdminAccess() {

        final String result = testControllerUnderTest.adminAccess();

        assertThat(result).isEqualTo("Admin Board.");
    }
}
