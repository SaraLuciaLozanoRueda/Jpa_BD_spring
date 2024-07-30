package com.primer.ejercicio.domain.service.responseCatalog;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primer.ejercicio.domain.repository.ResponseCatalogRepository;
import com.primer.ejercicio.persistence.entity.ResponseCatalog;


@Service
public class ResponseCatalogImpl implements ResponseCatalogService {
    @Autowired
    private ResponseCatalogRepository repository;

    @Transactional
    @Override
    public List<ResponseCatalog> findAll(){
        return (List<ResponseCatalog>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<ResponseCatalog> findById(Long id){
        return repository.findById(id);
    }

    @Transactional
    @Override
    public ResponseCatalog save(ResponseCatalog responseCatalog){
        return repository.save(responseCatalog);
    }

    @Transactional
    @Override
    public Optional<ResponseCatalog> update(Long id,ResponseCatalog responseCatalog){
        Optional<ResponseCatalog> optionalResponseCatalog = repository.findById(id);
        if(optionalResponseCatalog.isPresent()){
            ResponseCatalog responsecatalogItem = optionalResponseCatalog.orElseThrow();
            responsecatalogItem.setResponse_option(responseCatalog.getResponse_option());
            responsecatalogItem.setCatalog(responseCatalog.getCatalog());
            responsecatalogItem.setQuestion(responseCatalog.getQuestion());
            responsecatalogItem.setResponse(responseCatalog.getResponse());
            responsecatalogItem.setResponseText(responseCatalog.getResponseText());

            return Optional.of(repository.save(responsecatalogItem));
        }
        return optionalResponseCatalog;
    }

    @Transactional
    @Override
    public Optional<ResponseCatalog> delete(Long id){
        Optional<ResponseCatalog> optionalResponseCatalog = repository.findById(id);
        optionalResponseCatalog.ifPresent(responseCatalogItem ->{
            repository.delete(responseCatalogItem);
        });
        return optionalResponseCatalog;
    }
    
}
