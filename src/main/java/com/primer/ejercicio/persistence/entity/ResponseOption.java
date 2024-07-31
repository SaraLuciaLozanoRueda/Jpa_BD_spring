package com.primer.ejercicio.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "response_options")
public class ResponseOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{NotNull.responseoption.option_value}")
    private String option_value;
    
    @ManyToOne
    @JoinColumn(name = "question_id")
    @NotNull(message = "{NotNull.responseoption.question}")
    private Question question;

    @NotBlank(message = "{NotBlank.responseoption.comment_response}")
    private String comment_response;

    @NotEmpty(message = "{NotNull.responseoption.question_parent}")
    private String option_text;

    @NotNull(message = "{NotNull.responseoption.question_parent}")
    private Long question_parent;
    
    public ResponseOption() {
    }

    public ResponseOption(String option_value, Question question, String comment_response, String option_text,
            Long question_parent) {
        this.option_value = option_value;
        this.question = question;
        this.comment_response = comment_response;
        this.option_text = option_text;
        this.question_parent = question_parent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOption_value() {
        return option_value;
    }

    public void setOption_value(String option_value) {
        this.option_value = option_value;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getComment_response() {
        return comment_response;
    }

    public void setComment_response(String comment_response) {
        this.comment_response = comment_response;
    }

    public String getOption_text() {
        return option_text;
    }

    public void setOption_text(String option_text) {
        this.option_text = option_text;
    }

    public Long getQuestion_parent() {
        return question_parent;
    }

    public void setQuestion_parent(Long question_parent) {
        this.question_parent = question_parent;
    }

    @Override
    public String toString() {
        return "ResponseOption [id=" + id + ", option_value=" + option_value + ", question=" + question
                + ", comment_response=" + comment_response + ", option_text=" + option_text + ", question_parent="
                + question_parent + "]";
    }

    
}
