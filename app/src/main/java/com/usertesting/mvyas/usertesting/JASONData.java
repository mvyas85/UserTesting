package com.usertesting.mvyas.usertesting;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by manisha.vyas on 6/5/15.
 */
public class JASONData implements Serializable {
    String id;
    String state;
    String[] operating_systems;
    String introduction;
    String reference_id;
    Screener screener;


    public JASONData(String id, String state, String[] operating_systems, String introduction,String reference_id, Screener screener) {
        this.id = id;
        this.state = state;
        this.operating_systems = operating_systems;
        this.introduction = introduction;
        this.reference_id = reference_id;

        this.screener = screener;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String[] getOperating_systems() {
        return operating_systems;
    }

    public void setOperating_systems(String[] operating_systems) {
        this.operating_systems = operating_systems;
    }

    public String getOperating_system(int i) {
        return operating_systems[i];
    }

    public void setOperating_systems(String operating_systems,int i) {
        this.operating_systems[i] = operating_systems;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getReference_id() {
        return reference_id;
    }

    public void setReference_id(String reference_id) {
        this.reference_id = reference_id;
    }

    public Screener getScreener() {
        return screener;
    }

    public void setScreener(Screener screener) {
        this.screener = screener;
    }
}



class Screener implements Serializable{
    String id;
    NextQuestion next_question;

    public Screener(String id, NextQuestion next_question) {
        this.id = id;
        this.next_question = next_question;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NextQuestion getNext_question() {
        return next_question;
    }

    public void setNext_question(NextQuestion next_question) {
        this.next_question = next_question;
    }
}

class NextQuestion implements  Serializable{

    String question;
    Answers[] answers;

    public NextQuestion(String question, Answers[] answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Answers[] getAnswers() {
        return answers;
    }

    public void setAnswers(Answers[] answers) {
        this.answers = answers;
    }

    public Answers getAnswer(int i) {
        return answers[i];
    }

    public void setAnswers(Answers answers,int i) {
        this.answers[i] = answers;
    }
}

class Answers implements Serializable{
    String ans_text;
    String right_wrong;

    public Answers(String ans_text, String right_wrong) {
        this.ans_text = ans_text;
        this.right_wrong = right_wrong;
    }

    public String getAns_text() {
        return ans_text;
    }

    public void setAns_text(String ans_text) {
        this.ans_text = ans_text;
    }

    public String getRight_wrong() {
        return right_wrong;
    }

    public void setRight_wrong(String right_wrong) {
        this.right_wrong = right_wrong;
    }
}