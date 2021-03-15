package com.objective.objectivetest.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEntity {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "ifTrueIdFk")
    private QuestionEntity ifTrueNext;

    @OneToOne
    @JoinColumn(name = "ifFalseIdFk")
    private QuestionEntity ifFalseNext;

    private String option;

    public boolean isLeaf() {
        return ifTrueNext == null && ifFalseNext == null;
    }
}
