package com.primer.ejercicio.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "detail_responses")
public class DetailsResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int response_option;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "response_id")
    private Response response;

    private String response_text;
    
    public DetailsResponse() {
    }

    public DetailsResponse(int response_option, Question question, Response response, String response_text) {
        this.response_option = response_option;
        this.question = question;
        this.response = response;
        this.response_text = response_text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getResponse_option() {
        return response_option;
    }

    public void setResponse_option(int response_option) {
        this.response_option = response_option;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getResponse_text() {
        return response_text;
    }

    public void setResponse_text(String response_text) {
        this.response_text = response_text;
    }

    @Override
    public String toString() {
        return "DetailsResponse [id=" + id + ", response_option=" + response_option + ", question=" + question
                + ", response=" + response + ", response_text=" + response_text + "]";
    }

    
 
}
