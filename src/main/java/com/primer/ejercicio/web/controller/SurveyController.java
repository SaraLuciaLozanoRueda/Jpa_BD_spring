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

import com.primer.ejercicio.domain.service.survey.SurveyImpl;
import com.primer.ejercicio.persistence.entity.Survey;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/surveys")
public class SurveyController {
    @Autowired
    private SurveyImpl service;

    @GetMapping
    public List<Survey> listCatalog(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Survey> view(@PathVariable Long id){
        Optional<Survey> optionalSurvey = service.findById(id);
        if(optionalSurvey.isPresent()){
            return ResponseEntity.ok(optionalSurvey.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Survey survey,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(survey));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Survey survey,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Survey> surveyOpt = service.update(id, survey);
        if (surveyOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(surveyOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Survey> delete(@PathVariable Long id){
        Survey survey = new Survey();
        survey.setId(id);
        Optional<Survey> surveyOpt = service.delete(id);
        if (surveyOpt.isPresent()) {
            return ResponseEntity.ok(surveyOpt.orElseThrow());
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
