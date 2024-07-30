package com.primer.ejercicio.domain.service.question;

import java.util.List;
import java.util.Optional;

import com.primer.ejercicio.persistence.entity.Question;

public interface QuestionService {
    List<Question> findAll();
    Optional<Question> findById(Long id);
    Question save(Question question);
    Optional<Question> update(Long id,Question question);
    Optional<Question> delete(Long id);
}
