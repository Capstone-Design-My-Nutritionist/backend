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

        필드 설명:
        - `mealType`: BREAKFAST(아침), LUNCH(점심), DINNER(저녁), SNACK(간식) 중 선택 (Enum)
        - `image`: 사용자가 업로드한 음식 사진 (Multipart 형식)
        - `request`: 음식 데이터 본문 (JSON)
            - `foods`: 사용자가 섭취한 음식 리스트
                - `name`: 음식 이름 (예: White Rice)
                - `fullName`: 전체 이름 또는 상세 정보
                - `eatAmount`: 섭취량 (1.0 = 1인분 기준)
                - `nutrition`: 영양소 정보
                    - `energy`: 에너지 (kcal)
                    - `carbohydrate`: 탄수화물 (g)
                    - `protein`: 단백질 (g)
                    - `fat`: 지방 (g)
                    - `saturatedFattyAcid`: 포화지방산 (g)
                    - `transFattyAcid`: 트랜스지방산 (g)
                    - `cholesterol`: 콜레스테롤 (mg)
                    - `totalSugars`: 당류 (g)
                    - `totalDietaryFiber`: 식이섬유 (g)
                    - `sodium`: 나트륨 (mg)
                    - `calcium`: 칼슘 (mg)
                    - `vitaminA`: 비타민 A (μg RE)
                    - `vitaminC`: 비타민 C (mg)
                    - `vitaminD`: 비타민 D (μg)
                    - `vitaminE`: 비타민 E (mg α-TE)
                    - `vitaminB6`: 비타민 B6 (mg)

        주의사항:
        - `eatAmount`는 사용자가 선택한 섭취 비율이며, 1.0 = 1인분입니다.
        - 모든 영양소는 nullable이며, 누락된 정보는 생략해도 됩니다.
        - 이후 입력 항목은 유동적으로 변경될 수 있습니다.
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
    @GetMapping("/meals/{mealType}")
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

    @Operation(summary = "특정 음식 삭제", description = "특정 날짜, 식사 종류, 음식 이름으로 해당 음식 1개를 삭제합니다.")
    @DeleteMapping("/meals")
    public ResponseEntity<SingleResponse<String>> deleteMealFood(
            @Parameter(description = "조회할 날짜", example = "2025-04-05")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam MealType mealType,
            @RequestParam String foodName) {

        mealRecordService.deleteMealFood(date, mealType, foodName);
        return ResponseEntity.ok(new SingleResponse<>(200, "음식 삭제 완료", foodName));
    }

}
