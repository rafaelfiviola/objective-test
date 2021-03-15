package com.objective.objectivetest.repository;

import com.objective.objectivetest.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository
        extends JpaRepository<QuestionEntity, Integer> {
    @Query("FROM QuestionEntity u WHERE u.ifFalseNext = ?1 OR u.ifTrueNext = ?1")
    QuestionEntity getParent(QuestionEntity questionEntity);
}
