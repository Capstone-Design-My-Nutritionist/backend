package com.example.myNutrition.domain.user.controller;


import com.example.myNutrition.common.response.SingleResponse;
import com.example.myNutrition.domain.user.dto.UserCreateRequestDto;
import com.example.myNutrition.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users")
@Tag(name = "User API", description = "회원 관련 API")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "중복 체크 이후 최종 회원가입 진행.")
    @PostMapping("/signup")
    public ResponseEntity<SingleResponse<?>> signUpUser(
            @RequestBody @Valid UserCreateRequestDto userCreateRequestDto) {
        userService.createUser(userCreateRequestDto);
        return ResponseEntity.ok()
                .body(new SingleResponse<>(200, "회원가입 성공", null));
    }


    @Operation(summary = "이메일 중복 확인", description = "입력한 이메일이 사용 가능한지 여부를 확인합니다.")
    @GetMapping("/check/email")
    public ResponseEntity<SingleResponse<Boolean>> checkEmail(
            @Parameter(description = "중복 확인할 이메일", example = "test@example.com")
            @RequestParam String email) {
        boolean isAvailable = userService.isEmailAvailable(email);
        return ResponseEntity.ok(new SingleResponse<>(200, "email 사용 가능 여부", isAvailable));
    }

    @Operation(summary = "닉네임 중복 확인", description = "입력한 닉네임이 사용 가능한지 여부를 확인합니다.")
    @GetMapping("/check/nickname")
    public ResponseEntity<SingleResponse<Boolean>> checkNickname(
            @Parameter(description = "중복 확인할 닉네임", example = "test")
            @RequestParam String nickname) {
        boolean isAvailable = userService.isNicknameAvailable(nickname);
        return ResponseEntity.ok(new SingleResponse<>(200, "nickname 사용 가능 여부", isAvailable));
    }

}
