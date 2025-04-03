package com.example.myNutrition.domain.survey.repository;

import com.example.myNutrition.domain.survey.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
