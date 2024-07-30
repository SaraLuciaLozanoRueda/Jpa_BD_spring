package com.primer.ejercicio.domain.service.detailResponse;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primer.ejercicio.domain.repository.DetailsResponseRepository;
import com.primer.ejercicio.persistence.entity.DetailsResponse;


@Service
public class DetailResponseImpl implements DetailResponseService{
    
    @Autowired
    private DetailsResponseRepository repository;

    @Transactional
    @Override
    public List<DetailsResponse> findAll(){
        return (List<DetailsResponse>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<DetailsResponse> findById(Long id){
        return repository.findById(id);
    }

    @Transactional
    @Override
    public DetailsResponse save(DetailsResponse detailresponse){
        return repository.save(detailresponse);
    }

    @Transactional
    @Override
    public Optional<DetailsResponse> update(Long id,DetailsResponse detailresponse){
        Optional<DetailsResponse> optionalDetailResponse = repository.findById(id);
        if(optionalDetailResponse.isPresent()){
            DetailsResponse detailItem = optionalDetailResponse.orElseThrow();
            detailItem.setQuestion(detailresponse.getQuestion());
            detailItem.setResponse(detailresponse.getResponse());
            detailItem.setResponse_option(detailresponse.getResponse_option());
            detailItem.setResponse_text(detailresponse.getResponse_text());
            return Optional.of(repository.save(detailItem));
        }
        return optionalDetailResponse;
    }

    @Transactional
    @Override
    public  Optional<DetailsResponse> delete(Long id){
        Optional<DetailsResponse> optionaldetail = repository.findById(id);
        optionaldetail.ifPresent(catalogItem ->{
            repository.delete(catalogItem);
        });
        return optionaldetail;
    }
}   
