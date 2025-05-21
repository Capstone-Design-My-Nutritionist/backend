package com.example.myNutrition.domain.userNutritiontarget.controller;

import com.example.myNutrition.common.exception.NotFoundException;
import com.example.myNutrition.common.response.SingleResponse;
import com.example.myNutrition.common.security.util.SecurityUtils;
import com.example.myNutrition.domain.userNutritiontarget.dto.UserNutritionTargetResponseDto;
import com.example.myNutrition.domain.userNutritiontarget.entity.UserNutritionTarget;
import com.example.myNutrition.domain.userNutritiontarget.service.UserNutritionTargetService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user-nutrition-target")
@RequiredArgsConstructor
public class UserNutritionTargetController {

    private final UserNutritionTargetService userNutritionTargetService;

    @Operation(summary = "유저의 일일 영양소 목표값 조회")
    @GetMapping()
    public ResponseEntity<SingleResponse<UserNutritionTargetResponseDto>> getUserNutritionTarget() {
        UserNutritionTargetResponseDto response = userNutritionTargetService.getTargetForCurrentUser();
        return ResponseEntity.ok(new SingleResponse<>(200, "사용자 영양소 할당량 조회 성공", response));
    }

}
