package com.quizzy.quizzy.model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String topic;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private List<Question> questions;

    public Quiz(String name, String topic, List<Question> questions) {
        this.name = name;
        this.topic = topic;
        this.questions = questions;
    }

    public Quiz(String name, String topic) {
        this.name = name;
        this.topic = topic;
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
}
