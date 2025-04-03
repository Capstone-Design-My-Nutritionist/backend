package com.example.myNutrition.domain.survey.controller;

import com.example.myNutrition.common.response.SingleResponse;
import com.example.myNutrition.domain.survey.dto.request.*;
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


    @PatchMapping("/{surveyId}/medications")
    @Operation(summary = "복용약 선택 항목 저장", description = "복용 중인 약을 선택합니다.")
    public ResponseEntity<SingleResponse<Void>> updateMedications(
            @PathVariable Long surveyId,
            @RequestBody UpdateMedicationsRequest request
    ) {
        surveyService.updateMedications(surveyId, request.medications());
        return ResponseEntity.ok(new SingleResponse<>(200, "복용약 항목 업데이트 완료", null));
    }


}
