package com.example.myNutrition.domain.survey.dto.request.plural;
import com.example.myNutrition.domain.survey.enums.plural.HealthGoal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SurveyHealthGoalRequestDto {
    private List<HealthGoal> goals;
}
