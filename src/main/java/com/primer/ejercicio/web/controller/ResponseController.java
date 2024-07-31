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

import com.primer.ejercicio.domain.service.response.ResponseImpl;
import com.primer.ejercicio.persistence.entity.Response;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/responses")
public class ResponseController {
    @Autowired
    private ResponseImpl service;

    @GetMapping
    public List<Response> listResponse(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> view(@PathVariable Long id){
        Optional<Response> optionalResponse = service.findById(id);
        if(optionalResponse.isPresent()){
            return ResponseEntity.ok(optionalResponse.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Response response,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Response response,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Response> responseOpt = service.update(id, response);
        if (responseOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id){
        Response response = new Response();
        response.setId(id);
        Optional<Response> responseOpt = service.delete(id);
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
