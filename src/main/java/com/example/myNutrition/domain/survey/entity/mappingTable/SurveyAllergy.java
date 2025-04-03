package com.example.myNutrition.domain.survey.entity.mappingTable;

import com.example.myNutrition.domain.survey.entity.Survey;
import com.example.myNutrition.domain.survey.enums.plural.Allergy;
import jakarta.persistence.*;

@Entity
@Table(name = "survey_allergy")
public class SurveyAllergy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Allergy allergy;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;
}