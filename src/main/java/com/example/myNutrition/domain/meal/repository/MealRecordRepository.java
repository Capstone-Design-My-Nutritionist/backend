package com.example.myNutrition.domain.meal.repository;

import com.example.myNutrition.domain.meal.entity.MealRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealRecordRepository extends JpaRepository<MealRecord, Long> {
    List<MealRecord> findAllByUserIdAndDate(Long userId, LocalDate date);
}



