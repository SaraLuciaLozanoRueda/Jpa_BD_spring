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

import com.primer.ejercicio.domain.service.chapter.ChapterImpl;
import com.primer.ejercicio.persistence.entity.Chapter;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/chapters")
public class ChapterController {
    @Autowired
    private ChapterImpl service;

    @GetMapping
    public List<Chapter> listChapter(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chapter> view(@PathVariable Long id){
        Optional<Chapter> optionalChapter = service.findById(id);
        if(optionalChapter.isPresent()){
            return ResponseEntity.ok(optionalChapter.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Chapter chapter,BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(chapter));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Chapter chapter,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Chapter> chapterOpt = service.update(id, chapter);
        if (chapterOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(chapterOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Chapter> delete(@PathVariable Long id){
        Chapter chapter = new Chapter();
        chapter.setId(id);
        Optional<Chapter> chapterOpt = service.delete(id);
        if (chapterOpt.isPresent()) {
            return ResponseEntity.ok(chapterOpt.orElseThrow());
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
