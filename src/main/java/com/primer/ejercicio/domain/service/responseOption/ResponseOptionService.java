package com.primer.ejercicio.domain.service.responseOption;

import java.util.List;
import java.util.Optional;

import com.primer.ejercicio.persistence.entity.ResponseOption;

public interface ResponseOptionService {
    List<ResponseOption> findAll();
    Optional<ResponseOption> findById(Long id);
    ResponseOption save(ResponseOption responseOption);
    Optional<ResponseOption> update(Long id,ResponseOption responseOption);
    Optional<ResponseOption> delete(Long id);
}
