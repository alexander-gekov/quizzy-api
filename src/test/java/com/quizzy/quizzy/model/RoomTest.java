package com.quizzy.quizzy.model;

import org.junit.Before;

import java.util.List;

public class RoomTest {

    private Room roomUnderTest;

    @Before
    public void setUp() {
        roomUnderTest = new Room("gameId", List.of("value"));
    }
}
