package com.example.myNutrition.domain.survey.entity.mappingTable;

import com.example.myNutrition.domain.survey.entity.Survey;
import com.example.myNutrition.domain.survey.enums.plural.Supplement;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "survey_supplement")
@NoArgsConstructor
public class SurveySupplement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Supplement supplement; // ex: 오메가3, 마그네슘 등

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    public SurveySupplement(Supplement supplement, Survey survey) {
        this.supplement = supplement;
        this.survey = survey;
    }
}
