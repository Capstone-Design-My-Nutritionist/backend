package com.example.myNutrition.domain.survey.entity.mappingTable;

import com.example.myNutrition.domain.survey.entity.Survey;
import com.example.myNutrition.domain.survey.enums.plural.Disease;
import jakarta.persistence.*;

@Entity
@Table(name = "survey_disease")
public class SurveyDisease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Disease disease;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;
}
