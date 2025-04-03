package com.example.myNutrition.domain.survey.entity.mappingTable;

import com.example.myNutrition.domain.survey.entity.Survey;
import jakarta.persistence.*;

@Entity
@Table(name = "survey_supplement")
public class SurveySupplement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String supplement; // ex: 오메가3, 마그네슘 등

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;
}
