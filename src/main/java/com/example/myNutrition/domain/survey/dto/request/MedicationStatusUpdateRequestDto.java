package com.example.myNutrition.domain.survey.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "약 복용 여부 업데이트 요청")
public record MedicationStatusUpdateRequestDto(
        @Schema(description = "약 복용 여부", example = "true")
        boolean takingMedication
) {}
