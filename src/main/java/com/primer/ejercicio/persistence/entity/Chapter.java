package com.primer.ejercicio.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "chapters")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "survey_id")
    @NotNull (message = "{NotNull.chapter.survey}")
    private Survey survey;
    
    @NotEmpty (message = "{NotEmpty.chapter.chapter_number}" )
    private String chapter_number;

    @NotNull (message = "{NotNull.chapter.chapter_title}")
    private String chapter_title;
    
    public Chapter() {
    }

    public Chapter(Survey survey, String chapter_number, String chapter_title) {
        this.survey = survey;
        this.chapter_number = chapter_number;
        this.chapter_title = chapter_title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public String getChapter_number() {
        return chapter_number;
    }

    public void setChapter_number(String chapter_number) {
        this.chapter_number = chapter_number;
    }

    public String getChapter_title() {
        return chapter_title;
    }

    public void setChapter_title(String chapter_title) {
        this.chapter_title = chapter_title;
    }

    @Override
    public String toString() {
        return "Chapter [id=" + id + ", survey=" + survey + ", chapter_number=" + chapter_number + ", chapter_title="
                + chapter_title + "]";
    }

    
}
