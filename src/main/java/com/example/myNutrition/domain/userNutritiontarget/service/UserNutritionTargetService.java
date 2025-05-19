package com.example.myNutrition.domain.userNutritiontarget.service;

import com.example.myNutrition.common.exception.NotFoundException;
import com.example.myNutrition.common.security.util.SecurityUtils;
import com.example.myNutrition.domain.survey.entity.Survey;
import com.example.myNutrition.domain.survey.enums.singular.Gender;
import com.example.myNutrition.domain.survey.repository.SurveyRepository;
import com.example.myNutrition.domain.user.entity.User;
import com.example.myNutrition.domain.user.exception.UserNotFoundException;
import com.example.myNutrition.domain.user.repository.UserRepository;
import com.example.myNutrition.domain.userNutritiontarget.dto.UserNutritionTargetResponseDto;
import com.example.myNutrition.domain.userNutritiontarget.entity.UserNutritionTarget;
import com.example.myNutrition.domain.userNutritiontarget.exception.UserNutritionTargetCreationFailedException;
import com.example.myNutrition.domain.userNutritiontarget.repository.UserNutritionTargetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserNutritionTargetService {

    private final UserNutritionTargetRepository userNutritionTargetRepository;
    private final UserRepository userRepository;
    private final SurveyRepository surveyRepository;

    @Transactional
    public void calculateAndSave(User user, Survey survey) {
        // 1. 총 에너지 소비량(TDEE) 계산
        double energy = survey.calculateTDEE();

        // 2. 영양소 비율에 따른 kcal 계산
        double proteinKcal = energy * 0.2;
        double fatKcal = energy * 0.25;
        double carbKcal = energy - proteinKcal - fatKcal;

        // 3. g 단위로 변환
        double protein = proteinKcal / 4; // 단백질 1g = 4kcal
        double fat = fatKcal / 9;         // 지방 1g = 9kcal
        double carbohydrate = carbKcal / 4; // 탄수화물 1g = 4kcal

        // 4. 고정값 또는 성별 기반 기타 영양소 설정
        double cholesterol = 300.0; // mg
        double dietaryFiber = (survey.getGender() == Gender.MALE) ? 25.0 : 20.0; // g
        double saturatedFattyAcid = fat * 0.3; // g, 지방 중 약 30% 가정
        double transFattyAcid = 1.0; // g, 권장상한
        double sugars = 50.0; // g
        double sodium = 1500.0; // mg
        double calcium = 700.0; // mg
        double vitaminA = 700.0; // μg RE
        double vitaminC = 100.0; // mg
        double vitaminD = 10.0; // μg
        double vitaminE = 15.0; // mg α-TE
        double vitaminK = 90.0; // μg

        // 5. UserNutritionTarget 엔티티 생성 및 저장
        UserNutritionTarget target = UserNutritionTarget.of(
                user,
                energy,
                carbohydrate,
                protein,
                fat,
                cholesterol,
                dietaryFiber,
                saturatedFattyAcid,
                transFattyAcid,
                sugars,
                sodium,
                calcium,
                vitaminA,
                vitaminC,
                vitaminD,
                vitaminE,
                vitaminK
        );

        userNutritionTargetRepository.save(target);
    }


    @Transactional
    public UserNutritionTargetResponseDto getTargetForCurrentUser() {
        Long userId = SecurityUtils.getCurrentUserId();

        // 1. 조회 시도
        Optional<UserNutritionTarget> optional = userNutritionTargetRepository.findByUserId(userId);

        if (optional.isPresent()) {
            return UserNutritionTargetResponseDto.from(optional.get());
        }

        // 2. 없으면 계산 및 저장
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));
        Survey survey = surveyRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("설문 정보가 없습니다."));

        // 3. 계산 및 저장
        calculateAndSave(user, survey);

        UserNutritionTarget target = userNutritionTargetRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNutritionTargetCreationFailedException("영양소 목표 계산 후 조회 실패"));

        return UserNutritionTargetResponseDto.from(target);
    }
}