package com.quizzy.quizzy.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoleTests {
    @Test
    public void GetSetIdTest() throws NoSuchFieldException, IllegalAccessException {
        Role role = new Role(ERole.ROLE_USER);
        role.setId(1);
        assertEquals(1, role.getId());
    }

    @Test
    public void GetNameTest() throws NoSuchFieldException, IllegalAccessException {
        Role role = new Role(ERole.ROLE_USER);
        assertEquals(ERole.ROLE_USER, role.getName());
    }

    @Test
    public void SetNameTest() throws NoSuchFieldException, IllegalAccessException {
        Role role = new Role(ERole.ROLE_USER);
        role.setName(ERole.ROLE_ADMIN);
        assertEquals(ERole.ROLE_ADMIN, role.getName());
    }
}
