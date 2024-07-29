package com.primer.ejercicio.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "response_catalogs")
public class ResponseCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int response_option;


    @ManyToOne
    @JoinColumn(name ="catalog_id" )
    private Catalog catalog;


    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "response_id")
    private Response response;

    private String response_text;
    
    public ResponseCatalog() {
    }

    public ResponseCatalog(int response_option, Catalog catalog, Question question, Response response,
            String response_text) {
        this.response_option = response_option;
        this.catalog = catalog;
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

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
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
        return "ResponseCatalog [id=" + id + ", response_option=" + response_option + ", catalog=" + catalog
                + ", question=" + question + ", response=" + response + ", response_text=" + response_text + "]";
    }

    
}
