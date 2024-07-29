package com.primer.ejercicio.persistence.entity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "surveys")
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime create_at;
    private LocalDateTime updated_at;
    private String description;
    private String name;
    
    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Chapter> chapters;

    public Survey() {
    }

    public Survey(LocalDateTime create_at, LocalDateTime updated_at, String description, String name,
            Set<Chapter> chapters) {
        this.create_at = create_at;
        this.updated_at = updated_at;
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

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
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
        return "Survey [id=" + id + ", create_at=" + create_at + ", updated_at=" + updated_at + ", description="
                + description + ", name=" + name + ", chapters=" + chapters + "]";
    }
    
}
