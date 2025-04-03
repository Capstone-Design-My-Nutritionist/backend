package com.example.myNutrition.domain.user.controller;


import com.example.myNutrition.common.response.SingleResponse;
import com.example.myNutrition.domain.user.dto.NicknameUpdateRequestDto;
import com.example.myNutrition.domain.user.dto.PasswordCheckRequestDto;
import com.example.myNutrition.domain.user.dto.PasswordUpdateRequestDto;
import com.example.myNutrition.domain.user.dto.UserCreateRequestDto;
import com.example.myNutrition.domain.user.security.CustomUserDetails;
import com.example.myNutrition.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<SingleResponse<String>> checkEmail(
            @Parameter(description = "중복 확인할 이메일", example = "test@example.com")
            @RequestParam String email) {

        userService.validateEmailAvailable(email);
        return ResponseEntity.ok(new SingleResponse<>(200, "사용 가능한 이메일입니다.", "OK"));
    }

    @Operation(summary = "닉네임 중복 확인", description = "입력한 닉네임이 사용 가능한지 여부를 확인합니다.")
    @GetMapping("/check/nickname")
    public ResponseEntity<SingleResponse<String>> checkNickname(
            @Parameter(description = "중복 확인할 닉네임", example = "test")
            @RequestParam String nickname) {

        userService.validateNicknameAvailable(nickname);
        return ResponseEntity.ok(new SingleResponse<>(200, "사용 가능한 닉네임입니다.", "OK"));
    }




    @Operation(summary = "기존 비밀번호 확인", description = "입력한 비밀번호가 현재 비밀번호와 일치하는지 확인합니다.")
    @PostMapping("/check/password")
    public ResponseEntity<SingleResponse<?>> checkPassword(
            @RequestBody PasswordCheckRequestDto requestDto,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        userService.checkPassword(userDetails.getUsername(), requestDto);
        return ResponseEntity.ok(new SingleResponse<>(200, "비밀번호 일치 ", null));
    }

    @Operation(summary = "비밀번호 변경", description = "현재 비밀번호와 새 비밀번호를 입력받아 비밀번호를 변경합니다.")
    @PutMapping("/password")
    public ResponseEntity<SingleResponse<Void>> updatePassword(
            @RequestBody PasswordUpdateRequestDto requestDto,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        userService.updatePassword(userDetails.getUsername(), requestDto);
        return ResponseEntity.ok(new SingleResponse<>(200, "비밀번호 변경 성공", null));
    }

    @Operation(summary = "닉네임 변경", description = "사용자의 닉네임을 변경합니다.")
    @PutMapping("/nickname")
    public ResponseEntity<SingleResponse<Void>> updateNickname(
            @RequestBody NicknameUpdateRequestDto requestDto,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        userService.updateNickname(userDetails.getUsername(), requestDto);
        return ResponseEntity.ok(new SingleResponse<>(200, "닉네임 변경 성공", null));
    }

}
