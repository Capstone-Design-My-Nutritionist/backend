package com.example.myNutrition.domain.survey.repository;

import com.example.myNutrition.domain.survey.entity.Survey;
import com.example.myNutrition.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    boolean existsByUser(User user);

    Optional<Survey> findByUserId(Long userId);
}
