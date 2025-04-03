package com.example.myNutrition.domain.survey.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

// AllergyStatusUpdateRequestDto.java
public record AllergyStatusUpdateRequestDto(
        @Schema(description = "알레르기 유무", example = "true")
        boolean hasAllergy
) {}
