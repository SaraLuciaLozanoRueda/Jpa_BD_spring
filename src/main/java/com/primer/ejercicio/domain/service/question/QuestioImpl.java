package com.primer.ejercicio.domain.service.question;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primer.ejercicio.domain.repository.QuestionRepository;
import com.primer.ejercicio.persistence.entity.Question;


@Service
public class QuestioImpl implements QuestionService{
    @Autowired
    private QuestionRepository repository;

    @Transactional
    @Override
    public List<Question> findAll(){
        return (List<Question>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Question> findById(Long id){
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Question save(Question question){
        return repository.save(question);
    }   

    @Transactional
    @Override
    public Optional<Question> update(Long id,Question question){
         Optional<Question> optionalQuestion = repository.findById(id);
        if(optionalQuestion.isPresent()){
            Question questionItem = optionalQuestion.orElseThrow();
            questionItem.setChapter(question.getChapter());
            questionItem.setParentQuestion(question.getParentQuestion());
            questionItem.setSurvey(question.getSurvey());
            questionItem.setQuestion_number(question.getQuestion_number());
            questionItem.setQuestion_text(question.getQuestion_text());
            questionItem.setResponse_type(question.getResponse_type());
            questionItem.setComment_question(question.getComment_question());
            return Optional.of(repository.save(questionItem));
        }
        return optionalQuestion;
    }

    @Transactional
    @Override
    public Optional<Question> delete(Long id){
        Optional<Question> optionalQuestion = repository.findById(id);
        optionalQuestion.ifPresent(questionItem ->{
            repository.delete(questionItem);
        });
        return optionalQuestion;
    }
}
