package com.example.myNutrition.domain.survey.entity.mappingTable;

import com.example.myNutrition.domain.survey.entity.Survey;
import com.example.myNutrition.domain.survey.enums.plural.Allergy;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "survey_allergy")
@NoArgsConstructor
public class SurveyAllergy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Allergy allergy;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    public SurveyAllergy(Allergy allergy, Survey survey) {
        this.allergy = allergy;
        this.survey = survey;
    }
}