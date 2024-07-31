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

import com.primer.ejercicio.domain.service.question.QuestioImpl;
import com.primer.ejercicio.persistence.entity.Question;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/questions")
public class QuestionController {
    @Autowired
    private QuestioImpl service;

    @GetMapping
    public List<Question> listQuestion(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> view(@PathVariable Long id){
        Optional<Question> optionalQuestion = service.findById(id);
        if(optionalQuestion.isPresent()){
            return ResponseEntity.ok(optionalQuestion.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Question question,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(question));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Question question,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Question> questionOpt = service.update(id, question);
        if (questionOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(questionOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Question> delete(@PathVariable Long id){
        Question question = new Question();
        question.setId(id);
        Optional<Question> questionOpt = service.delete(id);
        if (questionOpt.isPresent()) {
            return ResponseEntity.ok(questionOpt.orElseThrow());
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
