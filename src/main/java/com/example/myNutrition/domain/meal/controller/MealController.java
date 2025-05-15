package com.example.myNutrition.domain.meal.controller;

import com.example.myNutrition.common.response.SingleResponse;
import com.example.myNutrition.domain.meal.dto.request.MealFoodRegisterRequestDto;
import com.example.myNutrition.domain.meal.dto.response.DailyMealRecordResponseDto;
import com.example.myNutrition.domain.meal.dto.response.DailyNutritionSummaryResponseDto;
import com.example.myNutrition.domain.meal.entity.MealType;
import com.example.myNutrition.domain.meal.service.MealRecordService;
import com.example.myNutrition.infra.s3.S3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@RestController("/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealRecordService mealRecordService;
    private final S3Service s3Service;

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
    ) @PostMapping(value = "/meals", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SingleResponse<Long>> registerMeal(
            @RequestPart("request") @Valid MealFoodRegisterRequestDto request,
            @RequestPart("image") MultipartFile imageFile
    ) {
        String imageUrl = s3Service.uploadImage(imageFile);
        Long mealRecordId = mealRecordService.registerMealRecord(request, imageUrl);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SingleResponse<>(201, "음식 기록 등록 완료", mealRecordId));
    }



    @Operation(summary = "하루 식사 기록 조회", description = "하루 동안 아침, 점심, 저녁의 식사 기록을 조회합니다.")
    @GetMapping("/meal-records")
    public ResponseEntity<SingleResponse<DailyMealRecordResponseDto>> getDailyMealRecords(
            @Parameter(description = "조회할 날짜 (yyyy-MM-dd)", example = "2025-04-25")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        DailyMealRecordResponseDto response = mealRecordService.getDailyMealRecords(date);
        return ResponseEntity.ok(new SingleResponse<>(200, "하루 식사 기록 조회 성공", response));
    }

    @Operation(summary = "하루 중 특정 식사 기록 조회", description = "아침, 점심, 저녁, 간식 중 선택한 식사의 기록을 조회합니다.")
    @GetMapping("/meals/{mealTime}")
    public ResponseEntity<SingleResponse<DailyMealRecordResponseDto.MealRecordDto>> getMealByTime(
            @Parameter(description = "조회할 날짜", example = "2025-04-04")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @Parameter(description = "식사 시간 (BREAKFAST, LUNCH, DINNER, SNACK)", example = "LUNCH")
            @PathVariable MealType mealType
    ) {
        DailyMealRecordResponseDto.MealRecordDto result = mealRecordService.getMealRecordByTime(date, mealType);
        return ResponseEntity.ok(new SingleResponse<>(200, mealType.getDisplayName() + " 식사 조회 완료", result));
    }

    @Operation(summary = "하루 영양소 총합 조회", description = "사용자가 하루 동안 섭취한 총 영양소 정보를 조회합니다.")
    @GetMapping("/meals/nutrition/summary")
    public ResponseEntity<SingleResponse<DailyNutritionSummaryResponseDto>> getDailyNutritionSummary(
            @Parameter(description = "조회할 날짜", example = "2025-04-05")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        DailyNutritionSummaryResponseDto summary = mealRecordService.getDailyNutritionSummary(date);
        return ResponseEntity.ok(new SingleResponse<>(200, "하루 영양소 총합 조회 성공", summary));
    }

}
