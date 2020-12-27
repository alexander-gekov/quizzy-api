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
        // Setup

        // Run the test
        final String result = testControllerUnderTest.allAccess();

        // Verify the results
        assertThat(result).isEqualTo("Public Content.");
    }

    @Test
    public void testUserAccess() {
        // Setup

        // Run the test
        final String result = testControllerUnderTest.userAccess();

        // Verify the results
        assertThat(result).isEqualTo("User Content.");
    }

    @Test
    public void testModeratorAccess() {
        // Setup

        // Run the test
        final String result = testControllerUnderTest.moderatorAccess();

        // Verify the results
        assertThat(result).isEqualTo("Moderator Board.");
    }

    @Test
    public void testAdminAccess() {
        // Setup

        // Run the test
        final String result = testControllerUnderTest.adminAccess();

        // Verify the results
        assertThat(result).isEqualTo("Admin Board.");
    }
}
