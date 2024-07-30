package com.primer.ejercicio.domain.service.chapter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primer.ejercicio.domain.repository.ChapterRepository;
import com.primer.ejercicio.persistence.entity.Chapter;

@Service
public class ChapterImpl implements ChapterService {

    @Autowired
    private ChapterRepository repository;

    @Transactional
    @Override
    public List<Chapter> findAll(){
        return (List<Chapter>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Chapter> findById(Long id){
        return repository.findById(id);
    }
    

    @Transactional
    @Override
    public Chapter save(Chapter chapter){
        return repository.save(chapter);
    }   

    @Transactional
    @Override
    public Optional<Chapter> update(Long id,Chapter chapter){
         Optional<Chapter> optionalChapter = repository.findById(id);
        if(optionalChapter.isPresent()){
            Chapter chapterItem = optionalChapter.orElseThrow();
            chapterItem.setSurvey(chapter.getSurvey());
            chapterItem.setChapter_number(chapter.getChapter_number());
            chapterItem.setChapter_title(chapter.getChapter_title());
            return Optional.of(repository.save(chapterItem));
        }
        return optionalChapter;
    }   

    @Transactional
    @Override
    public Optional<Chapter> delete(Long id){
        Optional<Chapter> optionalChapter = repository.findById(id);
        optionalChapter.ifPresent(chapterItem ->{
            repository.delete(chapterItem);
        });
        return optionalChapter;
    }
}
