package com.example.myNutrition.domain.survey.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "나이 업데이트 요청")
public record AgeUpdateRequestDto(
        @Schema(description = "나이", example = "28")
        int age
) {}
