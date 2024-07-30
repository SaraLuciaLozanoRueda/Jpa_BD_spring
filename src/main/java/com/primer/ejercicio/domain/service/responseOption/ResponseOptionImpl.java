package com.primer.ejercicio.domain.service.responseOption;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primer.ejercicio.domain.repository.ResponseOptionRepository;
import com.primer.ejercicio.persistence.entity.ResponseOption;

@Service
public class ResponseOptionImpl implements ResponseOptionService{
    @Autowired
    private ResponseOptionRepository repository;

    @Transactional
    @Override
    public List<ResponseOption> findAll(){
        return (List<ResponseOption>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<ResponseOption> findById(Long id){
        return repository.findById(id);
    }

    @Transactional
    @Override
    public  ResponseOption save(ResponseOption responseOption){
        return repository.save(responseOption);
    }

    @Transactional
    @Override
    public Optional<ResponseOption> update(Long id,ResponseOption responseOption){
        Optional<ResponseOption> optionalRO = repository.findById(id);
        if(optionalRO.isPresent()){
            ResponseOption RoItem = optionalRO.orElseThrow();
            RoItem.setOption_value(responseOption.getOption_value());
            RoItem.setQuestion(responseOption.getQuestion());
            RoItem.setComment_response(responseOption.getComment_response());
            RoItem.setOption_text(responseOption.getOption_text());
            RoItem.setQuestion_parent(responseOption.getQuestion_parent());
            return Optional.of(repository.save(RoItem));
        }
        return optionalRO;
    }

    @Transactional
    @Override
    public Optional<ResponseOption> delete(Long id){
        Optional<ResponseOption> optionalRO = repository.findById(id);
        optionalRO.ifPresent(ROItem ->{
            repository.delete(ROItem);
        });
        return optionalRO;
    }
}
