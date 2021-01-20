package com.quizzy.quizzy.model;

import org.junit.Before;

public class MessageTest {

    private Message messageUnderTest;

    @Before
    public void setUp() {
        messageUnderTest = new Message(Message.MessageType.CHAT, "content", "sender");
    }
}
