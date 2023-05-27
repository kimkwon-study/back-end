package com.mangoplate.mangoplate.domain.request;

import com.mangoplate.mangoplate.domain.entity.UserEntity;

public record UserJoinRequest(
        String userId,
        String password,
        String nickname,
        String email

) {

    public UserEntity getEntity(){
        return UserEntity.getEntity(userId,password,email,nickname);
    }
}
