package com.mangoplate.mangoplate.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserJoinRequest(
        String userId,
        String password,
        String nickname,
        @Email(message = "이메일 인증 형식이 아님.")
        String email

) {

}
