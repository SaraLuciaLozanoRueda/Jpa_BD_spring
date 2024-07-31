package com.primer.ejercicio.web.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.primer.ejercicio.domain.service.catalog.CatalogImpl;
import com.primer.ejercicio.persistence.entity.Catalog;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/catalogs")
public class CatalogController {
    @Autowired
    private CatalogImpl service;

    @GetMapping
    public List<Catalog> listCatalog(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Catalog> view(@PathVariable Long id){
        Optional<Catalog> optionalCatalog = service.findById(id);
        if(optionalCatalog.isPresent()){
            return ResponseEntity.ok(optionalCatalog.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Catalog catalog,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(catalog));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Catalog catalog,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Catalog> catalogOpt = service.update(id, catalog);
        if (catalogOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(catalogOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Catalog> delete(@PathVariable Long id){
        Catalog catalog = new Catalog();
        catalog.setId(id);
        Optional<Catalog> catalogOpt = service.delete(id);
        if (catalogOpt.isPresent()) {
            return ResponseEntity.ok(catalogOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {//Este siempre es necesario y en realidad no es una mala practica...
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
        errors.put(err.getField(), err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
        }
}
