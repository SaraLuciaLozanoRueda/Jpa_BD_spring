package com.primer.ejercicio.persistence.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "responses")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{NotNull.response.response_date}")
    private LocalTime response_time;

    @NotNull(message = "{NotNull.response.response_time}")
    private LocalDateTime response_date;

    @ManyToOne
    @JoinColumn(name ="survey_id" )
    @NotNull(message = "{NotNull.response.survey}")
    private Survey survey;

    @NotBlank(message = "{NotBlank.response.name_respondent}")
    private String name_respondent;
    
    public Response() {
    }

    public Response(LocalTime response_time, LocalDateTime response_date, Survey survey, String name_respondent) {
        this.response_time = response_time;
        this.response_date = response_date;
        this.survey = survey;
        this.name_respondent = name_respondent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getResponse_time() {
        return response_time;
    }

    public void setResponse_time(LocalTime response_time) {
        this.response_time = response_time;
    }

    public LocalDateTime getResponse_date() {
        return response_date;
    }

    public void setResponse_date(LocalDateTime response_date) {
        this.response_date = response_date;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public String getName_respondent() {
        return name_respondent;
    }

    public void setName_respondent(String name_respondent) {
        this.name_respondent = name_respondent;
    }

    @Override
    public String toString() {
        return "Response [id=" + id + ", response_time=" + response_time + ", response_date=" + response_date
                + ", survey=" + survey + ", name_respondent=" + name_respondent + "]";
    }

    

    
}
