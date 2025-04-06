package com.example.myNutrition.domain.survey.dto.request.singular;
import com.example.myNutrition.domain.survey.enums.singular.WaterIntake;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyWaterIntakeRequestDto {
    private WaterIntake waterIntake;
}
