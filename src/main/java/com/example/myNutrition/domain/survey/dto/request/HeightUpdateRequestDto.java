package com.example.myNutrition.domain.survey.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "키 업데이트 요청")
public record HeightUpdateRequestDto(
        @Schema(description = "키 (cm)", example = "175")
        int height
) {}
