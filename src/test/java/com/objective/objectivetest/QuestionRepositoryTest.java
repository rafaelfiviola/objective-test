package com.objective.objectivetest;

import com.objective.objectivetest.entities.QuestionEntity;
import com.objective.objectivetest.repository.QuestionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class
})
class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test()
    void addsToDb() {
//        var questionRepository.save(new QuestionEntity());
    }

    @Test()
    void add() {
        String mainOptionText = "Text";
        var optionIfFalse =
                QuestionEntity.builder().option("any_option").build();
        var optionIfTrue =
                QuestionEntity.builder().option("other_option").build();
        var persistedQuestion = questionRepository
                .save(
                        QuestionEntity.builder()
                                .option("")
                                .ifTrueNext(optionIfTrue)
                                .ifFalseNext(optionIfFalse)
                                .build());
        var questionFromDb =
                questionRepository.findById(persistedQuestion.getId());
        Assertions
                .assertNotNull(persistedQuestion, "Return should not be null");
        Assertions
                .assertEquals(questionFromDb.orElse(null).getOption(),
                        persistedQuestion.getOption());
    }

    @Test()
    void addsObjectWithCorrectValues() {
        var persistedQuestion = questionRepository.save(new QuestionEntity());
        Assertions.assertNotNull(persistedQuestion);
    }
}