package com.example.myNutrition.domain.survey.service;

import com.example.myNutrition.common.exception.NotFoundException;
import com.example.myNutrition.common.security.util.SecurityUtils;
import com.example.myNutrition.domain.survey.entity.Survey;
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
}
