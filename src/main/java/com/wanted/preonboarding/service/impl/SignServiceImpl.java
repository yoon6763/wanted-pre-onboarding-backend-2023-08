package com.wanted.preonboarding.service.impl;

import com.sun.jdi.request.DuplicateRequestException;
import com.wanted.preonboarding.config.security.JwtTokenProvider;
import com.wanted.preonboarding.entity.user.User;
import com.wanted.preonboarding.entity.user.dto.SignInResultDto;
import com.wanted.preonboarding.exception.user.UserException;
import com.wanted.preonboarding.exception.user.impl.EmailDuplicateException;
import com.wanted.preonboarding.exception.user.impl.PasswordShortException;
import com.wanted.preonboarding.repository.UserRepository;
import com.wanted.preonboarding.service.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SignServiceImpl implements SignService {
    private final Logger LOGGER = LoggerFactory.getLogger(SignService.class);

    public UserRepository userRepository;
    public JwtTokenProvider jwtTokenProvider;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public SignServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(String email, String password) throws UserException {
        LOGGER.info("[getSignUpResult] 회원 가입 정보 전달");

        validateEmail(email);
        validatePassword(password);

        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .roles(Collections.singletonList("ROLE_USER"))
                .build();

        userRepository.save(user);
    }

    private void validateEmail(String email) {
        LOGGER.info("[validateEmail] 이메일 중복 검사");
        userRepository.getByEmail(email).ifPresent(user -> {
            throw new EmailDuplicateException();
        });
    }

    private void validatePassword(String password) {
        if (password.length() < 8) {
            throw new PasswordShortException();
        }
    }

    @Override
    public SignInResultDto signIn(String email, String password) throws RuntimeException {
        LOGGER.info("[getSignInResult] signDataHandler로 회원 정보 요청");
        User user = userRepository.getByEmail(email).orElseThrow(RuntimeException::new);
        LOGGER.info("[getSignInResult] id : {}", email);

        LOGGER.info("[getSignInResult] 패스워드 비교 수행");
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException();
        }
        LOGGER.info("[getSignInResult] 패스워드 일치");

        LOGGER.info("[getSignInResult] SignInResultDto 객체 생성");
        SignInResultDto signInResultDto = new SignInResultDto(jwtTokenProvider.createToken(String.valueOf(user.getEmail()), user.getRoles()));

        LOGGER.info("[getSignInResult] SignInResultDto 객체에 값 주입");

        return signInResultDto;
    }
}