package com.primer.ejercicio.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.primer.ejercicio.domain.service.responseCatalog.ResponseCatalogImpl;
import com.primer.ejercicio.persistence.entity.ResponseCatalog;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/responsesCatalog")
public class ResponseCatalogController {
    @Autowired
    private ResponseCatalogImpl service;

    @GetMapping
    public List<ResponseCatalog> listResponse(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCatalog> view(@PathVariable Long id){
        Optional<ResponseCatalog> optionalResponse = service.findById(id);
        if(optionalResponse.isPresent()){
            return ResponseEntity.ok(optionalResponse.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ResponseCatalog responseCatalog,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(responseCatalog));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ResponseCatalog responseCatalog,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<ResponseCatalog> responseOpt = service.update(id, responseCatalog);
        if (responseOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseCatalog> delete(@PathVariable Long id){
        ResponseCatalog responseCatalog = new ResponseCatalog();
        responseCatalog.setId(id);
        Optional<ResponseCatalog> responseOpt = service.delete(id);
        if (responseOpt.isPresent()) {
            return ResponseEntity.ok(responseOpt.orElseThrow());
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
