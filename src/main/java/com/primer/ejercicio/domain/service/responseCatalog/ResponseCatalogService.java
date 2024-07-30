package com.primer.ejercicio.domain.service.responseCatalog;

import java.util.*;

import com.primer.ejercicio.persistence.entity.ResponseCatalog;

public interface ResponseCatalogService {
    List<ResponseCatalog> findAll();
    Optional<ResponseCatalog> findById(Long id);
    ResponseCatalog save(ResponseCatalog responseCatalog);
    Optional<ResponseCatalog> update(Long id,ResponseCatalog responseCatalog);
    Optional<ResponseCatalog> delete(Long id);
}
