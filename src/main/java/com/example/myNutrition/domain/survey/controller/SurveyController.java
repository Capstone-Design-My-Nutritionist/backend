package com.example.myNutrition.domain.survey.controller;

import com.example.myNutrition.common.response.SingleResponse;
import com.example.myNutrition.domain.survey.service.SurveyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/surveys")
@RequiredArgsConstructor
@Tag(name = "Survey API", description = "설문조사 관련 API")
public class SurveyController {

    private final SurveyService surveyService;

    @Operation(summary = "설문 생성", description = "새로운 설문을 생성하고 surveyId를 반환합니다.")
    @PostMapping
    public ResponseEntity<SingleResponse<Long>> createSurvey() {
        Long surveyId = surveyService.createSurvey();  // 현재 로그인한 유저로 생성
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SingleResponse<>(201, "설문이 생성되었습니다.", surveyId));
    }
}
