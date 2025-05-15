package com.example.myNutrition.domain.meal.repository;

import com.example.myNutrition.domain.meal.entity.MealRecord;
import com.example.myNutrition.domain.meal.entity.MealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MealRecordRepository extends JpaRepository<MealRecord, Long> {
    @Query("SELECT m FROM MealRecord m WHERE m.user.id = :userId AND DATE(m.createdAt) = :date")
    List<MealRecord> findAllByUserIdAndDate(@Param("userId") Long userId, @Param("date") LocalDate date);

    List<MealRecord> findAllByUserIdAndMealTypeAndCreatedAtBetween(Long userId, MealType mealType, LocalDateTime start, LocalDateTime end);

    List<MealRecord> findAllByUserIdAndCreatedAtBetween(Long userId, LocalDateTime start, LocalDateTime end);
}



