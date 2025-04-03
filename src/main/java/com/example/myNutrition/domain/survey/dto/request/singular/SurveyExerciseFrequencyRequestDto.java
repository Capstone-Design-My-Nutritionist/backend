package com.example.myNutrition.domain.survey.dto.request.singular;
import com.example.myNutrition.domain.survey.enums.singular.ExerciseFrequency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyExerciseFrequencyRequestDto {
    private ExerciseFrequency exerciseFrequency;
}
