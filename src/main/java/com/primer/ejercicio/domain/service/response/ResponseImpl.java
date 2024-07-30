package com.primer.ejercicio.domain.service.response;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primer.ejercicio.domain.repository.ResponseRepository;
import com.primer.ejercicio.persistence.entity.Response;


@Service
public class ResponseImpl implements ResponseService{
    @Autowired
    private ResponseRepository repository;

    @Transactional
    @Override
    public List<Response> findAll(){
        return (List<Response>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Response> findById(Long id){
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Response save(Response response){
        return repository.save(response);
    }

    @Transactional
    @Override
    public Optional<Response> update(Long id,Response response){
        Optional<Response> optionalResponse = repository.findById(id);
        if(optionalResponse.isPresent()){
            Response responseItem = optionalResponse.orElseThrow();
            responseItem.setResponse_time(response.getResponse_time());
            responseItem.setResponse_date(response.getResponse_date());
            responseItem.setSurvey(response.getSurvey());
            responseItem.setName_respondent(response.getName_respondent());
            return Optional.of(repository.save(responseItem));
        }
        return optionalResponse;
    }

    @Transactional
    @Override
    public Optional<Response> delete(Long id){
        Optional<Response> optionalResponse = repository.findById(id);
        optionalResponse.ifPresent(responseItem ->{
            repository.delete(responseItem);
        });
        return optionalResponse;
    }
}
