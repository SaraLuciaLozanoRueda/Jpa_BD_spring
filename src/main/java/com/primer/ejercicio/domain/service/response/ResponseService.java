package com.primer.ejercicio.domain.service.response;

import java.util.List;
import java.util.Optional;

import com.primer.ejercicio.persistence.entity.Response;

public interface ResponseService {
    List<Response> findAll();
    Optional<Response> findById(Long id);
    Response save(Response response);
    Optional<Response> update(Long id,Response response);
    Optional<Response> delete(Long id);
}
