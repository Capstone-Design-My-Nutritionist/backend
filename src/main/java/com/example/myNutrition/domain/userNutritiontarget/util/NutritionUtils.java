package com.example.myNutrition.domain.userNutritiontarget.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NutritionUtils {

    /**
     * 주어진 값을 소수점 1자리까지 반올림합니다.
     * 예: 3.14159 → 3.1
     */
    public static double round1(double value) {
        return BigDecimal.valueOf(value)
                .setScale(1, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
