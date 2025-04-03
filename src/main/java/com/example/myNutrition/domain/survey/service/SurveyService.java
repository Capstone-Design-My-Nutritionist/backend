package com.example.myNutrition.domain.survey.service;

import com.example.myNutrition.common.exception.NotFoundException;
import com.example.myNutrition.common.security.util.SecurityUtils;
import com.example.myNutrition.domain.survey.dto.request.*;
import com.example.myNutrition.domain.survey.dto.request.plural.GenderUpdateRequestDto;
import com.example.myNutrition.domain.survey.dto.request.singular.*;
import com.example.myNutrition.domain.survey.entity.Survey;
import com.example.myNutrition.domain.survey.entity.mappingTable.*;
import com.example.myNutrition.domain.survey.enums.plural.*;
import com.example.myNutrition.domain.survey.repository.SurveyRepository;
import com.example.myNutrition.domain.user.entity.User;
import com.example.myNutrition.domain.user.exception.UserNotFoundException;
import com.example.myNutrition.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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




    @Transactional
    public void updateMedications(Long surveyId, List<Medication> medications) {
        Survey survey = validateSurveyCreateRequest(surveyId);
        survey.getMedications().clear();
        for (Medication medication : medications) {
            survey.getMedications().add(new SurveyMedication(medication, survey));
        }
    }

    @Transactional
    public void updateSupplements(Long surveyId, List<Supplement> supplements) {
        Survey survey = validateSurveyCreateRequest(surveyId);
        survey.getSupplements().clear();
        for (Supplement supplement : supplements) {
            survey.getSupplements().add(new SurveySupplement(supplement, survey));
        }
    }


    @Transactional
    public void updateDiseases(Long surveyId, List<Disease> diseases) {
        Survey survey = validateSurveyCreateRequest(surveyId);
        survey.getDiseases().clear();
        for (Disease disease : diseases) {
            survey.getDiseases().add(new SurveyDisease(disease, survey));
        }
    }

    @Transactional
    public void updateFamilyHistories(Long surveyId, List<FamilyHistory> familyHistorys) {
        Survey survey = validateSurveyCreateRequest(surveyId);
        survey.getFamilyHistories().clear();
        for (FamilyHistory familyHistory : familyHistorys) {
            survey.getFamilyHistories().add(new SurveyFamilyHistory(familyHistory, survey));
        }
    }

    @Transactional
    public void updateAllergies(Long surveyId, List<Allergy> allergies) {
        Survey survey = validateSurveyCreateRequest(surveyId);
        survey.getAllergies().clear();
        for (Allergy allergy : allergies) {
            survey.getAllergies().add(new SurveyAllergy(allergy, survey));
        }
    }

    @Transactional
    public void updateConcerns(Long surveyId, List<HealthConcern> healthConcerns) {
        Survey survey = validateSurveyCreateRequest(surveyId);
        survey.getConcerns().clear();
        for (HealthConcern healthConcern : healthConcerns) {
            survey.getConcerns().add(new SurveyConcern(healthConcern, survey));
        }
    }

    @Transactional
    public void updateHealthGoals(Long surveyId, List<HealthGoal> healthGoals) {
        Survey survey = validateSurveyCreateRequest(surveyId);
        survey.getHealthGoals().clear();
        for (HealthGoal healthGoal : healthGoals) {
            survey.getHealthGoals().add(new SurveyHealthGoal(healthGoal, survey));
        }
    }


    //Singular

    @Transactional
    public void updateSleepTime(Long surveyId, SurveySleepTimeRequestDto dto) {
        Survey survey = validateSurveyCreateRequest(surveyId);
        survey.updateSleepTime(dto.getSleepTime());
    }

    @Transactional
    public void updateExerciseFrequency(Long surveyId, SurveyExerciseFrequencyRequestDto dto) {
        Survey survey = validateSurveyCreateRequest(surveyId);
        survey.updateExerciseFrequency(dto.getExerciseFrequency());
    }

    @Transactional
    public void updateMealCount(Long surveyId, SurveyMealCountRequestDto dto) {
        Survey survey = validateSurveyCreateRequest(surveyId);
        survey.updateMealCount(dto.getMealCount());
    }

    @Transactional
    public void updateVegetableFruitIntake(Long surveyId, SurveyVegetableFruitIntakeRequestDto dto) {
        Survey survey = validateSurveyCreateRequest(surveyId);
        survey.updateVegetableFruitIntake(dto.getVegetableFruitIntake());
    }

    @Transactional
    public void updateWaterIntake(Long surveyId, SurveyWaterIntakeRequestDto dto) {
        Survey survey = validateSurveyCreateRequest(surveyId);
        survey.updateWaterIntake(dto.getWaterIntake());
    }

    @Transactional
    public void updateDrinking(Long surveyId, SurveyDrinkingRequestDto dto) {
        Survey survey = validateSurveyCreateRequest(surveyId);
        survey.updateDrinking(dto.getDrinking());
    }

    @Transactional
    public void updateSmoking(Long surveyId, SurveySmokingRequestDto dto) {
        Survey survey = validateSurveyCreateRequest(surveyId);
        survey.updateSmoking(dto.getSmoking());
    }




}
