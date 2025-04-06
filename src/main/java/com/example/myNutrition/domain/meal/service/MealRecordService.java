package com.example.myNutrition.domain.meal.service;

import com.example.myNutrition.common.security.util.SecurityUtils;
import com.example.myNutrition.domain.meal.dto.request.MealFoodRegisterRequestDto;
import com.example.myNutrition.domain.meal.dto.response.DailyMealRecordResponseDto;
import com.example.myNutrition.domain.meal.entity.MealFood;
import com.example.myNutrition.domain.meal.entity.MealImage;
import com.example.myNutrition.domain.meal.entity.MealRecord;
import com.example.myNutrition.domain.meal.entity.NutritionDetail;
import com.example.myNutrition.domain.meal.repository.MealRecordRepository;
import com.example.myNutrition.domain.user.entity.User;
import com.example.myNutrition.domain.user.exception.UserNotFoundException;
import com.example.myNutrition.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealRecordService {

    private final UserRepository userRepository;
    private final MealRecordRepository mealRecordRepository;

    @Transactional
    public Long registerMealRecord(MealFoodRegisterRequestDto request) {
        Long userId = SecurityUtils.getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        MealRecord mealRecord = MealRecord.builder()
                .user(user)
                .mealTime(request.getMealTime())
                .build();

        MealImage image = MealImage.builder()
                .imageUrl(request.getImageUrl())
                .mealRecord(mealRecord)
                .build();

        for (MealFoodRegisterRequestDto.MealFoodDto foodDto : request.getFoods()) {
            NutritionDetail nutrition = NutritionDetail.builder()
                    .energy(foodDto.getNutrition().getEnergy())
                    .carbohydrate(foodDto.getNutrition().getCarbohydrate())
                    .protein(foodDto.getNutrition().getProtein())
                    .fat(foodDto.getNutrition().getFat())
                    .vitaminA(foodDto.getNutrition().getVitaminA())
                    .vitaminC(foodDto.getNutrition().getVitaminC())
                    .calcium(foodDto.getNutrition().getCalcium())
                    .build();

            MealFood mealFood = MealFood.builder()
                    .name(foodDto.getName())
                    .amount(foodDto.getEatAmount())
                    .mealImage(image)
                    .nutritionDetail(nutrition)
                    .build();

            image.addMealFood(mealFood); // 연관관계 메서드
        }

        mealRecord.addMealImage(image); // 연관관계 메서드
        mealRecordRepository.save(mealRecord);
        return mealRecord.getId();
    }




}
