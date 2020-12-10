package com.quizzy.quizzy.UnitTests;

import com.quizzy.quizzy.model.User;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class UserTests {

    @Before
    public void prepare() throws NoSuchFieldException, IllegalAccessException {
        Field field = User.class.getDeclaredField("counter");
        field.setAccessible(true);
        field.set(null,1);
    }

    @Test
    public void testUserCreate() {
        User user = new User("username", "email", "password");

        assertEquals("username", user.getUsername());
        assertEquals("email", user.getEmail());
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testGetId() throws NoSuchFieldException, IllegalAccessException {
        prepare();
        User user = new User("username", "email", "password");

        assertEquals(1, user.getId());
    }

    @Test
    public void testIdIncrement() throws NoSuchFieldException, IllegalAccessException {
        prepare();
        User user = new User("username", "email", "password");
        User user2 = new User("username", "email", "password");
        assertEquals(1, user.getId());
        assertEquals(2, user2.getId());
    }

    @Test
    public void testSetId() {
        User user = new User("username", "email", "password");
        user.setId(3);
        assertEquals(3, user.getId());
    }

    @Test
    public void testGetUsername() {
        User user = new User("username", "email", "password");

        assertEquals("username", user.getUsername());
    }

    @Test
    public void testSetUsername() {
        User user = new User("username", "email", "password");
        user.setUsername("username101");
        assertEquals("username101", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        User user = new User("username", "email", "password");

        assertEquals("password", user.getPassword());
    }

    @Test
    public void testGetEmail() {
        User user = new User("username", "email", "password");

        assertEquals("email", user.getEmail());
    }

    @Test
    public void testSetEmailTest() {
        User user = new User("username", "email", "password");
        user.setEmail("email101");
        assertEquals("email101", user.getEmail());
    }

    @Test
    public void testEqualsTest(){
        User user = new User("username", "email", "password");
        User user2 = new User("username", "email", "password");

        assertTrue(user.equals(user2));
    }

    @Test
    public void ToStringTest() throws NoSuchFieldException, IllegalAccessException {
        prepare();
        User user = new User("username", "email", "password");

        assertEquals("User{" +
                "id='" + 1 + '\'' +
                ", username='" + "username" + '\'' +
                ", email='" + "email" + '\'' +
                '}',user.toString());
    }
}
