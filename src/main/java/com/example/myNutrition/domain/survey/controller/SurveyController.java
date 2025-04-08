package com.example.myNutrition.domain.survey.controller;

import com.example.myNutrition.common.response.SingleResponse;
import com.example.myNutrition.domain.survey.dto.request.*;
import com.example.myNutrition.domain.survey.dto.request.plural.*;
import com.example.myNutrition.domain.survey.dto.request.singular.*;
import com.example.myNutrition.domain.survey.dto.response.SurveyResponseDto;
import com.example.myNutrition.domain.survey.service.SurveyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/surveys")
@RequiredArgsConstructor
@Tag(name = "Survey API", description = "설문조사 관련 API")
public class SurveyController {

    private final SurveyService surveyService;

    @Operation(summary = "설문 생성", description = "새로운 설문을 생성하고 surveyId를 반환합니다.")
    @PostMapping
    public ResponseEntity<SingleResponse<Long>> createSurvey() {
        Long surveyId = surveyService.createSurvey();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SingleResponse<>(201, "설문이 생성되었습니다.", surveyId));
    }

    @Operation(summary = "설문 조회", description = "로그인한 사용자의 설문 전체를 조회합니다.")
    @GetMapping
    public ResponseEntity<SingleResponse<SurveyResponseDto>> getSurvey() {
        SurveyResponseDto survey = surveyService.getSurvey();
        return ResponseEntity.ok(new SingleResponse<>(200, "설문 조회 성공", survey));
    }

    @Operation(summary = "설문 완료", description = "설문을 완료 상태로 표시합니다. 모든 항목이 입력되지 않았을 경우 에러를 반환합니다.")
    @PatchMapping("/complete")
    public ResponseEntity<SingleResponse<Void>> completeSurvey() {
        surveyService.completeSurvey();
        return ResponseEntity.ok(new SingleResponse<>(200, "설문 완료 처리 성공", null));
    }

    @PatchMapping("/{surveyId}/gender")
    @Operation(summary = "성별 저장", description = """
        성별을 저장합니다. 가능한 값:
        - MALE (남성)
        - FEMALE (여성)
        """)
    public ResponseEntity<SingleResponse<Void>> updateGender(
            @PathVariable Long surveyId,
            @RequestBody GenderUpdateRequestDto requestDto) {
        surveyService.updateGender(surveyId, requestDto);
        return ResponseEntity.ok(new SingleResponse<>(200, "성별 저장 완료", null));
    }

    @Operation(summary = "나이 저장", description = "정수값으로 나이를 저장합니다. 예: 25")
    @PatchMapping("/{surveyId}/age")
    public ResponseEntity<SingleResponse<String>> updateAge(
            @PathVariable Long surveyId,
            @RequestBody AgeUpdateRequestDto dto) {
        surveyService.updateAge(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "나이 저장 완료", null));
    }

    @Operation(summary = "키 저장", description = "정수값으로 키(cm)를 저장합니다. 예: 175")
    @PatchMapping("/{surveyId}/height")
    public ResponseEntity<SingleResponse<String>> updateHeight(
            @PathVariable Long surveyId,
            @RequestBody HeightUpdateRequestDto dto) {
        surveyService.updateHeight(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "키 저장 완료", null));
    }

    @Operation(summary = "몸무게 저장", description = "정수값으로 몸무게(kg)를 저장합니다. 예: 70")
    @PatchMapping("/{surveyId}/weight")
    public ResponseEntity<SingleResponse<String>> updateWeight(
            @PathVariable Long surveyId,
            @RequestBody WeightUpdateRequestDto dto) {
        surveyService.updateWeight(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "몸무게 저장 완료", null));
    }

    // Boolean 항목
    @Operation(summary = "약 복용 여부 저장", description = "약 복용 여부를 저장합니다. true(복용 중) 또는 false(아님)")
    @PatchMapping("/{surveyId}/medication-status")
    public ResponseEntity<SingleResponse<String>> updateMedicationStatus(
            @PathVariable Long surveyId,
            @RequestBody MedicationStatusUpdateRequestDto dto) {
        surveyService.updateMedicationStatus(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "약 복용 여부 저장 완료", null));
    }

    @Operation(summary = "영양제 복용 여부 저장", description = "영양제 복용 여부를 저장합니다. true(복용 중) 또는 false(아님)")
    @PatchMapping("/{surveyId}/supplement-status")
    public ResponseEntity<SingleResponse<String>> updateSupplementStatus(
            @PathVariable Long surveyId,
            @RequestBody SupplementStatusUpdateRequestDto dto) {
        surveyService.updateSupplementStatus(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "영양제 복용 여부 저장 완료", null));
    }

    @Operation(summary = "진단 질환 여부 저장", description = "진단 질환 유무를 저장합니다. true(있음) 또는 false(없음)")
    @PatchMapping("/{surveyId}/diagnosed-disease-status")
    public ResponseEntity<SingleResponse<String>> updateDiagnosedDiseaseStatus(
            @PathVariable Long surveyId,
            @RequestBody DiagnosedDiseaseStatusUpdateRequestDto dto) {
        surveyService.updateDiagnosedDiseaseStatus(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "진단 질환 여부 저장 완료", null));
    }

    @Operation(summary = "가족력 여부 저장", description = "가족력 유무를 저장합니다. true(있음) 또는 false(없음)")
    @PatchMapping("/{surveyId}/family-history-status")
    public ResponseEntity<SingleResponse<String>> updateFamilyHistoryStatus(
            @PathVariable Long surveyId,
            @RequestBody FamilyHistoryStatusUpdateRequestDto dto) {
        surveyService.updateFamilyHistoryStatus(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "가족력 여부 저장 완료", null));
    }

    @Operation(summary = "알레르기 여부 저장", description = "알레르기 유무를 저장합니다. true(있음) 또는 false(없음)")
    @PatchMapping("/{surveyId}/allergy-status")
    public ResponseEntity<SingleResponse<String>> updateAllergyStatus(
            @PathVariable Long surveyId,
            @RequestBody AllergyStatusUpdateRequestDto dto) {
        surveyService.updateAllergyStatus(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "알레르기 여부 저장 완료", null));
    }


    // 다중 선택 항목

    @Operation(summary = "복용약 선택 저장", description = """
        사용자가 복용 중인 약 정보를 저장합니다. 가능한 값:
        - BLOOD_PRESSURE (혈압)
        - ANTICOAGULANT (혈액응고제)
        - DIABETES (당뇨)
        - THYROID (갑상선)
        - STEROID (스테로이드)
        - GASTRIC (위장)
        - DEPRESSION (우울증)
        - CONTRACEPTIVE (피임)
        - PAINKILLER (진통제)
        """)
    @PatchMapping("/{surveyId}/medications")
    public ResponseEntity<SingleResponse<Void>> updateMedications(
            @PathVariable Long surveyId,
            @RequestBody SurveyMedicationRequestDto requestDto) {
        surveyService.updateMedications(surveyId, requestDto.getMedications());
        return ResponseEntity.ok(new SingleResponse<>(200, "복용약 정보 저장 완료", null));
    }

    @Operation(summary = "영양제 선택 저장", description = """
        건강기능식품 선택을 저장합니다. 가능한 값:
        - OMEGA3 (오메가-3)
        - VITAMIN_K
        - MAGNESIUM
        - CALCIUM
        - IRON
        - VITAMIN_C
        - PROBIOTICS
        - VITAMIN_D
        - GINSENG (홍삼)
        """)
    @PatchMapping("/{surveyId}/supplements")
    public ResponseEntity<SingleResponse<Void>> updateSupplements(
            @PathVariable Long surveyId,
            @RequestBody SurveySupplementRequestDto requestDto) {
        surveyService.updateSupplements(surveyId, requestDto.getSupplements());
        return ResponseEntity.ok(new SingleResponse<>(200, "영양제 정보 저장 완료", null));
    }

    @Operation(summary = "진단 질환 저장", description = """
        진단받은 질환을 저장합니다. 가능한 값:
        - DIABETES (당뇨병)
        - HYPERTENSION (고혈압)
        - HYPERLIPIDEMIA (고지혈증)
        - THYROID_DISEASE (갑상선 질환)
        - OSTEOPOROSIS (골다공증)
        - ANEMIA (빈혈)
        - GASTRIC_DISORDER (위장 장애)
        - LIVER_DISEASE (간 질환)
        - KIDNEY_DISEASE (신장 질환)
        """)
    @PatchMapping("/{surveyId}/diseases")
    public ResponseEntity<SingleResponse<Void>> updateDiseases(
            @PathVariable Long surveyId,
            @RequestBody SurveyDiseaseRequestDto requestDto) {
        surveyService.updateDiseases(surveyId, requestDto.getDiseases());
        return ResponseEntity.ok(new SingleResponse<>(200, "진단 질환 저장 완료", null));
    }

    @Operation(summary = "가족력 질환 저장", description = """
        가족력 질환을 저장합니다. 가능한 값:
        - DIABETES, HYPERTENSION, CARDIOVASCULAR
        - THYROID_DISEASE, OSTEOPOROSIS, HYPERLIPIDEMIA
        - CANCER, ALZHEIMER, KIDNEY_DISEASE
        """)
    @PatchMapping("/{surveyId}/family-histories")
    public ResponseEntity<SingleResponse<Void>> updateFamilyHistories(
            @PathVariable Long surveyId,
            @RequestBody SurveyFamilyHistoryRequestDto requestDto) {
        surveyService.updateFamilyHistories(surveyId, requestDto.getFamilyDiseases());
        return ResponseEntity.ok(new SingleResponse<>(200, "가족력 질환 저장 완료", null));
    }

    @Operation(summary = "알레르기 저장", description = """
        알레르기 항목을 저장합니다. 가능한 값:
        - NUTS (견과류)
        - DAIRY (유제품)
        - SHELLFISH (갑각류)
        - GLUTEN (글루텐)
        - NONE (해당 없음)
        """)
    @PatchMapping("/{surveyId}/allergies")
    public ResponseEntity<SingleResponse<Void>> updateAllergies(
            @PathVariable Long surveyId,
            @RequestBody SurveyAllergyRequestDto requestDto) {
        surveyService.updateAllergies(surveyId, requestDto.getAllergies());
        return ResponseEntity.ok(new SingleResponse<>(200, "알레르기 정보 저장 완료", null));
    }

    @Operation(summary = "건강 고민 저장", description = """
        건강 고민 항목을 저장합니다. 가능한 값:
        - FATIGUE (피로감)
        - WEIGHT_CONTROL (체중 조절)
        - DIGESTIVE_PROBLEM (소화 문제)
        - IMMUNITY (면역력 저하)
        - SLEEP (수면 문제)
        - SKIN_PROBLEM (피부 문제)
        """)
    @PatchMapping("/{surveyId}/concerns")
    public ResponseEntity<SingleResponse<Void>> updateConcerns(
            @PathVariable Long surveyId,
            @RequestBody SurveyHealthConcernRequestDto requestDto) {
        surveyService.updateConcerns(surveyId, requestDto.getConcerns());
        return ResponseEntity.ok(new SingleResponse<>(200, "건강 고민 저장 완료", null));
    }

    @Operation(summary = "건강 목표 저장", description = """
        건강 목표 항목을 저장합니다. 가능한 값:
        - WEIGHT_CONTROL("체중 조절"),
        - MUSCLE_GAIN("근육 증가"),
        - ENERGY("에너지 충전"),
        - BLOOD_PRESSURE_CONTROL("혈압 조절"),
        - DIGESTIVE_HEALTH("소화 개선"),
        - IMMUNE_SUPPORT("면역력 강화")
        """)
    @PatchMapping("/{surveyId}/goals")
    public ResponseEntity<SingleResponse<Void>> updateGoals(
            @PathVariable Long surveyId,
            @RequestBody SurveyHealthGoalRequestDto requestDto) {
        surveyService.updateHealthGoals(surveyId, requestDto.getGoals());
        return ResponseEntity.ok(new SingleResponse<>(200, "건강 목표 저장 완료", null));
    }

    // 단일 Enum/기타 항목 저장

    @Operation(summary = "수면 시간 저장", description = """
        수면 시간을 저장합니다. 가능한 값:
        - UNDER_5_HOURS (5시간 이하)
        - SIX_TO_SEVEN_HOURS (6~7시간)
        - OVER_8_HOURS (8시간 이상)
        """)
    @PatchMapping("/{surveyId}/sleep-time")
    public ResponseEntity<SingleResponse<String>> updateSleepTime(
            @PathVariable Long surveyId,
            @RequestBody SurveySleepTimeRequestDto dto) {
        surveyService.updateSleepTime(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "수면 시간 저장 완료", null));
    }

    @Operation(summary = "운동 빈도 저장", description = """
        주간 운동 빈도 저장. 가능한 값:
        - NONE (거의 하지 않음)
        - LIGHT_1_3 (1~3회 가벼운 운동)
        - MODERATE_3_5 (3~5회 보통 강도)
        - INTENSE_6_7 (6~7회 고강도)
        - DAILY_ACTIVE (매일 활동적이거나 운동선수)
        """)
    @PatchMapping("/{surveyId}/exercise-frequency")
    public ResponseEntity<SingleResponse<String>> updateExerciseFrequency(
            @PathVariable Long surveyId,
            @RequestBody SurveyExerciseFrequencyRequestDto dto) {
        surveyService.updateExerciseFrequency(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "운동 빈도 저장 완료", null));
    }

    @Operation(summary = "끼니 수 저장", description = "하루 평균 끼니 수를 정수(int)로 저장합니다. 예: 3")
    @PatchMapping("/{surveyId}/meal-count")
    public ResponseEntity<SingleResponse<String>> updateMealCount(
            @PathVariable Long surveyId,
            @RequestBody SurveyMealCountRequestDto dto) {
        surveyService.updateMealCount(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "끼니 수 저장 완료", null));
    }

    @Operation(summary = "채소/과일 섭취 빈도 저장", description = """
        섭취 빈도 저장. 가능한 값:
        - RARELY (거의 먹지 않음)
        - SOMETIMES (가끔 먹음)
        - DAILY (매일 먹음)
        """)
    @PatchMapping("/{surveyId}/vegetable-fruit-intake")
    public ResponseEntity<SingleResponse<String>> updateVegetableFruitIntake(
            @PathVariable Long surveyId,
            @RequestBody SurveyVegetableFruitIntakeRequestDto dto) {
        surveyService.updateVegetableFruitIntake(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "섭취 빈도 저장 완료", null));
    }

    @Operation(summary = "물 섭취량 저장", description = """
        하루 평균 물 섭취량을 저장합니다. 가능한 값:
        - UNDER_1L (1L 이하)
        - ONE_TO_TWO_L (1~2L)
        - OVER_2L (2L 이상)
        """)
    @PatchMapping("/{surveyId}/water-intake")
    public ResponseEntity<SingleResponse<String>> updateWaterIntake(
            @PathVariable Long surveyId,
            @RequestBody SurveyWaterIntakeRequestDto dto) {
        surveyService.updateWaterIntake(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "물 섭취량 저장 완료", null));
    }

    @Operation(summary = "음주 정보 저장", description = """
        음주 빈도 정보를 저장합니다. 가능한 값:
        - NEVER (전혀 안함)
        - SOMETIMES (가끔)
        - ONE_TWO_WEEKLY (주 1~2회)
        - THREE_MORE_WEEKLY (주 3회 이상)
        """)
    @PatchMapping("/{surveyId}/drinking")
    public ResponseEntity<SingleResponse<String>> updateDrinking(
            @PathVariable Long surveyId,
            @RequestBody SurveyDrinkingRequestDto dto) {
        surveyService.updateDrinking(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "음주 정보 저장 완료", null));
    }

    @Operation(summary = "흡연 정보 저장", description = """
        흡연 상태 정보를 저장합니다. 가능한 값:
        - NON_SMOKER (비흡연자)
        - PAST_SMOKER (과거 흡연)
        - CURRENT_SMOKER (현재 흡연)
        """)
    @PatchMapping("/{surveyId}/smoking")
    public ResponseEntity<SingleResponse<String>> updateSmoking(
            @PathVariable Long surveyId,
            @RequestBody SurveySmokingRequestDto dto) {
        surveyService.updateSmoking(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "흡연 정보 저장 완료", null));
    }
}