package com.quizzy.quizzy.model;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserTests {

    @Before
    public void prepare() throws NoSuchFieldException, IllegalAccessException {
        Field field = User.class.getDeclaredField("counter");
        field.setAccessible(true);
        field.set(null,1);
    }

    @Test
    public void UserCreateTest() {
        User user = new User("username", "email", "password");

        assertEquals("username", user.getUsername());
        assertEquals("email", user.getEmail());
        assertEquals("password", user.getPassword());
    }

    @Test
    public void GetIdTest() throws NoSuchFieldException, IllegalAccessException {
        prepare();
        User user = new User("username", "email", "password");

        assertEquals(1, user.getId());
    }

    @Test
    public void IdIncrementTest() throws NoSuchFieldException, IllegalAccessException {
        prepare();
        User user = new User("username", "email", "password");
        User user2 = new User("username", "email", "password");
        assertEquals(1, user.getId());
        assertEquals(2, user2.getId());
    }

    @Test
    public void SetIdTest() {
        User user = new User("username", "email", "password");
        user.setId(3);
        assertEquals(3, user.getId());
    }

    @Test
    public void GetUsernameTest() {
        User user = new User("username", "email", "password");

        assertEquals("username", user.getUsername());
    }

    @Test
    public void SetUsernameTest() {
        User user = new User("username", "email", "password");
        user.setUsername("username101");
        assertEquals("username101", user.getUsername());
    }

    @Test
    public void GetPasswordTest() {
        User user = new User("username", "email", "password");

        assertEquals("password", user.getPassword());
    }

    @Test
    public void GetEmailTest() {
        User user = new User("username", "email", "password");

        assertEquals("email", user.getEmail());
    }

    @Test
    public void SetEmailTest() {
        User user = new User("username", "email", "password");
        user.setEmail("email101");
        assertEquals("email101", user.getEmail());
    }

    @Test
    public void EqualsTest(){
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

    @Test
    public void GetRolesTest() throws NoSuchFieldException, IllegalAccessException {
        prepare();
        User user = new User("username","email","password");
        Set<Role> roles = new HashSet<>();
        Role role = new Role(ERole.ROLE_USER);
        roles.add(role);
        user.setRoles(roles);
        assertFalse(user.getRoles().isEmpty());
    }
}
