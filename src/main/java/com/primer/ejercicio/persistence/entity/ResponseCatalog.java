package com.primer.ejercicio.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "response_catalogs")
public class ResponseCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{NotNull.responsecatlog.response_option}")
    private int response_option;


    @ManyToOne
    @JoinColumn(name ="catalog_id" )
    @NotNull(message = "{NotNull.responsecatlog.catalog}" )
    private Catalog catalog;


    @ManyToOne
    @JoinColumn(name = "question_id")
    @NotNull(message = "{NotNull.responsecatlog.question}")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "response_id")
    @NotNull(message = "{NotNull.responsecatlog.response}")
    private Response response;

    @NotBlank(message = "{NotBlank.responsecatlog.responseText}")
    private String responseText;
    
    public ResponseCatalog() {
    }

    public ResponseCatalog(int response_option, Catalog catalog, Question question, Response response,
            String responseText) {
        this.response_option = response_option;
        this.catalog = catalog;
        this.question = question;
        this.response = response;
        this.responseText = responseText;
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

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    @Override
    public String toString() {
        return "ResponseCatalog [id=" + id + ", response_option=" + response_option + ", catalog=" + catalog
                + ", question=" + question + ", response=" + response + ", responseText=" + responseText + "]";
    }

    
    

    
}
