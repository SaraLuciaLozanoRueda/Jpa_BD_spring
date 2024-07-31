package com.primer.ejercicio.persistence.entity;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "surveys")
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    Audit audit = new Audit();

    @NotNull(message = "{NotNull.survey.description}")
    private String description;

    @NotEmpty(message = "{NotEmpty.survey.name}")
    private String name;
    
    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull(message = "{NotNull.survey.chapters}")
    private Set<Chapter> chapters;

    public Survey() {
    }

    public Survey(Audit audit, String description, String name, Set<Chapter> chapters) {
        this.audit = audit;
        this.description = description;
        this.name = name;
        this.chapters = chapters;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(Set<Chapter> chapters) {
        this.chapters = chapters;
    }

    @Override
    public String toString() {
        return "Survey [id=" + id + ", audit=" + audit + ", description=" + description + ", name=" + name
                + ", chapters=" + chapters + "]";
    }

    
    
}
