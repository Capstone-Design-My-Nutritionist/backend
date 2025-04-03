package com.example.myNutrition.domain.survey.dto.request.plural;

import com.example.myNutrition.domain.survey.enums.singular.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class GenderUpdateRequestDto {

    @NotNull(message = "성별은 필수입니다.")
    private Gender gender;
}