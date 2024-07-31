package com.primer.ejercicio.persistence.entity;

import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "catalogs")
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty (message = "{NotEmpty.catalog.name}")
    private String name;

    @OneToMany(mappedBy = "catalog", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ResponseCatalog> responseCatalogs;

    public Catalog() {
    }

    public Catalog(String name, Set<ResponseCatalog> responseCatalogs) {
        this.name = name;
        this.responseCatalogs = responseCatalogs;
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

    public Set<ResponseCatalog> getResponseCatalogs() {
        return responseCatalogs;
    }

    public void setResponseCatalogs(Set<ResponseCatalog> responseCatalogs) {
        this.responseCatalogs = responseCatalogs;
    }

    @Override
    public String toString() {
        return "Catalog [id=" + id + ", name=" + name + ", responseCatalogs=" + responseCatalogs + "]";
    }

    
}
