package com.primer.ejercicio.domain.service.chapter;

import java.util.List;
import java.util.Optional;

import com.primer.ejercicio.persistence.entity.Chapter;

public interface ChapterService {
    List<Chapter> findAll();
    Optional<Chapter> findById(Long id);
    Chapter save(Chapter chapter);
    Optional<Chapter> update(Long id,Chapter chapter);
    Optional<Chapter> delete(Long id);
}
