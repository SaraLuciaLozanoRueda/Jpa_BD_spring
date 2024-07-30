package com.primer.ejercicio.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.primer.ejercicio.persistence.entity.Catalog;

public interface CatalogRepository extends CrudRepository<Catalog,Long> {

}
