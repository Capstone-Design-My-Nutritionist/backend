package com.example.myNutrition.domain.survey.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

// FamilyHistoryStatusUpdateRequestDto.java
public record FamilyHistoryStatusUpdateRequestDto(
        @Schema(description = "가족력 여부", example = "true")
        boolean hasFamilyHistory
) {}
