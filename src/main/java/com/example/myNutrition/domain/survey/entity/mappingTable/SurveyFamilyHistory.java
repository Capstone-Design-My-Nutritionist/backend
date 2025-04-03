package com.example.myNutrition.domain.survey.entity.mappingTable;

import com.example.myNutrition.domain.survey.entity.Survey;
import com.example.myNutrition.domain.survey.enums.plural.FamilyHistory;
import jakarta.persistence.*;

@Entity
@Table(name = "survey_family_history")
public class SurveyFamilyHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FamilyHistory disease;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;
}
