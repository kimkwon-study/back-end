package com.mangoplate.mangoplate.domain.request;

public record UserJoinRequest(
        String userId,
        String password,
        String nickname,
        String email

) {

}
