package com.example.myNutrition.domain.survey.controller;

import com.example.myNutrition.common.response.SingleResponse;
import com.example.myNutrition.domain.survey.dto.request.*;
import com.example.myNutrition.domain.survey.dto.request.plural.*;
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
        Long surveyId = surveyService.createSurvey();  // 현재 로그인한 유저로 생성
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SingleResponse<>(201, "설문이 생성되었습니다.", surveyId));
    }

    @PatchMapping("/{surveyId}/gender")
    @Operation(summary = "성별 업데이트", description = "설문에서 성별을 저장합니다.")
    public ResponseEntity<SingleResponse<Void>> updateGender(
            @PathVariable Long surveyId,
            @Valid @RequestBody GenderUpdateRequestDto requestDto) {

        surveyService.updateGender(surveyId, requestDto);
        return ResponseEntity.ok(new SingleResponse<>(200, "성별 업데이트 완료", null));
    }

    @Operation(summary = "나이 저장", description = "설문에서 나이 항목을 저장합니다.")
    @PatchMapping("/{surveyId}/age")
    public ResponseEntity<SingleResponse<String>> updateAge(
            @PathVariable Long surveyId,
            @RequestBody AgeUpdateRequestDto dto
    ) {
        surveyService.updateAge(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "나이 저장 완료", null));
    }

    @Operation(summary = "키 저장", description = "설문에서 키 항목을 저장합니다.")
    @PatchMapping("/{surveyId}/height")
    public ResponseEntity<SingleResponse<String>> updateHeight(
            @PathVariable Long surveyId,
            @RequestBody HeightUpdateRequestDto dto
    ) {
        surveyService.updateHeight(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "키 저장 완료", null));
    }

    @Operation(summary = "몸무게 저장", description = "설문에서 몸무게 항목을 저장합니다.")
    @PatchMapping("/{surveyId}/weight")
    public ResponseEntity<SingleResponse<String>> updateWeight(
            @PathVariable Long surveyId,
            @RequestBody WeightUpdateRequestDto dto
    ) {
        surveyService.updateWeight(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "몸무게 저장 완료", null));
    }



    //boolean

    @Operation(summary = "약 복용 여부 저장", description = "설문에서 약 복용 여부를 저장합니다.")
    @PatchMapping("/{surveyId}/medication-status")
    public ResponseEntity<SingleResponse<String>> updateMedicationStatus(
            @PathVariable Long surveyId,
            @RequestBody MedicationStatusUpdateRequestDto dto
    ) {
        surveyService.updateMedicationStatus(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "약 복용 여부 저장 완료", null));
    }

    @Operation(summary = "영양제 복용 여부 저장", description = "설문에서 영양제 복용 여부를 저장합니다.")
    @PatchMapping("/{surveyId}/supplement-status")
    public ResponseEntity<SingleResponse<String>> updateSupplementStatus(
            @PathVariable Long surveyId,
            @RequestBody SupplementStatusUpdateRequestDto dto
    ) {
        surveyService.updateSupplementStatus(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "영양제 복용 여부 저장 완료", null));
    }

    @Operation(summary = "진단 질환 여부 저장", description = "설문에서 진단 질환 유무를 저장합니다.")
    @PatchMapping("/{surveyId}/diagnosed-disease-status")
    public ResponseEntity<SingleResponse<String>> updateDiagnosedDiseaseStatus(
            @PathVariable Long surveyId,
            @RequestBody DiagnosedDiseaseStatusUpdateRequestDto dto
    ) {
        surveyService.updateDiagnosedDiseaseStatus(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "진단 질환 여부 저장 완료", null));
    }

    @Operation(summary = "가족력 여부 저장", description = "설문에서 가족력 유무를 저장합니다.")
    @PatchMapping("/{surveyId}/family-history-status")
    public ResponseEntity<SingleResponse<String>> updateFamilyHistoryStatus(
            @PathVariable Long surveyId,
            @RequestBody FamilyHistoryStatusUpdateRequestDto dto
    ) {
        surveyService.updateFamilyHistoryStatus(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "가족력 여부 저장 완료", null));
    }

    @Operation(summary = "알레르기 여부 저장", description = "설문에서 알레르기 유무를 저장합니다.")
    @PatchMapping("/{surveyId}/allergy-status")
    public ResponseEntity<SingleResponse<String>> updateAllergyStatus(
            @PathVariable Long surveyId,
            @RequestBody AllergyStatusUpdateRequestDto dto
    ) {
        surveyService.updateAllergyStatus(surveyId, dto);
        return ResponseEntity.ok(new SingleResponse<>(200, "알레르기 여부 저장 완료", null));
    }



    //다중 선택 항목

    @Operation(summary = "복용약 선택 저장", description = "설문에서 사용자가 복용 중인 약 정보를 저장합니다.")
    @PatchMapping("/{surveyId}/medications")
    public ResponseEntity<SingleResponse<Void>> updateMedications(
            @PathVariable Long surveyId,
            @RequestBody SurveyMedicationRequestDto requestDto) {
        surveyService.updateMedications(surveyId, requestDto.getMedications());
        return ResponseEntity.ok(new SingleResponse<>(200, "복용약 정보 저장 완료", null));
    }

    @Operation(summary = "영양제 선택 저장", description = "설문에서 사용자가 복용 중인 건강기능식품 정보를 저장합니다.")
    @PatchMapping("/{surveyId}/supplements")
    public ResponseEntity<SingleResponse<Void>> updateSupplements(
            @PathVariable Long surveyId,
            @RequestBody SurveySupplementRequestDto requestDto) {
        surveyService.updateSupplements(surveyId, requestDto.getSupplements());
        return ResponseEntity.ok(new SingleResponse<>(200, "영양제 정보 저장 완료", null));
    }

    @Operation(summary = "진단 질환 저장", description = "설문에서 사용자가 진단받은 질환 정보를 저장합니다.")
    @PatchMapping("/{surveyId}/diseases")
    public ResponseEntity<SingleResponse<Void>> updateDiseases(
            @PathVariable Long surveyId,
            @RequestBody SurveyDiseaseRequestDto requestDto) {
        surveyService.updateDiseases(surveyId, requestDto.getDiseases());
        return ResponseEntity.ok(new SingleResponse<>(200, "진단 질환 저장 완료", null));
    }

    @Operation(summary = "가족력 질환 저장", description = "설문에서 사용자의 가족력 정보를 저장합니다.")
    @PatchMapping("/{surveyId}/family-histories")
    public ResponseEntity<SingleResponse<Void>> updateFamilyHistories(
            @PathVariable Long surveyId,
            @RequestBody SurveyFamilyHistoryRequestDto requestDto) {
        surveyService.updateFamilyHistories(surveyId, requestDto.getFamilyDiseases());
        return ResponseEntity.ok(new SingleResponse<>(200, "가족력 질환 저장 완료", null));
    }

    @Operation(summary = "알레르기 저장", description = "설문에서 사용자의 알레르기 정보를 저장합니다.")
    @PatchMapping("/{surveyId}/allergies")
    public ResponseEntity<SingleResponse<Void>> updateAllergies(
            @PathVariable Long surveyId,
            @RequestBody SurveyAllergyRequestDto requestDto) {
        surveyService.updateAllergies(surveyId, requestDto.getAllergies());
        return ResponseEntity.ok(new SingleResponse<>(200, "알레르기 정보 저장 완료", null));
    }

    @Operation(summary = "건강 고민 저장", description = "설문에서 사용자가 가진 건강 고민을 저장합니다.")
    @PatchMapping("/{surveyId}/concerns")
    public ResponseEntity<SingleResponse<Void>> updateConcerns(
            @PathVariable Long surveyId,
            @RequestBody SurveyHealthConcernRequestDto requestDto) {
        surveyService.updateConcerns(surveyId, requestDto.getConcerns());
        return ResponseEntity.ok(new SingleResponse<>(200, "건강 고민 저장 완료", null));
    }

    @Operation(summary = "건강 목표 저장", description = "설문에서 사용자의 건강 목표 정보를 저장합니다.")
    @PatchMapping("/{surveyId}/goals")
    public ResponseEntity<SingleResponse<Void>> updateGoals(
            @PathVariable Long surveyId,
            @RequestBody SurveyHealthGoalRequestDto requestDto) {
        surveyService.updateHealthGoals(surveyId, requestDto.getGoals());
        return ResponseEntity.ok(new SingleResponse<>(200, "건강 목표 저장 완료", null));
    }


}
