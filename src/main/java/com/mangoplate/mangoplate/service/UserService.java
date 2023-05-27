package com.mangoplate.mangoplate.service;

import com.mangoplate.mangoplate.domain.entity.UserEntity;
import com.mangoplate.mangoplate.domain.request.UserJoinRequest;
import com.mangoplate.mangoplate.domain.request.UserLoginRequest;
import com.mangoplate.mangoplate.domain.response.UserRegisterResponse;
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
    private final JwtTokenUtils jwtTokenUtils;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;



    public void user_join(UserJoinRequest request) {

        repository.findById(request.userId())
                .ifPresent(it -> {
                    throw new ApplicationException(ErrorCode.REGISTER_SAME_ID);
                });
        UserEntity user = request.getEntity();
        user.setPassword(passwordEncoder.encode(request.password()));
        repository.save(user);

    }

    public UserRegisterResponse user_login(UserLoginRequest request) {

        UserEntity userEntity = repository.findById(request.userId())
                .orElseThrow(() -> {
                    throw new ApplicationException(ErrorCode.LOGIN_NO_JOIN);
                });
        if (!passwordEncoder.matches(request.password(), userEntity.getPassword())) {
            throw new ApplicationException(ErrorCode.LOGIN_NO_PASSWORD);
        }

        //토큰 생성
        String token = JwtTokenUtils.generateToken(request.userId(), secretKey, expiredTimeMs);

        return new UserRegisterResponse(userEntity.getNickname(), token);
    }

    public UserEntity loadUserByUserId(String userId){
        return repository.findById(userId).orElseThrow(()->
                new ApplicationException(ErrorCode.LOGIN_NO_JOIN)
        );
    }

}
