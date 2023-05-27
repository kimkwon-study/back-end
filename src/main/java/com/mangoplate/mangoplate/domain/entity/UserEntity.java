package com.mangoplate.mangoplate.domain.entity;

import com.mangoplate.mangoplate.domain.type.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    private String userId;

    @Column
    private String password;

    @Column
    private String nickname;

    @Column
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;


    @Column
    private Timestamp registeredAt;
    @Column
    private Timestamp updatedAt;


    @PrePersist
    void registeredAt() {
        this.registeredAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }

    private UserEntity(){}
    private UserEntity(String userId, String password, String email, UserRole role,String nickname) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.role = role;
        this.nickname = nickname;
    }

    public static UserEntity getEntity(String userId, String password, String email,String nickname) {
        return new UserEntity(userId,password,email,UserRole.USER,nickname);
    }

}