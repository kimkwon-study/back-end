package com.mangoplate.mangoplate.service;

import com.mangoplate.mangoplate.domain.entity.User;
import com.mangoplate.mangoplate.domain.request.UserJoinRequest;
import com.mangoplate.mangoplate.domain.request.UserLoginRequest;
import com.mangoplate.mangoplate.domain.response.UserJoinResponse;
import com.mangoplate.mangoplate.domain.type.ErrorCode;
import com.mangoplate.mangoplate.exception.ApplicationException;
import com.mangoplate.mangoplate.repository.UserRepository;
import com.mangoplate.mangoplate.utill.JwtTokenUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;



    public void user_join(UserJoinRequest request) {

        repository.findById(request.userId())
                .ifPresent(it -> {
                    throw new ApplicationException(ErrorCode.REGISTER_SAME_ID);
                });

        User user = User.getEntity(
                request.userId(),
                passwordEncoder.encode(request.password()),
                request.email(),
                request.nickname());
        user.setPassword(passwordEncoder.encode(request.password()));
        repository.save(user);

    }

    public UserJoinResponse user_login(UserLoginRequest request) {

        User user = repository.findById(request.userId())
                .orElseThrow(() -> {
                    throw new ApplicationException(ErrorCode.LOGIN_NO_JOIN);
                });
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new ApplicationException(ErrorCode.LOGIN_NO_PASSWORD);
        }

        //토큰 생성
        String token = JwtTokenUtils.generateToken(request.userId(), secretKey, expiredTimeMs);

        return new UserJoinResponse(user.getNickname(), token);
    }

    public User loadUserByUserId(String userId){
        return repository.findById(userId).orElseThrow(()->
                new ApplicationException(ErrorCode.TOKEN_TIME_END)
        );
    }

}
