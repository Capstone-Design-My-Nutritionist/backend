package com.example.myNutrition.domain.meal.controller;

import com.example.myNutrition.common.response.SingleResponse;
import com.example.myNutrition.domain.meal.dto.request.MealFoodRegisterRequestDto;
import com.example.myNutrition.domain.meal.dto.response.DailyMealRecordResponseDto;
import com.example.myNutrition.domain.meal.service.MealRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController("/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealRecordService mealRecordService;

    @Operation(
            summary = "음식 사진 기반 식사 기록 저장",
            description = """
        푸드렌즈 API를 통해 분석된 음식 데이터를 받아 저장합니다.

        - `mealTime`: BREAKFAST(아침), LUNCH(점심), DINNER(저녁), SNACK(간식) 등 (enum)
        - `imageUrl`: 사용자 업로드 이미지의 URL
        - `foods`: 사용자가 섭취한 음식 리스트
            - `name`: 음식 이름 (예: White Rice)
            - `eatAmount`: 섭취량 (1.0 = 1인분 기준)
            - `nutrition`: 영양소 정보
                - `energy`: 칼로리 (kcal)
                - `carbohydrate`: 탄수화물 (g)
                - `protein`: 단백질 (g)
                - `fat`: 지방 (g)
                - `vitaminA`, `vitaminC`, `calcium`: 기타 영양소 (선택 입력 가능)

         주의:
        - `eatAmount`는 사용자가 선택한 섭취량(1인분 기준 비율)
        - 누락된 영양소는 null 허용 (예: 비타민A 정보가 없을 경우 생략)
        - 추후 입력 받을 영양소의 경우 조율 예정
    """
    ) @PostMapping("/meals")
    public ResponseEntity<SingleResponse<Long>> registerMeal(
            @Valid @RequestBody MealFoodRegisterRequestDto request
    ) {
        Long mealRecordId = mealRecordService.registerMealRecord(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SingleResponse<>(201, "음식 기록 등록 완료", mealRecordId));
    }





}
