package com.example.myNutrition.domain.survey.entity.mappingTable;

import com.example.myNutrition.domain.survey.entity.Survey;
import jakarta.persistence.*;

@Entity
@Table(name = "survey_disease")
public class SurveyDisease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String disease;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;
}
