package com.quizzy.quizzy.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String question;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="answer_1",referencedColumnName = "id")
    private Answer answer1;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="answer_2",referencedColumnName = "id")
   private Answer answer2;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="answer_3",referencedColumnName = "id")
   private Answer answer3;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="answer_4",referencedColumnName = "id")
    private Answer answer4;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "quiz_id", nullable = false)
   private Quiz quiz;

    public Question(){

    }

    public Question(String question, Quiz quiz){
        this.question = question;
        this.quiz = quiz;
    }

    public Question(String question, Answer answer1, Answer answer2, Answer answer3, Answer answer4, Quiz quiz) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.quiz = quiz;
    }

    public Question(String question, Answer answer1, Answer answer2, Answer answer3, Answer answer4) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Answer getAnswer1() {
        return answer1;
    }

    public void setAnswer1(Answer answer1) {
        this.answer1 = answer1;
    }

    public Answer getAnswer2() {
        return answer2;
    }

    public void setAnswer2(Answer answer2) {
        this.answer2 = answer2;
    }

    public Answer getAnswer3() {
        return answer3;
    }

    public void setAnswer3(Answer answer3) {
        this.answer3 = answer3;
    }

    public Answer getAnswer4() {
        return answer4;
    }

    public void setAnswer4(Answer answer4) {
        this.answer4 = answer4;
    }

}
