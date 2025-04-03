package com.example.myNutrition.domain.survey.entity.mappingTable;

import com.example.myNutrition.domain.survey.entity.Survey;
import com.example.myNutrition.domain.survey.enums.plural.HealthConcern;
import jakarta.persistence.*;

@Entity
@Table(name = "survey_concern")
public class SurveyConcern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private HealthConcern concern;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;
}
