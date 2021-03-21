package com.objective.objectivetest.service;

import com.objective.objectivetest.entities.QuestionEntity;
import com.objective.objectivetest.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    QuestionRepository questionRepository;
    QuestionEntity firstQuestion;

    @Autowired
    QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
        var lasanha = questionRepository.save(
                QuestionEntity.builder()
                        .option("Lasanha")
                        .build());
        var boloDeChocolate = questionRepository.save(
                QuestionEntity.builder()
                        .option("Bolo De Chocolate")
                        .build());
        var massa = questionRepository.save(
                QuestionEntity.builder()
                        .option("Massa")
                        .ifTrueNext(lasanha)
                        .ifFalseNext(boloDeChocolate)
                        .build());
        this.firstQuestion = massa;
    }

    public QuestionEntity add(QuestionEntity qe) {
        return this.questionRepository.save(qe);
    }

    public List<QuestionEntity> findAll() {
        return this.questionRepository.findAll();
    }

    public QuestionEntity findFirst() {
        return questionRepository.findById(3).orElse(null);
    }

    public void insert(QuestionEntity falseOption,
                       String newQuestionText,
                       String newOptionText) {

        var parentQuestion = questionRepository.getParent(falseOption);

        var newOption = QuestionEntity.builder()
                .option(newOptionText)
                .build();

        this.questionRepository.save(newOption);

        var isFalseLeaf = falseOption.equals(parentQuestion.getIfFalseNext());

        var newQuestion = QuestionEntity.builder()
                .option(newQuestionText)
                .ifFalseNext(falseOption)
                .ifTrueNext(newOption)
                .build();

        if (isFalseLeaf) {
            parentQuestion.setIfFalseNext(newQuestion);
        } else {
            parentQuestion.setIfTrueNext(newQuestion);
        }

        this.questionRepository.save(newQuestion);
        this.questionRepository.save(parentQuestion);
        var list = this.questionRepository.findAll();
        System.out.println(list);
    }
}
