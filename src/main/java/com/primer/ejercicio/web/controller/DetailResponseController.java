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

import com.primer.ejercicio.domain.service.detailResponse.DetailResponseImpl;
import com.primer.ejercicio.persistence.entity.DetailsResponse;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/detailresponse")
public class DetailResponseController {
    @Autowired
    private DetailResponseImpl service;

    @GetMapping
    public List<DetailsResponse> listDetailsResponse(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsResponse> view(@PathVariable Long id){
        Optional<DetailsResponse> optionalDetailsResponse = service.findById(id);
        if(optionalDetailsResponse.isPresent()){
            return ResponseEntity.ok(optionalDetailsResponse.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DetailsResponse detailresponse,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(detailresponse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody DetailsResponse detailresponse,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<DetailsResponse> detailresponseOpt = service.update(id, detailresponse);
        if (detailresponseOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(detailresponseOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DetailsResponse> delete(@PathVariable Long id){
        DetailsResponse detailresponse = new DetailsResponse();
        detailresponse.setId(id);
        Optional<DetailsResponse> detailresponseOpt = service.delete(id);
        if (detailresponseOpt.isPresent()) {
            return ResponseEntity.ok(detailresponseOpt.orElseThrow());
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
