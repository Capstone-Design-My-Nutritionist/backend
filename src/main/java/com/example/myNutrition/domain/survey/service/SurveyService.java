package com.example.myNutrition.domain.survey.service;

import com.example.myNutrition.common.exception.NotFoundException;
import com.example.myNutrition.common.security.util.SecurityUtils;
import com.example.myNutrition.domain.survey.dto.request.*;
import com.example.myNutrition.domain.survey.entity.Survey;
import com.example.myNutrition.domain.survey.enums.singular.Gender;
import com.example.myNutrition.domain.survey.repository.SurveyRepository;
import com.example.myNutrition.domain.user.entity.User;
import com.example.myNutrition.domain.user.exception.UserNotFoundException;
import com.example.myNutrition.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;

    private Survey validateSurveyCreateRequest(Long surveyId) {
        return surveyRepository.findById(surveyId)
                .orElseThrow(() -> new NotFoundException("설문을 찾을 수 없습니다."));
    }

    @Transactional
    public Long createSurvey() {
        Long userId = SecurityUtils.getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));

        // 유저 연관 설문 생성
        Survey survey = Survey.builder()
                .user(user)
                .build();
        surveyRepository.save(survey);
        return survey.getId();
    }

    @Transactional
    public void updateGender(Long surveyId, GenderUpdateRequestDto requestDto) {
        Survey survey = validateSurveyCreateRequest(surveyId);

        survey.updateGender(requestDto.getGender());
    }

    @Transactional
    public void updateAge(Long surveyId, AgeUpdateRequestDto dto) {
        Survey survey = validateSurveyCreateRequest(surveyId);

        survey.updateAge(dto.age());
    }

    @Transactional
    public void updateHeight(Long surveyId, HeightUpdateRequestDto dto) {
        Survey survey = validateSurveyCreateRequest(surveyId);
        survey.updateHeight(dto.height());
    }

    @Transactional
    public void updateWeight(Long surveyId, WeightUpdateRequestDto dto) {
        Survey survey = validateSurveyCreateRequest(surveyId);
        survey.updateWeight(dto.weight());
    }

    @Transactional
    public void updateMedicationStatus(Long surveyId, MedicationStatusUpdateRequestDto dto) {
        Survey survey = validateSurveyCreateRequest(surveyId);
        survey.updateTakingMedication(dto.takingMedication());
    }



    @Transactional
    public void updateSupplementStatus(Long surveyId, SupplementStatusUpdateRequestDto dto) {
        Survey survey = validateSurveyCreateRequest(surveyId);
        survey.updateTakingSupplements(dto.takingSupplements());
    }

    @Transactional
    public void updateDiagnosedDiseaseStatus(Long surveyId, DiagnosedDiseaseStatusUpdateRequestDto dto) {
        Survey survey = validateSurveyCreateRequest(surveyId);
        survey.updateHasDiagnosedDisease(dto.hasDiagnosedDisease());
    }

    @Transactional
    public void updateFamilyHistoryStatus(Long surveyId, FamilyHistoryStatusUpdateRequestDto dto) {
        Survey survey = validateSurveyCreateRequest(surveyId);
        survey.updateHasFamilyHistory(dto.hasFamilyHistory());
    }

    @Transactional
    public void updateAllergyStatus(Long surveyId, AllergyStatusUpdateRequestDto dto) {
        Survey survey = validateSurveyCreateRequest(surveyId);
        survey.updateHasAllergy(dto.hasAllergy());
    }




}
