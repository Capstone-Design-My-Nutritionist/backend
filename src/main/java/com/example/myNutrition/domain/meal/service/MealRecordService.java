package com.example.myNutrition.domain.meal.service;

import com.example.myNutrition.common.security.util.SecurityUtils;
import com.example.myNutrition.domain.meal.dto.request.MealFoodRegisterRequestDto;
import com.example.myNutrition.domain.meal.dto.response.DailyMealRecordResponseDto;
import com.example.myNutrition.domain.meal.dto.response.DailyNutritionSummaryResponseDto;
import com.example.myNutrition.domain.meal.dto.response.NutritionSummaryDto;
import com.example.myNutrition.domain.meal.entity.*;
import com.example.myNutrition.domain.meal.repository.MealRecordRepository;
import com.example.myNutrition.domain.user.entity.User;
import com.example.myNutrition.domain.user.exception.UserNotFoundException;
import com.example.myNutrition.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MealRecordService {

    private final UserRepository userRepository;
    private final MealRecordRepository mealRecordRepository;

    @Transactional
    public Long registerMealRecord(MealFoodRegisterRequestDto request, String imageUrl) {
        Long userId = SecurityUtils.getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        // MealRecord 생성
        MealRecord mealRecord = MealRecord.builder()
                .user(user)
                .mealType(request.getMealType())
                .build();

        // MealImage 생성
        MealImage image = MealImage.builder()
                .imageUrl(imageUrl)
                .mealRecord(mealRecord)
                .build();

        // MealFood + NutritionDetail 생성 후 연결
        for (MealFoodRegisterRequestDto.MealFoodDto foodDto : request.getFoods()) {
            NutritionDetail nutrition = NutritionDetail.from(foodDto.getNutrition());

            MealFood mealFood = MealFood.builder()
                    .name(foodDto.getName())
                    .fullName(foodDto.getFullName())
                    .amount(foodDto.getEatAmount())
                    .mealImage(image)
                    .nutritionDetail(nutrition)
                    .build();

            image.addMealFood(mealFood);
        }

        mealRecord.addMealImage(image);
        mealRecordRepository.save(mealRecord);
        return mealRecord.getId();
    }


    @Transactional(readOnly = true)
    public DailyMealRecordResponseDto getDailyMealRecords(LocalDate date) {
        Long userId = SecurityUtils.getCurrentUserId();

        List<MealRecord> records = mealRecordRepository.findAllByUserIdAndDate(userId, date);

        List<DailyMealRecordResponseDto.MealRecordDto> mealDtos = records.stream().map(record -> {
            List<DailyMealRecordResponseDto.MealRecordDto.MealFoodDto> foodDtos = record.getMealImages().stream()
                    .flatMap(image -> image.getMealFoods().stream())
                    .map(food -> DailyMealRecordResponseDto.MealRecordDto.MealFoodDto.builder()
                            .name(food.getName())
                            .fullName(food.getFullName())
                            .imageUrl(food.getMealImage().getImageUrl())
                            .amount(food.getAmount())
                            .nutrition(NutritionSummaryDto.from(food.getNutritionDetail()))
                            .build())
                    .collect(Collectors.toList());

            return DailyMealRecordResponseDto.MealRecordDto.builder()
                    .mealType(record.getMealType())
                    .foods(foodDtos)
                    .build();
        }).toList();

        return DailyMealRecordResponseDto.builder()
                .date(date)
                .meals(mealDtos)
                .build();
    }

    @Transactional(readOnly = true)
    public DailyMealRecordResponseDto.MealRecordDto getMealRecordByTime(LocalDate date, MealType mealType) {
        Long userId = SecurityUtils.getCurrentUserId();

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);

        List<MealRecord> records = mealRecordRepository.findAllByUserIdAndMealTypeAndCreatedAtBetween(userId, mealType, start, end);

        List<DailyMealRecordResponseDto.MealRecordDto.MealFoodDto> foods = records.stream()
                .flatMap(record -> record.getMealImages().stream())
                .flatMap(image -> image.getMealFoods().stream())
                .map(food -> DailyMealRecordResponseDto.MealRecordDto.MealFoodDto.builder()
                        .name(food.getName())
                        .fullName(food.getFullName())
                        .imageUrl(food.getMealImage().getImageUrl())
                        .amount(food.getAmount())
                        .nutrition(NutritionSummaryDto.from(food.getNutritionDetail()))
                        .build())
                .collect(Collectors.toList());

        return DailyMealRecordResponseDto.MealRecordDto.builder()
                .mealType(mealType)
                .foods(foods)
                .build();
    }

    @Transactional(readOnly = true)
    public DailyNutritionSummaryResponseDto getDailyNutritionSummary(LocalDate date) {
        Long userId = SecurityUtils.getCurrentUserId();

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);

        List<MealRecord> records = mealRecordRepository.findAllByUserIdAndCreatedAtBetween(userId, start, end);

        // 누적값 초기화
        double totalEnergy = 0.0;
        double totalCarbohydrate = 0.0;
        double totalProtein = 0.0;
        double totalFat = 0.0;
        double totalSaturatedFat = 0.0;
        double totalTransFat = 0.0;
        double totalCholesterol = 0.0;
        double totalSugars = 0.0;
        double totalDietaryFiber = 0.0;
        double totalSodium = 0.0;
        double totalCalcium = 0.0;
        double totalVitaminA = 0.0;
        double totalVitaminC = 0.0;
        double totalVitaminD = 0.0;
        double totalVitaminE = 0.0;
        double totalVitaminB6 = 0.0;

        for (MealRecord record : records) {
            for (MealImage image : record.getMealImages()) {
                for (MealFood food : image.getMealFoods()) {
                    NutritionDetail detail = food.getNutritionDetail();

                    totalEnergy           += safeValue(detail, NutritionDetail::getEnergy);
                    totalCarbohydrate     += safeValue(detail, NutritionDetail::getCarbohydrate);
                    totalProtein          += safeValue(detail, NutritionDetail::getProtein);
                    totalFat              += safeValue(detail, NutritionDetail::getFat);
                    totalSaturatedFat     += safeValue(detail, NutritionDetail::getSaturatedFat);
                    totalTransFat         += safeValue(detail, NutritionDetail::getTransFat);
                    totalCholesterol      += safeValue(detail, NutritionDetail::getCholesterol);
                    totalSugars           += safeValue(detail, NutritionDetail::getSugar);
                    totalDietaryFiber     += safeValue(detail, NutritionDetail::getDietaryFiber);
                    totalSodium           += safeValue(detail, NutritionDetail::getSodium);
                    totalCalcium          += safeValue(detail, NutritionDetail::getCalcium);
                    totalVitaminA         += safeValue(detail, NutritionDetail::getVitaminA);
                    totalVitaminC         += safeValue(detail, NutritionDetail::getVitaminC);
                    totalVitaminD         += safeValue(detail, NutritionDetail::getVitaminD);
                    totalVitaminE         += safeValue(detail, NutritionDetail::getVitaminE);
                    totalVitaminB6        += safeValue(detail, NutritionDetail::getVitaminB);
                }
            }
        }

        return DailyNutritionSummaryResponseDto.from(
                date,
                totalEnergy,
                totalCarbohydrate,
                totalProtein,
                totalFat,
                totalSaturatedFat,
                totalTransFat,
                totalCholesterol,
                totalSugars,
                totalDietaryFiber,
                totalSodium,
                totalCalcium,
                totalVitaminA,
                totalVitaminC,
                totalVitaminD,
                totalVitaminE,
                totalVitaminB6
        );
    }

    /***
     * null일 수도 있는 상황을 안전하게 처리하기 위한 헬퍼 메서드
     * NutritionDetail이 null이면 0.0 반환
     * getter(detail.getEnergy() 등)를 통해 값 꺼냈을 때도 null이면 0.0 반환
     */
    private Double safeValue(NutritionDetail detail, Function<NutritionDetail, Double> getter) {
        if (detail == null) return 0.0;
        Double val = getter.apply(detail);
        return val != null ? val : 0.0;
    }
}
