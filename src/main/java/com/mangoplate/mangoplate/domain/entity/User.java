package com.mangoplate.mangoplate.domain.entity;

import com.mangoplate.mangoplate.domain.type.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class User {

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

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.ALL)
    private List<Post> postList;

    protected User(){}
    private User(String userId, String password, String email, UserRole role, String nickname) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.role = role;
        this.nickname = nickname;
    }

    public static User getEntity(String userId, String password, String email, String nickname) {
        return new User(userId,password,email,UserRole.USER,nickname);
    }

}