package com.example.myNutrition.common.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {

    @Schema(description = "이메일 주소", example = "gisu1102@gmail.com")
    @NotBlank
    private String email;

    @Schema(description = "비밀번호", example = "passowrd")
    @NotBlank
    private String password;

}
