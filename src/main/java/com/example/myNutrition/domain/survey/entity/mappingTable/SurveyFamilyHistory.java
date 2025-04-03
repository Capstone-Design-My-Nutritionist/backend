package com.example.myNutrition.domain.survey.entity.mappingTable;

import com.example.myNutrition.domain.survey.entity.Survey;
import com.example.myNutrition.domain.survey.enums.plural.FamilyHistory;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "survey_family_history")
@NoArgsConstructor
public class SurveyFamilyHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FamilyHistory disease;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    public SurveyFamilyHistory(FamilyHistory disease, Survey survey) {
        this.disease = disease;
        this.survey = survey;
    }

}
