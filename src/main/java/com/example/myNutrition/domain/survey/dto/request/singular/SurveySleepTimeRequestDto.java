package com.example.myNutrition.domain.survey.dto.request.singular;

import com.example.myNutrition.domain.survey.enums.singular.SleepTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SurveySleepTimeRequestDto {
    private SleepTime sleepTime;
}
