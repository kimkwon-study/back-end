package com.mangoplate.mangoplate.domain.response;

public record UserRegisterResponse(
        String nickname,
        String token
) {
}
