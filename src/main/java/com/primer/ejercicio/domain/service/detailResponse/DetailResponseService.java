package com.primer.ejercicio.domain.service.detailResponse;

import java.util.*;

import com.primer.ejercicio.persistence.entity.DetailsResponse;

public interface DetailResponseService {
    List<DetailsResponse> findAll();
    Optional<DetailsResponse> findById(Long id);
    DetailsResponse save(DetailsResponse detailresponse);
    Optional<DetailsResponse> update(Long id,DetailsResponse detailresponse);
    Optional<DetailsResponse> delete(Long id);
}
