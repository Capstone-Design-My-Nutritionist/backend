package com.example.myNutrition.common.config;


import com.example.myNutrition.common.security.filter.ExceptionHandlerFilter;
import com.example.myNutrition.common.security.filter.JwtAuthenticationFilter;
import com.example.myNutrition.common.security.jwt.JwtTokenProvider;
import com.example.myNutrition.common.security.jwt.JwtUtils;
import com.example.myNutrition.common.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final JwtUtils jwtUtils;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(CsrfConfigurer::disable) // csrf 비활성화
                .cors(Customizer.withDefaults()) // cors 설정
                .formLogin(FormLoginConfigurer::disable) // form login 비활성화
                .httpBasic(HttpBasicConfigurer::disable) // basic auth 비활성화
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용하지 않음
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll() // Swagger 관련 경로 허용
                        .requestMatchers("/api/**").permitAll() //공개 API
                        .requestMatchers("/auth/**").permitAll() //hasRole("ADMIN") // 관리자 API
                        .anyRequest().authenticated() // 나머지는 인증 필요
                )
                // JwtAuthenticationFilter 추가
                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService, jwtService, jwtUtils),
                        UsernamePasswordAuthenticationFilter.class
                )
                // 예외 처리 필터 추가
                .addFilterBefore(new ExceptionHandlerFilter(), LogoutFilter.class);

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.fromHierarchy("ROLE_ADMIN > ROLE_USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}