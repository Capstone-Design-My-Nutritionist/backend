package com.example.myNutrition.domain.user.service;


import com.example.myNutrition.common.exception.BadRequestException;
import com.example.myNutrition.common.exception.NotFoundException;
import com.example.myNutrition.domain.user.dto.NicknameUpdateRequestDto;
import com.example.myNutrition.domain.user.dto.PasswordCheckRequestDto;
import com.example.myNutrition.domain.user.dto.PasswordUpdateRequestDto;
import com.example.myNutrition.domain.user.dto.UserCreateRequestDto;
import com.example.myNutrition.domain.user.entity.UserEntity;
import com.example.myNutrition.domain.user.entity.UserRole;
import com.example.myNutrition.domain.user.exception.EmailAlreadyExistsException;
import com.example.myNutrition.domain.user.exception.NicknameAlreadyExistsException;
import com.example.myNutrition.domain.user.exception.NotSamePasswordException;
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

    public void validateEmailAvailable(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("이미 사용 중인 이메일입니다.");
        }
    }

    public void validateNicknameAvailable(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new NicknameAlreadyExistsException("이미 사용 중인 닉네임입니다.");
        }
    }

    public void checkPassword(String email, PasswordCheckRequestDto requestDto) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("유저 정보를 찾을 수 없습니다."));
        if (!passwordEncoder.matches(requestDto.password(), user.getPassword())) {
            throw new NotSamePasswordException("비밀번호가 일치하지 않습니다.");
        }
    }

    public void updatePassword(String email, PasswordUpdateRequestDto dto) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("유저 정보를 찾을 수 없습니다."));

        //기존 비밀번호와 같을시 에러
        if (passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new BadRequestException("기존 비밀번호와 동일한 비밀번호는 사용할 수 없습니다.");
        }

        user.updatePassword(passwordEncoder.encode(dto.password()));
        userRepository.save(user);
    }


    public void updateNickname(String email, NicknameUpdateRequestDto dto) {
        if (userRepository.existsByNickname(dto.nickname())) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("유저 정보를 찾을 수 없습니다."));

        user.updateNickname(dto.nickname());
        userRepository.save(user);
    }


    // todo : 정책적으로 보안 위반 사항 확인 -> 에러 메세지를 통해서 유추 금지
    private void validateUserCreateRequest(UserCreateRequestDto userCreateRequestDto) {
        if (userRepository.findByEmail(userCreateRequestDto.getEmail()).isPresent())
            throw new UserCreateFailException("이미 존재하는 이메일입니다.");
    }

}
