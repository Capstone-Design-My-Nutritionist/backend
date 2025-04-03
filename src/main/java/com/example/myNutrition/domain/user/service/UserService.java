package com.example.myNutrition.domain.user.service;


import com.example.myNutrition.domain.user.dto.UserCreateRequestDto;
import com.example.myNutrition.domain.user.entity.UserEntity;
import com.example.myNutrition.domain.user.entity.UserRole;
import com.example.myNutrition.domain.user.exception.UserCreateFailException;
import com.example.myNutrition.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(UserCreateRequestDto userCreateRequestDto) {

        String encodedPassword = passwordEncoder.encode(userCreateRequestDto.getPassword());

        validateUserCreateRequest(userCreateRequestDto);
        log.info("[signUpUser] UserCreateRequestDto : {}", userCreateRequestDto);

        // todo : 중복 이메일, 닉네임 체크, 유저 상태, 유저 역할 변경 기능 추가
        UserEntity user = UserEntity.builder()
                .email(userCreateRequestDto.getEmail())
                .password(encodedPassword)
                .nickname(userCreateRequestDto.getNickname())
                .userRole(UserRole.USER)
                .build();

        userRepository.save(user);
        log.info("[signUpUser] SIGN UP SUCCESS!! : {}", user.getEmail());
    }

    public boolean isEmailAvailable(String email) {
        return !userRepository.existsByEmail(email);
    }

    public boolean isNicknameAvailable(String nickname) {
        return !userRepository.existsByNickname(nickname);
    }

    // todo : 정책적으로 보안 위반 사항 확인 -> 에러 메세지를 통해서 유추 금지
    private void validateUserCreateRequest(UserCreateRequestDto userCreateRequestDto) {
        if (userRepository.findByEmail(userCreateRequestDto.getEmail()).isPresent())
            throw new UserCreateFailException("이미 존재하는 이메일입니다.");
    }

}
