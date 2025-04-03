package com.example.myNutrition.domain.survey.dto.request.plural;
import com.example.myNutrition.domain.survey.enums.plural.Disease;
import com.example.myNutrition.domain.survey.enums.plural.Medication;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SurveyDiseaseRequestDto {
    private List<Disease> diseases;
}
