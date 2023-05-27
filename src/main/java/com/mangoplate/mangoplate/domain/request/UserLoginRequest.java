package com.mangoplate.mangoplate.domain.request;

public record UserLoginRequest(
        String userId,
        String password
) {
}
