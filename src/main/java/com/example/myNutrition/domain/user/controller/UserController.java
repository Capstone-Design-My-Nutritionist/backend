package com.example.myNutrition.domain.user.controller;


import com.example.myNutrition.domain.user.dto.UserCreateRequestDto;
import com.example.myNutrition.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users", produces = "plain/text; charset=utf-8")
@Tag(name = "User API")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(
            @RequestBody @Valid UserCreateRequestDto userCreateRequestDto) {
        userService.createUser(userCreateRequestDto);
        return ResponseEntity.ok("회원가입 성공");
    }

}
