package com.example.myNutrition.domain.survey.entity.mappingTable;

import com.example.myNutrition.domain.survey.entity.Survey;
import com.example.myNutrition.domain.survey.enums.plural.Medication;
import jakarta.persistence.*;

@Entity
@Table(name = "survey_medication")
public class SurveyMedication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Medication medication; // ex: 혈압, 당뇨, 진통제

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;
}