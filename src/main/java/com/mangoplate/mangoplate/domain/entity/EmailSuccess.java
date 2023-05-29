package com.mangoplate.mangoplate.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Getter
@Setter
public class EmailSuccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String successKey;


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


    protected EmailSuccess(){}

    private EmailSuccess(String email, String successKey) {
        this.email = email;
        this.successKey = successKey;
    }

    public static EmailSuccess getEntity(String email, String successKey){
        return new EmailSuccess(email,successKey);
    }

}
