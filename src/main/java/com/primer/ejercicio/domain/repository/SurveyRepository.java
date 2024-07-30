package com.primer.ejercicio.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.primer.ejercicio.persistence.entity.Survey;

public interface SurveyRepository extends CrudRepository<Survey,Long> {

}
