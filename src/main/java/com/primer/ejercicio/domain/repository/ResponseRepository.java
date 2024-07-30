package com.primer.ejercicio.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.primer.ejercicio.persistence.entity.Response;

public interface ResponseRepository extends CrudRepository<Response,Long>{

}
