package com.example.myNutrition.domain.survey.entity.mappingTable;

import com.example.myNutrition.domain.survey.entity.Survey;
import com.example.myNutrition.domain.survey.enums.plural.Supplement;
import jakarta.persistence.*;

@Entity
@Table(name = "survey_supplement")
public class SurveySupplement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Supplement supplement; // ex: 오메가3, 마그네슘 등

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;
}
