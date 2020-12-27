package com.quizzy.quizzy.payload.request;

import javax.validation.constraints.NotBlank;

public class QuizRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String topic;

    private int user_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
