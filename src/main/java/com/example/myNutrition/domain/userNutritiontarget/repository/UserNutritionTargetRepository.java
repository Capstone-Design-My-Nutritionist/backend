package com.example.myNutrition.domain.userNutritiontarget.repository;

import com.example.myNutrition.domain.meal.entity.MealRecord;
import com.example.myNutrition.domain.userNutritiontarget.entity.UserNutritionTarget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserNutritionTargetRepository extends JpaRepository<UserNutritionTarget, Long> {
    Optional<UserNutritionTarget> findByUserId(Long userId);;
}
