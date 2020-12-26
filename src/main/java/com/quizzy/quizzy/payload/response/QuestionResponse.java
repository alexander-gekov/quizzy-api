package com.quizzy.quizzy.payload.response;

import com.quizzy.quizzy.model.Question;

public class QuestionResponse {
    private String questionString;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;

    public QuestionResponse(Question question) {
        this.questionString = question.getQuestionString();
        this.answer1 = question.getAnswer1();
        this.answer2 = question.getAnswer2();
        this.answer3 = question.getAnswer3();
        this.answer4 = question.getAnswer4();
    }

    public String getQuestionString() {
        return questionString;
    }

    public void setQuestionString(String questionString) {
        this.questionString = questionString;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }
}
