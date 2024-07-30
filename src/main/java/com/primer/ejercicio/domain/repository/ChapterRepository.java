package com.primer.ejercicio.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.primer.ejercicio.persistence.entity.Chapter;

public interface ChapterRepository extends CrudRepository<Chapter,Long> {

}
