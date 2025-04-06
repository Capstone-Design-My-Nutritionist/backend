package com.example.myNutrition.domain.survey.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

// SupplementStatusUpdateRequestDto.java
public record SupplementStatusUpdateRequestDto(
        @Schema(description = "영양제 복용 여부", example = "true")
        boolean takingSupplements
) {}
