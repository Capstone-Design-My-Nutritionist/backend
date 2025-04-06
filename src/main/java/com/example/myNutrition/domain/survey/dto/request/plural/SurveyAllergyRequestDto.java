package com.example.myNutrition.domain.survey.dto.request.plural;
import com.example.myNutrition.domain.survey.enums.plural.Allergy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SurveyAllergyRequestDto {
    private List<Allergy> allergies;
}
