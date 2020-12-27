package com.quizzy.quizzy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "quizzes")
public class Quiz implements Serializable {

     @Id
    @GeneratedValue
    private int id;

    private static int counter = 1;
    private String name;
    private String topic;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "quiz")
    @JsonManagedReference
    private List<Question> questions;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public Quiz(String name, String topic, List<Question> questions, User user) {
        this.name = name;
        this.topic = topic;
        this.questions = questions;
        this.user = user;
    }

    public Quiz(String name, String topic,User user) {
        this.name = name;
        this.topic = topic;
        this.user = user;
    }

    public Quiz() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
