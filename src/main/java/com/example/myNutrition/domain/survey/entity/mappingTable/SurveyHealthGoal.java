package com.example.myNutrition.domain.survey.entity.mappingTable;

import com.example.myNutrition.domain.survey.entity.Survey;
import com.example.myNutrition.domain.survey.enums.plural.HealthGoal;
import jakarta.persistence.*;

@Entity
@Table(name = "survey_goal")
public class SurveyHealthGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private HealthGoal goal;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;
}