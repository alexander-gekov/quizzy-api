package com.quizzy.quizzy.model;

import com.sun.istack.Nullable;
import org.springframework.messaging.simp.user.SimpUser;

import java.util.Set;

public class Message {
    private MessageType type;
    private String content;

    private String sender;
    @Nullable
    private String other;

    public Message(MessageType type, String content, String sender) {
        this.type = type;
        this.content = content;
        this.sender = sender;
    }

    public Message() {
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }


    public enum MessageType {
        CHAT,
        GET_PLAYERS,
        CREATE,
        EXISTS,
        START,
        END,
        ANSWER,
        JOIN,
        LEFT,
        LEAVE
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

}
