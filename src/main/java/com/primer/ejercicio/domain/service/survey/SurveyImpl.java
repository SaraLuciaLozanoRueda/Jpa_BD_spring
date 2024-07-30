package com.primer.ejercicio.domain.service.survey;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primer.ejercicio.domain.repository.SurveyRepository;
import com.primer.ejercicio.persistence.entity.Survey;

@Service
public class SurveyImpl implements SurveyService {
    @Autowired
    private SurveyRepository repository;

    @Transactional
    @Override
    public List<Survey> findAll() {
        return (List<Survey>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Survey> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Survey save(Survey survey) {
        return repository.save(survey);
    }

    @Transactional
    @Override
    public Optional<Survey> update(Long id, Survey survey) {
        Optional<Survey> optionalSuvey = repository.findById(id);
        if (optionalSuvey.isPresent()) {
            Survey surveyItem = optionalSuvey.orElseThrow();
            surveyItem.setAudit(survey.getAudit());
            surveyItem.setDescription(survey.getDescription());
            surveyItem.setName(survey.getName());
            surveyItem.setChapters(survey.getChapters());
            return Optional.of(repository.save(surveyItem));
        }
        return optionalSuvey;
    }

    @Transactional
    @Override
    public Optional<Survey> delete(Long id) {
        Optional<Survey> optionalSurvey = repository.findById(id);
        optionalSurvey.ifPresent(SurveyItem -> {
            repository.delete(SurveyItem);
        });
        return optionalSurvey;
    }

}
