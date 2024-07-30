package com.primer.ejercicio.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.primer.ejercicio.persistence.entity.Question;

public interface QuestionRepository extends CrudRepository<Question,Long>{

}
