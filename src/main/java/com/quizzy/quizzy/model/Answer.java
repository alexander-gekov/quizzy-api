package com.quizzy.quizzy.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "answers")
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private static int counter = 1;

    private String answer;
//
//    @OneToOne(mappedBy = "question")
//    private Question question;

    public Answer(String answer){
        this.id = counter;
        counter++;
        this.answer = answer;
    }

    public Answer() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
