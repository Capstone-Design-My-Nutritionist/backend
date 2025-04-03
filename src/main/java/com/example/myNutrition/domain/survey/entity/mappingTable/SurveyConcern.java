package com.example.myNutrition.domain.survey.entity.mappingTable;

import com.example.myNutrition.domain.survey.entity.Survey;
import jakarta.persistence.*;

@Entity
@Table(name = "survey_concern")
public class SurveyConcern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String concern;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;
}
