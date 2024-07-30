package com.primer.ejercicio.domain.service.catalog;

import java.util.*;

import com.primer.ejercicio.persistence.entity.Catalog;;

public interface CatalogService {
    List<Catalog> findAll();
    Optional<Catalog> findById(Long id);
    Catalog save(Catalog catalog);
    Optional<Catalog> update(Long id,Catalog catalog);
    Optional<Catalog> delete(Long id);
}
