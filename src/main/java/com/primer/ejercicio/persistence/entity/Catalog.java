package com.primer.ejercicio.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "catalogs")
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Catalog() {
    }

    public Catalog(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Catalog [id=" + id + ", name=" + name + "]";
    }

    
}
