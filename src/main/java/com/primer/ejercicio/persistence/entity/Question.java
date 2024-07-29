package com.primer.ejercicio.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @ManyToOne
    @JoinColumn(name = "parent_question_id")
    private Question parentQuestion;


    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    private String question_number;
    private String question_text;
    private String response_type;
    private String comment_question;
    
    public Question() {
    }

    public Question(Chapter chapter, Question parentQuestion, Survey survey, String question_number,
            String question_text, String response_type, String comment_question) {
        this.chapter = chapter;
        this.parentQuestion = parentQuestion;
        this.survey = survey;
        this.question_number = question_number;
        this.question_text = question_text;
        this.response_type = response_type;
        this.comment_question = comment_question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Question getParentQuestion() {
        return parentQuestion;
    }

    public void setParentQuestion(Question parentQuestion) {
        this.parentQuestion = parentQuestion;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public String getQuestion_number() {
        return question_number;
    }

    public void setQuestion_number(String question_number) {
        this.question_number = question_number;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public String getResponse_type() {
        return response_type;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }

    public String getComment_question() {
        return comment_question;
    }

    public void setComment_question(String comment_question) {
        this.comment_question = comment_question;
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", chapter=" + chapter + ", parentQuestion=" + parentQuestion + ", survey="
                + survey + ", question_number=" + question_number + ", question_text=" + question_text
                + ", response_type=" + response_type + ", comment_question=" + comment_question + "]";
    }

    

    
   
    
}
