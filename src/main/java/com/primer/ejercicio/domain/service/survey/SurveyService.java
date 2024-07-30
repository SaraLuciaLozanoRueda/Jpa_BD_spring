package com.primer.ejercicio.domain.service.survey;

import java.util.List;
import java.util.Optional;

import com.primer.ejercicio.persistence.entity.Survey;

public interface SurveyService {
    List<Survey> findAll();
    Optional<Survey> findById(Long id);
    Survey save(Survey survey);
    Optional<Survey> update(Long id,Survey survey);
    Optional<Survey> delete(Long id);
}
