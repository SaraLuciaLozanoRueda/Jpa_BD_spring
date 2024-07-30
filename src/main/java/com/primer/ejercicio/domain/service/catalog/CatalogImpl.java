package com.primer.ejercicio.domain.service.catalog;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primer.ejercicio.domain.repository.CatalogRepository;
import com.primer.ejercicio.persistence.entity.Catalog;


@Service
public class CatalogImpl implements CatalogService{
    @Autowired
    private CatalogRepository repository;

    @Transactional
    @Override
    public List<Catalog> findAll(){
        return (List<Catalog>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Catalog> findById(Long id){
        return repository.findById(id);
    }

    public Catalog save(Catalog catalog){
        return repository.save(catalog);
    }

    @Transactional
    @Override
    public Optional<Catalog> update(Long id,Catalog catalog){
        Optional<Catalog> optionalCatalog = repository.findById(id);
        if(optionalCatalog.isPresent()){
            Catalog catalogItem = optionalCatalog.orElseThrow();
            catalogItem.setName(catalog.getName());
            catalogItem.setResponseCatalogs(catalog.getResponseCatalogs());
            return Optional.of(repository.save(catalogItem));
        }
        return optionalCatalog;
    }

    @Transactional
    @Override
    public Optional<Catalog> delete(Long id){
        Optional<Catalog> optionalCatalog = repository.findById(id);
        optionalCatalog.ifPresent(catalogItem ->{
            repository.delete(catalogItem);
        });
        return optionalCatalog;
    }


}
