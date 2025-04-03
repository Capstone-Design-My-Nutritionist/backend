package com.example.myNutrition.domain.survey.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

// DiagnosedDiseaseStatusUpdateRequestDto.java
public record DiagnosedDiseaseStatusUpdateRequestDto(
        @Schema(description = "진단 질환 여부", example = "true")
        boolean hasDiagnosedDisease
) {}
