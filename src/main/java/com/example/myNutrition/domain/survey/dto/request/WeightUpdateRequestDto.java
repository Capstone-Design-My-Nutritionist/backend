package com.example.myNutrition.domain.survey.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "몸무게 업데이트 요청")
public record WeightUpdateRequestDto(
        @Schema(description = "몸무게 (kg)", example = "70")
        int weight
) {}
